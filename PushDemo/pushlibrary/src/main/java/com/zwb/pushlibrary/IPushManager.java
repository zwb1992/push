package com.zwb.pushlibrary;

import android.content.Context;

import androidx.annotation.DrawableRes;

import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/15 14:08.
 * @ desc:
 **/
public interface IPushManager {

    /**
     * 注册
     *
     * @param context
     * @return
     */
    boolean register(Context context);

    void onResume(Context context);

    void onPause(Context context);

    /**
     * 设置默认的通知样式  极光
     *
     * @param context
     * @param id
     */
    void setDefaultPushNotificationBuilder(Context context, @DrawableRes int id);

    /**
     * 设置渠道
     *
     * @param context
     * @param channel
     */
    void setChannel(Context context, String channel);


    /**
     * 暂停推送
     *
     * @param context
     */
    void pausePush(Context context);

    /**
     * 恢复推送
     */
    void resumePush(Context context);

    /**
     * 设置别名
     *
     * @param context
     * @param alias
     */
    void setAlias(Context context, String alias);

    /**
     * 撤销别名
     *
     * @param context
     * @param alias
     */
    void unsetAlias(Context context, String alias);

    /**
     * 设置帐号
     *
     * @param context
     * @param account
     */
    void setUserAccount(Context context, String account);

    /**
     * 撤销帐号
     *
     * @param context
     * @param account
     */
    void unsetUserAccount(Context context, String account);

    /**
     * 设置标签
     *
     * @param context
     * @param tag
     */
    void setTag(Context context, String tag);

    /**
     * 设置标签
     *
     * @param context
     * @param tags
     */
    void setTag(Context context, Set<String> tags);

    /**
     * 清除所有标签
     * @param context
     */
    void clearAllTags(Context context);

    /**
     * 撤销标签
     *
     * @param context
     * @param tag
     */
    void unsetTag(Context context, String tag);

    /**
     * 撤销标签
     *
     * @param context
     * @param tags
     */
    void unsetTag(Context context, Set<String> tags);

    /**
     * @param context
     * @param startHour
     * @param startMin
     * @param endHour
     * @param endMin
     */
    void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin);
}
