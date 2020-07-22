package com.zwb.pushlibrary.platform.jpush;

import android.content.Context;

import androidx.annotation.DrawableRes;

import com.zwb.pushlibrary.BaseMessageReceiver;
import com.zwb.pushlibrary.BuildConfig;
import com.zwb.pushlibrary.IPushManager;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


/**
 * @author wangchengm
 * 极光推送工具类
 */
public class JPushManager implements IPushManager {

    @Override
    public boolean register(Context context) {
        try {
            JPushInterface.init(context);
            JPushInterface.stopCrashHandler(context);
            // 设置开启日志,发布时请关闭日志
            JPushInterface.setDebugMode(BuildConfig.DEBUG);
            String regId = JPushInterface.getRegistrationID(context);
            PushCommandMessage commandMessage = new PushCommandMessage();
            commandMessage.platform = PlatformType.JIGUANG;
            commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
            commandMessage.resultCode = PushCommandMessage.COMMAND_SUCCESS;
            commandMessage.token = regId;
            BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onResume(Context context) {
        JPushInterface.onResume(context);
    }

    @Override
    public void onPause(Context context) {
        JPushInterface.onPause(context);
    }

    @Override
    public void pausePush(Context context) {
        JPushInterface.stopPush(context);
    }

    @Override
    public void resumePush(Context context) {
        if (JPushInterface.isPushStopped(context)) {
            JPushInterface.resumePush(context);
        }
    }

    @Override
    public void setDefaultPushNotificationBuilder(Context context, @DrawableRes int id) {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
        // 指定定制的 Notification Layout
        builder.statusBarDrawable = id;
        // 指定最顶层状态栏小图标
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }

    @Override
    public void setChannel(Context context, String channel) {
        JPushInterface.setChannel(context, channel);
    }

    @Override
    public void setTag(Context context, Set<String> tags) {
        JPushInterface.setTags(context, 0, tags);
    }

    @Override
    public void setTag(Context context, String tag) {
        Set<String> set = new HashSet<>();
        set.add(tag);
        JPushInterface.addTags(context, 0, set);
    }

    @Override
    public void unsetTag(Context context, String tag) {
        Set<String> set = new HashSet<>();
        set.add(tag);
        unsetTag(context, set);
    }

    @Override
    public void unsetTag(Context context, Set<String> tags) {
        JPushInterface.deleteTags(context, 0, tags);
    }

    @Override
    public void clearAllTags(Context context) {
        JPushInterface.cleanTags(context, 0);
    }

    @Override
    public void setAlias(Context context, String alias) {
        JPushInterface.setAlias(context, 0, alias);
    }

    @Override
    public void unsetAlias(Context context, String alias) {
        JPushInterface.deleteAlias(context, 0);
    }

    @Override
    public void setUserAccount(Context context, String account) {

    }

    @Override
    public void unsetUserAccount(Context context, String account) {

    }

    @Override
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {

    }
}