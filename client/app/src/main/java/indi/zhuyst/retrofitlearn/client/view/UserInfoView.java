package indi.zhuyst.retrofitlearn.client.view;

import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;

/**
 * Created by zhuyst on 2017/7/11.
 */

public interface UserInfoView extends BaseView{
    void onQueryResult(Msg<User> msg);

    void onUpdateResult(Msg msg);

    void startUpdate();
}
