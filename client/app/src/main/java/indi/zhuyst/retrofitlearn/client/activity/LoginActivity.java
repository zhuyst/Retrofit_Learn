package indi.zhuyst.retrofitlearn.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import indi.zhuyst.retrofitlearn.client.R;
import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import indi.zhuyst.retrofitlearn.client.presenter.LoginPresenter;
import indi.zhuyst.retrofitlearn.client.validator.EmptyValidator;
import indi.zhuyst.retrofitlearn.client.view.LoginView;

public class LoginActivity extends RxAppCompatActivity implements LoginView {
    private LoginPresenter loginPresenter;

    @BindView(R.id.text_username)
    MaterialEditText textUsername;

    @BindView(R.id.text_password)
    MaterialEditText textPassword;

    @BindView(R.id.remember_password)
    CheckBox rememberPassword;

    @BindView(R.id.auto_login)
    CheckBox autoLogin;

    @BindView(R.id.button_login)
    CircularProgressButton button_login;

    @BindView(R.id.button_register)
    CircularProgressButton button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);

        //添加表单验证器
        textUsername.addValidator(new EmptyValidator("用户名不能为空"));
        textPassword.addValidator(new EmptyValidator("密码不能为空"));

        //读取用户信息
        User user = loginPresenter.loadUserInfo();
        textUsername.setText(user.getUsername());
        textPassword.setText(user.getPassword());
        rememberPassword.setChecked(user.getRememberChecked());
        autoLogin.setChecked(user.getAutoLoginChecked());

        //如果勾选了自动登陆，进行登陆
        if(user.getAutoLoginChecked()){
            loginPresenter.login(user);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //注册成功后将用户名放入textUsername中
                    String username = data.getStringExtra("username");
                    textUsername.setText(username);
                    Toast.makeText(getApplicationContext(), "注册成功，请登录", Toast.LENGTH_SHORT).show();

                    //设置register按钮为Success
                    button_register.setProgress(100);
                }
                break;
        }
    }

    @Override
    public void onLoginResult(Msg msg) {
        if (msg.getSuccess()) {
            //设置登陆按钮为Success
            button_login.setProgress(100);
            Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();

            //封装用户信息后存入系统
            User user = new User(textUsername.getText().toString(), textPassword.getText().toString());
            user.setRememberChecked(rememberPassword.isChecked());
            user.setAutoLoginChecked(autoLogin.isChecked());
            loginPresenter.saveUserInfo(user);

            //进入主界面
            Intent intent = new Intent(LoginActivity.this, UserInfoActivity.class);
            intent.putExtra("username", textUsername.getText().toString());
            startActivity(intent);
            finish();
        } else {
            //设置登陆按钮为Error，并显示错误信息
            button_login.setProgress(-1);
            button_login.setErrorText(msg.getMessage());
            Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startLogin() {
        //设置登陆按钮为Progress，并设置为无限转动
        button_login.setIndeterminateProgressMode(true);
        button_login.setProgress(1);
    }

    @OnClick(R.id.button_login)
    public void login() {
        //如果登陆按钮状态为Error，则进行归位
        if(button_login.getProgress() == -1){
            button_login.setProgress(0);
        }
        //先进行表单检查
        else if(textUsername.validate() && textPassword.validate()){
            User user = new User(textUsername.getText().toString(), textPassword.getText().toString());
            loginPresenter.login(user);
        }
    }

    @OnClick(R.id.button_register)
    public void register() {
        //如果注册按钮状态为Success，则进行归位
        if(button_register.getProgress() == 100){
            button_register.setProgress(0);
        }
        else {
            //跳转注册界面
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @OnClick(R.id.remember_password)
    public void remember_password(){
        //如果没有勾选记住密码，则不能勾选自动登陆
        if(autoLogin.isChecked() && !rememberPassword.isChecked()){
            autoLogin.setChecked(false);
        }
    }

    @OnClick(R.id.auto_login)
    public void autoLogin(){
        //如果勾选自动登陆，记住密码也应被勾选
        if(autoLogin.isChecked()){
            rememberPassword.setChecked(true);
        }
    }
}