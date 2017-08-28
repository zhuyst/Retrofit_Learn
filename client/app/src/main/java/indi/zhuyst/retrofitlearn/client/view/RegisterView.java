package indi.zhuyst.retrofitlearn.client.view;

import indi.zhuyst.retrofitlearn.client.pojo.Msg;

/**
 * Created by zhuyst on 2017/7/11.
 */

public interface RegisterView extends BaseView{
    void onRegisterResult(Msg msg);

    void startRegister();
}
