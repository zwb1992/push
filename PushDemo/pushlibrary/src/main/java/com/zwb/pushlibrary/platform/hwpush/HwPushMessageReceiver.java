package com.zwb.pushlibrary.platform.hwpush;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 15:53.
 * @ desc: 华为推送的通知消息  action动作由服务器指定
 **/
public class HwPushMessageReceiver extends HmsMessageService {

    /**
     * 透传消息
     *
     * @param remoteMessage
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        Log.e("zwb", "HwPushMessageReceiver onMessageReceived == " + remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
//        Log.e("zwb", "HwPushMessageReceiver onDeletedMessages == ");
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
//        Log.e("zwb", "HwPushMessageReceiver onMessageSent == " + s);
    }

    @Override
    public void onMessageDelivered(String s, Exception e) {
        super.onMessageDelivered(s, e);
//        Log.e("zwb", "HwPushMessageReceiver onMessageDelivered == " + s + "      " + e);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
//        Log.e("zwb", "HwPushMessageReceiver onSendError == " + s + "      " + e);
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        PushCommandMessage commandMessage = new PushCommandMessage();
        commandMessage.platform = PlatformType.HUAWEI;
        commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
        commandMessage.resultCode = PushCommandMessage.COMMAND_SUCCESS;
        commandMessage.token = token;
        BaseMessageReceiver.sendMessage(getApplicationContext(), commandMessage, MsgConstants.commandMessage);
    }

    @Override
    public void onTokenError(Exception e) {
        super.onTokenError(e);
    }

}
