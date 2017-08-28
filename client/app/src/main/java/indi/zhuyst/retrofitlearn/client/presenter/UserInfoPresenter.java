package indi.zhuyst.retrofitlearn.client.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

import indi.zhuyst.retrofitlearn.client.RetrofitLearnApplication;
import indi.zhuyst.retrofitlearn.client.enums.UserEditorEnum;
import indi.zhuyst.retrofitlearn.client.model.UserModel;
import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import indi.zhuyst.retrofitlearn.client.view.UserInfoView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhuyst on 2017/7/11.
 */

public class UserInfoPresenter {
    private UserInfoView userInfoView;
    private UserModel userModel;

    public UserInfoPresenter(UserInfoView view){
        this.userInfoView = view;
        userModel = RetrofitLearnApplication.getRetrofit().create(UserModel.class);
    }

    public void getUserInfo(String username){
        userModel.getUser(username).timeout(5000, TimeUnit.MILLISECONDS)
                .compose(userInfoView.<Msg<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Msg<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull Msg<User> msg) {
                        userInfoView.onQueryResult(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Msg<User> msg = new Msg<>();
                        msg.setSuccess(false);
                        msg.setMessage("连接服务器失败！");
                        userInfoView.onQueryResult(msg);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    public void updateUserInfo(User user){
        userModel.updateInfo(user.getUsername(),user).timeout(5000, TimeUnit.MILLISECONDS)
                .compose(userInfoView.<Msg>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Msg>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        userInfoView.startUpdate();
                    }

                    @Override
                    public void onNext(@NonNull Msg msg) {
                        userInfoView.onUpdateResult(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Msg msg = new Msg();
                        msg.setSuccess(false);
                        msg.setMessage("连接服务器失败");
                        userInfoView.onUpdateResult(msg);
                    }

                    @Override
                    public void onComplete(){}
                });
    }

    public void logoff(){
        SharedPreferences.Editor editor = userInfoView.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        editor.putBoolean(UserEditorEnum.autoLogin_checked.toString(),false);
        editor.apply();
    }
}
