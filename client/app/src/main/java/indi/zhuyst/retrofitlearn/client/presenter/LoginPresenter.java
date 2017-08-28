package indi.zhuyst.retrofitlearn.client.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

import indi.zhuyst.retrofitlearn.client.RetrofitLearnApplication;
import indi.zhuyst.retrofitlearn.client.enums.UserEditorEnum;
import indi.zhuyst.retrofitlearn.client.model.UserModel;
import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import indi.zhuyst.retrofitlearn.client.view.LoginView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhuyst on 2017/7/10.
 */

public class LoginPresenter {
    private LoginView loginView;
    private UserModel userModel;

    public LoginPresenter(LoginView view){
        this.loginView = view;
        userModel = RetrofitLearnApplication.getRetrofit().create(UserModel.class);
    }

    public void login(User user) {
        userModel.login(user).timeout(5000, TimeUnit.MILLISECONDS)
                .compose(loginView.<Msg>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Msg>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        loginView.startLogin();
                    }

                    @Override
                    public void onNext(@NonNull Msg msg) {
                        loginView.onLoginResult(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Msg msg = new Msg();
                        msg.setSuccess(false);
                        msg.setMessage("连接服务器失败");
                        loginView.onLoginResult(msg);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public User loadUserInfo(){
        SharedPreferences preferences = loginView.getSharedPreferences("user", Context.MODE_PRIVATE);

        UserEditorEnum usernameEnum = UserEditorEnum.username;
        String username = preferences.getString(usernameEnum.toString(), usernameEnum.getStringDefaultValue());

        UserEditorEnum rememberCheckedEnum = UserEditorEnum.remember_checked;
        Boolean isRememberChecked = preferences.getBoolean(rememberCheckedEnum.toString(), rememberCheckedEnum.getBooleanDefaultValue());

        UserEditorEnum autoLoginCheckedEnum = UserEditorEnum.autoLogin_checked;
        Boolean isAutoLoginChecked = preferences.getBoolean(autoLoginCheckedEnum.toString(), autoLoginCheckedEnum.getBooleanDefaultValue());

        UserEditorEnum passwordEnum = UserEditorEnum.password;
        String password;
        if(isRememberChecked){
            password = preferences.getString(passwordEnum.toString(), passwordEnum.getStringDefaultValue());
        }
        else{
            password = passwordEnum.getStringDefaultValue();
        }

        User user = new User(username,password);
        user.setRememberChecked(isRememberChecked);
        user.setAutoLoginChecked(isAutoLoginChecked);
        return user;
    }

    public void saveUserInfo(User user){
        SharedPreferences.Editor editor = loginView.getSharedPreferences("user", Context.MODE_PRIVATE).edit();

        editor.putString(UserEditorEnum.username.toString(),user.getUsername());
        if(user.getRememberChecked()){
            editor.putString(UserEditorEnum.password.toString(),user.getPassword());
        }
        editor.putBoolean(UserEditorEnum.remember_checked.toString(),user.getRememberChecked());
        editor.putBoolean(UserEditorEnum.autoLogin_checked.toString(),user.getAutoLoginChecked());

        editor.apply();
    }
}
