package indi.zhuyst.retrofitlearn.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import indi.zhuyst.retrofitlearn.client.R;
import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import indi.zhuyst.retrofitlearn.client.presenter.RegisterPresenter;
import indi.zhuyst.retrofitlearn.client.validator.EmailValidator;
import indi.zhuyst.retrofitlearn.client.validator.EmptyValidator;
import indi.zhuyst.retrofitlearn.client.validator.LengthValidator;
import indi.zhuyst.retrofitlearn.client.validator.PasswordValidator;
import indi.zhuyst.retrofitlearn.client.view.RegisterView;

/**
 * Created by zhuyst on 2017/7/11.
 */

public class RegisterActivity extends RxAppCompatActivity implements RegisterView {
    private RegisterPresenter registerPresenter;

    @BindView(R.id.text_username)
    MaterialEditText textUsername;

    @BindView(R.id.text_password)
    MaterialEditText textPassword;

    @BindView(R.id.text_password_retry)
    MaterialEditText textPasswordRetry;

    @BindView(R.id.text_email)
    MaterialEditText textEmail;

    @BindView(R.id.group_sex)
    RadioGroup groupSex;

    @BindView(R.id.button_register)
    CircularProgressButton button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);

        //添加表单验证器
        textUsername.addValidator(new LengthValidator(5,12));
        textPassword.addValidator(new LengthValidator(5,12));
        textEmail.addValidator(new EmailValidator());
        textPasswordRetry.addValidator(new EmptyValidator("重复密码不能为空！"))
                .addValidator(new PasswordValidator(textPassword.getText()));
    }

    @Override
    public void onRegisterResult(Msg msg) {
        if(msg.getSuccess()){
            //设置注册按钮为Success
            button_register.setProgress(100);

            //将username带回登陆界面
            Intent intent = new Intent();
            intent.putExtra("username",textUsername.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
        else {
            //设置注册按钮为Error，并显示错误信息
            button_register.setProgress(-1);
            button_register.setErrorText(msg.getMessage());
            Toast.makeText(getApplicationContext(),msg.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startRegister() {
        //设置注册按钮为Progress，并设置为无限转动
        button_register.setIndeterminateProgressMode(true);
        button_register.setProgress(1);
    }

    @OnClick(R.id.button_register)
    public void register() {
        //如果注册按钮状态为Error，则进行归位
        if(button_register.getProgress() == -1){
            button_register.setProgress(0);
        }
        //先进行表单验证
        else if(textUsername.validate() &&
                textPassword.validate() &&
                textPasswordRetry.validate() &&
                (textEmail.getText().toString().equals("") || textEmail.validate())){

            //包装表单数据并进行注册
            User user = new User(textUsername.getText().toString(),textPassword.getText().toString());

            user.setEmail(textEmail.getText().toString());
            RadioButton radioButton = (RadioButton) findViewById(groupSex.getCheckedRadioButtonId());
            user.setSex(radioButton.getText().toString());

            registerPresenter.register(user);
        }
    }
}
