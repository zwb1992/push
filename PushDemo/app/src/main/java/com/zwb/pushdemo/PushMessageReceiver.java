package com.zwb.pushdemo;

import android.content.Context;
import android.util.Log;

import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.bean.PushMessage;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 10:10.
 * @ desc: 统一的消息处理广播 需要在应用中注册
 **/
public class PushMessageReceiver extends BaseMessageReceiver {

    @Override
    protected void onReceivePassThroughMessage(Context context, PushMessage message) {
        Log.e("zwb", "=====onReceivePassThroughMessage=====" + message + "   " + Thread.currentThread().getName());
    }

    @Override
    protected void onNotificationMessageArrived(Context context, PushMessage message) {
        Log.e("zwb", "====onNotificationMessageArrived======" + message + "   " + Thread.currentThread().getName());

    }

    @Override
    protected void onNotificationMessageClicked(Context context, PushMessage message) {
        Log.e("zwb", "=====onNotificationMessageClicked=====" + message + "   " + Thread.currentThread().getName());

    }

    @Override
    protected void onCommandResult(Context context, PushCommandMessage message) {
        Log.e("zwb", "=====onCommandResult=====" + message + "   " + Thread.currentThread().getName());

    }
}
