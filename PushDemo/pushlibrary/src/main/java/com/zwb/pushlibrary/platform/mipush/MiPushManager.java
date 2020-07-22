package com.zwb.pushlibrary.platform.mipush;

import android.content.Context;
import android.text.TextUtils;

import com.xiaomi.mipush.sdk.MiPushClient;
import com.zwb.pushlibrary.IPushManager;
import com.zwb.pushlibrary.MetaUtil;
import com.zwb.pushlibrary.MsgConstants;

import java.util.List;
import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/10 17:03.
 * @ desc: 小米推送工具
 **/
public class MiPushManager implements IPushManager {

    @Override
    public boolean register(Context context) {
        String appId = MetaUtil.getValue(context, MsgConstants.META_KEY_XIAOMI_APPID);
        String appKey = MetaUtil.getValue(context, MsgConstants.META_KEY_XIAOMI_APPKEY);
        if (!TextUtils.isEmpty(appId) && !TextUtils.isEmpty(appKey)) {
            MiPushClient.registerPush(context, appId, appKey);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void pausePush(Context context) {
        MiPushClient.pausePush(context, null);
    }

    @Override
    public void resumePush(Context context) {
        MiPushClient.resumePush(context, null);
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
    public void setAlias(Context context, String alias) {
        MiPushClient.setAlias(context, alias, null);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        MiPushClient.unsetAlias(context, alias, null);
    }

    @Override
    public void setUserAccount(Context context, String account) {
        MiPushClient.setUserAccount(context, account, null);
    }

    @Override
    public void unsetUserAccount(Context context, String account) {
        MiPushClient.unsetUserAccount(context, account, null);
    }

    @Override
    public void setTag(Context context, String tag) {
        MiPushClient.subscribe(context, tag, null);
    }

    @Override
    public void setTag(Context context, Set<String> tags) {
        // 去重
        List<String> list = MiPushClient.getAllTopic(context);
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
        MiPushClient.unsubscribe(context, tag, null);
    }

    @Override
    public void unsetTag(Context context, Set<String> tags) {
        for (String tag : tags) {
            unsetTag(context, tag);
        }
    }

    @Override
    public void clearAllTags(Context context) {
        List<String> tags = MiPushClient.getAllTopic(context);
        for (String tag : tags) {
            unsetTag(context, tag);
        }
    }

    @Override
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {
        MiPushClient.setAcceptTime(context, startHour, startMin, endHour, endMin, null);
    }
}
