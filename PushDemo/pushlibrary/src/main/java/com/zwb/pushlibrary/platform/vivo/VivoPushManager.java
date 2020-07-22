package com.zwb.pushlibrary.platform.vivo;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.ups.TokenResult;
import com.vivo.push.ups.UPSRegisterCallback;
import com.vivo.push.ups.VUpsManager;
import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.IPushManager;
import com.zwb.pushlibrary.MetaUtil;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;

import java.util.List;
import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/15 11:25.
 * @ desc: vivo推送相关
 **/
public class VivoPushManager implements IPushManager {

    @Override
    public boolean register(final Context context) {
        //vivo推送服务SDK支持的最低android版本为Android 6.0。
        if (Build.VERSION.SDK_INT >= 23 && PushClient.getInstance(context).isSupport()) {
            String appSecret = MetaUtil.getValue(context, MsgConstants.META_KEY_VIVO_APPSECRET);
            String appKey = MetaUtil.getValue(context, MsgConstants.META_KEY_VIVO_APPKEY);
            String appId = MetaUtil.getValue(context, MsgConstants.META_KEY_VIVO_APPID);
            if (!TextUtils.isEmpty(appSecret) && !TextUtils.isEmpty(appKey) && !TextUtils.isEmpty(appId)) {
                PushClient.getInstance(context).initialize();
                VUpsManager.getInstance().registerToken(context, appId, appKey, appSecret, new UPSRegisterCallback() {
                    @Override
                    public void onResult(TokenResult tokenResult) {
                        PushCommandMessage commandMessage = new PushCommandMessage();
                        commandMessage.platform = PlatformType.VIVO;
                        commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
                        commandMessage.resultCode = tokenResult.getReturnCode();
                        if (tokenResult.getReturnCode() == 0) {
                            commandMessage.token = tokenResult.getToken();
                        }
                        BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
                    }
                });
                return true;
            }
        }
        return false;
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
    public void setAlias(final Context context, String alias) {
        PushClient.getInstance(context).bindAlias(alias, new IPushActionListener() {

            @Override
            public void onStateChanged(int state) {
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.platform = PlatformType.VIVO;
                commandMessage.command = PushCommandMessage.COMMAND_SET_ALIAS;
                commandMessage.resultCode = state;
                commandMessage.cmdArg1 = alias;
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            }
        });
    }

    @Override
    public void unsetAlias(final Context context, String alias) {
        PushClient.getInstance(context).unBindAlias(alias, new IPushActionListener() {

            @Override
            public void onStateChanged(int state) {
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.platform = PlatformType.VIVO;
                commandMessage.command = PushCommandMessage.COMMAND_UNSET_ALIAS;
                commandMessage.resultCode = state;
                commandMessage.cmdArg1 = alias;
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            }
        });
    }

    @Override
    public void setUserAccount(Context context, String account) {

    }

    @Override
    public void unsetUserAccount(Context context, String account) {

    }

    @Override
    public void setTag(Context context, String tag) {
        PushClient.getInstance(context).setTopic(tag, new IPushActionListener() {

            @Override
            public void onStateChanged(int state) {
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.platform = PlatformType.VIVO;
                commandMessage.command = PushCommandMessage.COMMAND_SUBSCRIBE_TOPIC;
                commandMessage.resultCode = state;
                commandMessage.cmdArg1 = tag;
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            }
        });
    }

    @Override
    public void setTag(Context context, Set<String> tags) {
        List<String> list = PushClient.getInstance(context).getTopics();
        for (String tag:list){
            if(tags.contains(tag)){
                tags.remove(tag);
            }else {
                unsetTag(context,tag);
            }
        }
        if(!tags.isEmpty()) {
            for (String tag : tags) {
                setTag(context, tag);
            }
        }
    }

    @Override
    public void unsetTag(Context context, String tag) {
        PushClient.getInstance(context).delTopic(tag, new IPushActionListener() {

            @Override
            public void onStateChanged(int state) {
                PushCommandMessage commandMessage = new PushCommandMessage();
                commandMessage.platform = PlatformType.VIVO;
                commandMessage.command = PushCommandMessage.COMMAND_UNSUBSCRIBE_TOPIC;
                commandMessage.resultCode = state;
                commandMessage.cmdArg1 = tag;
                BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            }
        });
    }

    @Override
    public void unsetTag(Context context, Set<String> tags) {
        for (String tag : tags) {
            unsetTag(context, tag);
        }
    }

    @Override
    public void clearAllTags(Context context) {
        List<String> tags = PushClient.getInstance(context).getTopics();
        for (String tag : tags) {
            unsetTag(context, tag);
        }
    }

    @Override
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {
    }
}
