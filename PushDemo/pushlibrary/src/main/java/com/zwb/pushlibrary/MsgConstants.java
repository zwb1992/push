package com.zwb.pushlibrary;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 10:18.
 * @ desc:
 **/
public interface MsgConstants {
    String MSG_TYPE = "MSG_TYPE";
    String MSG_OBJECT = "MSG_OBJECT";

    String META_PREFIX = "PUSH-";                                           // meta 前缀
    String META_KEY_XIAOMI_APPID = "XIAOMI_APPID";                          // 小米推送的appId
    String META_KEY_XIAOMI_APPKEY = "XIAOMI_APPKEY";                        // 小米推送的appKey
    String META_KEY_OPPO_APPSECRET = "OPPO_APPSECRET";                      // OPPO推送的appId
    String META_KEY_OPPO_APPKEY = "OPPO_APPKEY";                            // OPPO推送的appKey
    String META_KEY_VIVO_APPSECRET = "VIVO_APPSECRET";                      // VIVO推送的appSecret
    String META_KEY_VIVO_APPKEY = "com.vivo.push.api_key";                  // VIVO推送的appKey
    String META_KEY_VIVO_APPID = "VIVO_APPID";                              // VIVO推送的appId
    String META_KEY_JPUSH_CHANNEL = "JPUSH_CHANNEL";                        // 极光推送的channel
    String META_KEY_JPUSH_APPKEY = "JPUSH_APPKEY";                          // 极光推送的appKey

    String ACTION_MSG_RECEIVER = "com.push.ACTION_MSG_RECEIVER";            // 消息接受者的action

    int passThroughMessage = 0;                        // 透传消息
    int notificationMessageArrived = 1;         // 通知到达
    int notificationMessageClicked = 2;         // 通知点击
    int commandMessage = 3;                     // 指令消息
}
