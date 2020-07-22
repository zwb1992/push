package com.zwb.pushlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.bean.PushMessage;

import java.io.Serializable;
import java.util.List;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 10:26.
 * @ desc: 结束个平台推送消息处理   各方法允许在主线程
 **/
public abstract class BaseMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            int msgType = intent.getIntExtra(MsgConstants.MSG_TYPE, MsgConstants.passThroughMessage);
            Serializable message = intent.getSerializableExtra(MsgConstants.MSG_OBJECT);
            switch (msgType) {
                case MsgConstants.passThroughMessage:
                    PushMessage msg1 = (PushMessage) message;
                    onReceivePassThroughMessage(context, msg1);
                    break;
                case MsgConstants.notificationMessageArrived:
                    PushMessage msg2 = (PushMessage) message;
                    onNotificationMessageArrived(context, msg2);
                    break;
                case MsgConstants.notificationMessageClicked:
                    PushMessage msg3 = (PushMessage) message;
                    onNotificationMessageClicked(context, msg3);
                    break;
                case MsgConstants.commandMessage:
                    PushCommandMessage msg4 = (PushCommandMessage) message;
                    onCommandResult(context, msg4);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 透传消息
     *
     * @param context
     * @param message
     */
    protected abstract void onReceivePassThroughMessage(Context context, PushMessage message);

    /**
     * 通知消息
     *
     * @param context
     * @param message
     */
    protected abstract void onNotificationMessageArrived(Context context, PushMessage message);

    /**
     * 通知点击的消息
     *
     * @param context
     * @param message
     */
    protected abstract void onNotificationMessageClicked(Context context, PushMessage message);

    /**
     * 指令消息--设置各种别名，tag的结果
     *
     * @param context
     * @param message
     */
    protected abstract void onCommandResult(Context context, PushCommandMessage message);


    public static void sendMessage(Context context, Serializable msg, int type) {
        if (context == null || msg == null) {
            return;
        }
        try {
            List<ResolveInfo> list = context.getPackageManager().queryBroadcastReceivers(new Intent(MsgConstants.ACTION_MSG_RECEIVER), 0);
            if (!list.isEmpty()) {
                Intent intent = new Intent();
                intent.putExtra(MsgConstants.MSG_TYPE, type);
                intent.putExtra(MsgConstants.MSG_OBJECT, msg);
                intent.setAction(MsgConstants.ACTION_MSG_RECEIVER);
                intent.setClass(context, Class.forName(list.get(0).activityInfo.name));
                context.sendBroadcast(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
