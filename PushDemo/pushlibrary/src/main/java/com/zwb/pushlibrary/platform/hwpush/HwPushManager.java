package com.zwb.pushlibrary.platform.hwpush;

import android.content.Context;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;
import com.zwb.pushlibrary.IPushManager;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.BaseMessageReceiver;

import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 16:30.
 * @ desc: 华为推送服务
 **/
public class HwPushManager implements IPushManager {

    @Override
    public boolean register(final Context context) {
        new Thread() {
            @Override
            public void run() {
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.platform = PlatformType.HUAWEI;
                commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
                try {
                    // read from agconnect-services.json
                    String appId = AGConnectServicesConfig.fromContext(context).getString("client/app_id");
                    String token = HmsInstanceId.getInstance(context).getToken(appId, "HCM");
                    commandMessage.resultCode = PushCommandMessage.COMMAND_SUCCESS;
                    commandMessage.token = token;
                } catch (ApiException e) {
                    commandMessage.reason = e.toString();
                }
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            }
        }.start();
        return true;
    }

    @Override
    public void setTag(Context context, String tag) {
        try {
            HmsMessaging.getInstance(context).subscribe(tag)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                        }
                    });
        } catch (Exception e) {
        }
    }

    @Override
    public void unsetTag(Context context, String tag) {
        try {
            HmsMessaging.getInstance(context).unsubscribe(tag)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                        }
                    });
        } catch (Exception e) {
        }
    }

    @Override
    public void onResume(Context context) {

    }

    @Override
    public void onPause(Context context) {

    }

    @Override
    public void setDefaultPushNotificationBuilder(Context context, int id) {

    }

    @Override
    public void setChannel(Context context, String channel) {

    }

    @Override
    public void pausePush(Context context) {

    }

    @Override
    public void resumePush(Context context) {

    }

    @Override
    public void setAlias(Context context, String alias) {

    }

    @Override
    public void unsetAlias(Context context, String alias) {

    }

    @Override
    public void setUserAccount(Context context, String account) {

    }

    @Override
    public void unsetUserAccount(Context context, String account) {

    }

    @Override
    public void setTag(Context context, Set<String> tags) {
        for (String tag : tags) {
            setTag(context, tag);
        }
    }

    @Override
    public void clearAllTags(Context context) {

    }

    @Override
    public void unsetTag(Context context, Set<String> tags) {
        for (String tag : tags) {
            unsetTag(context, tag);
        }
    }

    @Override
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {

    }
}
