package com.zwb.pushlibrary.platform.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.bean.PushMessage;
import com.zwb.pushlibrary.BaseMessageReceiver;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            Bundle bundle = intent.getExtras();
//            Log.e(TAG, "[JpushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//                Log.e(TAG, "[JpushReceiver] 接收到的注册id - " + regId);
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.resultCode = 0;
                commandMessage.platform = PlatformType.JIGUANG;
                commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
                commandMessage.token = getValue(bundle, JPushInterface.EXTRA_REGISTRATION_ID);
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                Log.e(TAG, "[JpushReceiver] 接收到推送下来的自定义消息: " + extra);
                PushMessage pushMessage = new PushMessage();
                pushMessage.extra = getValue(bundle, JPushInterface.EXTRA_MESSAGE);
                pushMessage.title = getValue(bundle, JPushInterface.EXTRA_NOTIFICATION_TITLE);
                pushMessage.content = getValue(bundle, JPushInterface.EXTRA_ALERT);
                pushMessage.msgId = getValue(bundle, JPushInterface.EXTRA_MSG_ID);
                pushMessage.platform = PlatformType.JIGUANG;
                BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.passThroughMessage);
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                Log.e(TAG, "[JpushReceiver] 接收到推送下来的通知:" + extra);
                PushMessage pushMessage = new PushMessage();
                pushMessage.extra = getValue(bundle, JPushInterface.EXTRA_EXTRA);
                pushMessage.title = getValue(bundle, JPushInterface.EXTRA_NOTIFICATION_TITLE);
                pushMessage.content = getValue(bundle, JPushInterface.EXTRA_ALERT);
                pushMessage.msgId = getValue(bundle, JPushInterface.EXTRA_MSG_ID);
                pushMessage.platform = PlatformType.JIGUANG;
                BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.notificationMessageArrived);
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//                Log.e(TAG, "[JpushReceiver] 用户点击打开了通知:" + extra);
                PushMessage pushMessage = new PushMessage();
                pushMessage.extra = getValue(bundle, JPushInterface.EXTRA_EXTRA);
                pushMessage.title = getValue(bundle, JPushInterface.EXTRA_NOTIFICATION_TITLE);
                pushMessage.content = getValue(bundle, JPushInterface.EXTRA_ALERT);
                pushMessage.msgId = getValue(bundle, JPushInterface.EXTRA_MSG_ID);
                pushMessage.platform = PlatformType.JIGUANG;
                BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.notificationMessageClicked);
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//                Log.e(TAG, "[JpushReceiver]用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//                Log.e(TAG, "[JpushReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
//                Log.e(TAG, "[JpushReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static String getValue(Bundle bundle, String key) {
        if (bundle != null) {
            return bundle.getString(key);
        }
        return "";
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        if (null == bundle) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getBoolean(key));
            } else {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getString(key));
            }
        }
        return sb.toString();
    }
}