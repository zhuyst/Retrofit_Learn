package indi.zhuyst.retrofitlearn.client.view;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by zhuyst on 2017/7/15.
 */

interface BaseView {
    <T> LifecycleTransformer<T> bindToLifecycle();

    <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event);

    SharedPreferences getSharedPreferences(String name, int mode);
}
