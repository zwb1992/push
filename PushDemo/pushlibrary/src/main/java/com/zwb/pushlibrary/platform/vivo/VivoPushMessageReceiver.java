package com.zwb.pushlibrary.platform.vivo;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;
import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.bean.PushMessage;

public class VivoPushMessageReceiver extends OpenClientPushMessageReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage msg) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.msgId = msg.getMsgId() + "";
        pushMessage.title = msg.getTitle();
        pushMessage.content = msg.getContent();
        if (msg.getParams() != null) {
            pushMessage.extra = JSON.toJSONString(msg.getParams());
        }
        pushMessage.platform = PlatformType.VIVO;
        BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.notificationMessageClicked);
    }

    @Override
    public void onReceiveRegId(Context context, String regId) {
        PushCommandMessage commandMessage = new PushCommandMessage();
        commandMessage.platform = PlatformType.VIVO;
        commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
        commandMessage.resultCode = PushCommandMessage.COMMAND_SUCCESS;
        commandMessage.token = regId;
        BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
    }
}
