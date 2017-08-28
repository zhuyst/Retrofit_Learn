package indi.zhuyst.retrofitlearn.client.presenter;

import java.util.concurrent.TimeUnit;

import indi.zhuyst.retrofitlearn.client.RetrofitLearnApplication;
import indi.zhuyst.retrofitlearn.client.model.UserModel;
import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import indi.zhuyst.retrofitlearn.client.view.RegisterView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhuyst on 2017/7/11.
 */

public class RegisterPresenter {
    private RegisterView registerView;
    private UserModel userModel;

    public RegisterPresenter(RegisterView view){
        this.registerView = view;
        userModel = RetrofitLearnApplication.getRetrofit().create(UserModel.class);
    }

    public void register(User user){
        userModel.register(user).timeout(5000, TimeUnit.MILLISECONDS)
                .compose(registerView.<Msg>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Msg>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        registerView.startRegister();
                    }

                    @Override
                    public void onNext(@NonNull Msg msg) {
                        registerView.onRegisterResult(msg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Msg msg = new Msg();
                        msg.setSuccess(false);
                        msg.setMessage("连接服务器失败");
                        registerView.onRegisterResult(msg);
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
