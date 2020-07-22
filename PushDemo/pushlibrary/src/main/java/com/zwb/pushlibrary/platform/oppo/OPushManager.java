package com.zwb.pushlibrary.platform.oppo;

import android.content.Context;
import android.text.TextUtils;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.IPushManager;
import com.zwb.pushlibrary.MetaUtil;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;

import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/15 11:25.
 * @ desc: oppo推送相关
 **/
public class OPushManager implements IPushManager {

    @Override
    public boolean register(final Context context) {
        try {
            HeytapPushManager.init(context, true);
            if (HeytapPushManager.isSupportPush()) {
                String appSecret = MetaUtil.getValue(context, MsgConstants.META_KEY_OPPO_APPSECRET);
                String appKey = MetaUtil.getValue(context, MsgConstants.META_KEY_OPPO_APPKEY);
                if (!TextUtils.isEmpty(appSecret) && !TextUtils.isEmpty(appKey)) {
                    HeytapPushManager.register(context, appKey, appSecret, new ICallBackResultService() {
                        @Override
                        public void onRegister(int code, String s) {
                            PushCommandMessage commandMessage = new PushCommandMessage();
                            commandMessage.platform = PlatformType.OPPO;
                            commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
                            commandMessage.resultCode = code;
                            if (code == 0) {
                                commandMessage.token = s;
                            } else {
                                commandMessage.reason = s;
                            }
                            BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
                        }

                        @Override
                        public void onUnRegister(int i) {

                        }

                        @Override
                        public void onSetPushTime(int i, String s) {

                        }

                        @Override
                        public void onGetPushStatus(int i, int i1) {

                        }

                        @Override
                        public void onGetNotificationStatus(int i, int i1) {

                        }
                    });
                    HeytapPushManager.requestNotificationPermission();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        HeytapPushManager.pausePush();
    }

    @Override
    public void resumePush(Context context) {
        HeytapPushManager.resumePush();
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
    public void setTag(Context context, String tag) {

    }

    @Override
    public void clearAllTags(Context context) {

    }

    @Override
    public void setTag(Context context, Set<String> tags) {

    }

    @Override
    public void unsetTag(Context context, String tag) {

    }

    @Override
    public void unsetTag(Context context, Set<String> tags) {

    }

    @Override
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {
    }
}
