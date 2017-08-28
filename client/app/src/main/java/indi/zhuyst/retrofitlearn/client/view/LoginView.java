package indi.zhuyst.retrofitlearn.client.view;

import indi.zhuyst.retrofitlearn.client.pojo.Msg;

/**
 * Created by zhuyst on 2017/7/10.
 */

public interface LoginView extends BaseView{
    void onLoginResult(Msg msg);

    void startLogin();
}
