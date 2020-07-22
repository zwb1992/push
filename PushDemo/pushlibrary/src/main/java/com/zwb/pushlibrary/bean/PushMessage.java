package com.zwb.pushlibrary.bean;

import androidx.annotation.Keep;

import com.zwb.pushlibrary.PlatformType;

import java.io.Serializable;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 10:01.
 * @ desc: 各平台推送的消息汇总--普通消息
 **/
@Keep
public class PushMessage implements Serializable {
    private static final long serialVersionUID = 1985312436654299854L;
    public String msgId;    // 消息id
    public String content;  // 通知显示的内容
    public String title;    // 标题
    public String extra;    // 额外的信息
    public @PlatformType
    String platform; // 平台


    @Override
    public String toString() {
        return "PushMessage{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
