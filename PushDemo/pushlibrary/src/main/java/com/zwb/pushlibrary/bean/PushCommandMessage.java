package com.zwb.pushlibrary.bean;

import androidx.annotation.Keep;

import com.zwb.pushlibrary.PlatformType;

import java.io.Serializable;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 10:41.
 * @ desc: 各平台推送的消息汇总--指令消息
 **/
@Keep
public class PushCommandMessage implements Serializable {

    private static final long serialVersionUID = 2400721871221097517L;
    public static int COMMAND_SUCCESS = 0;                       // 指令发送成功


    public static long UNKNOWN = -1;                             // 未知
    public static long COMMAND_REGISTER = 0;                     // 注册
    public static long COMMAND_SET_ALIAS = 1;                    // 设置别名
    public static long COMMAND_UNSET_ALIAS = 3;                  // 撤销别名
    public static long COMMAND_SET_ACCOUNT = 4;                  // 设置账户
    public static long COMMAND_UNSET_ACCOUNT = 5;                // 撤销账户
    public static long COMMAND_SUBSCRIBE_TOPIC = 6;              // 设置标签
    public static long COMMAND_UNSUBSCRIBE_TOPIC = 7;            // 撤销标签
    public static long COMMAND_SET_ACCEPT_TIME = 8;              // 设置接收时间

    public @PlatformType String platform;                        // 平台
    public String originCommand;                                 // 原始指令
    public long command;                                         // 指令id
    public String reason;                                        // 原因
    public long resultCode = -1;                                 // 结果code
    public String cmdArg1;                                       // 执行参数1
    public String cmdArg2;                                       // 执行参数2
    public String token;                                         // token


    @Override
    public String toString() {
        return "PushCommandMessage{" +
                "originCommand='" + originCommand + '\'' +
                ", platform=" + platform +
                ", command=" + command +
                ", reason='" + reason + '\'' +
                ", resultCode=" + resultCode +
                ", cmdArg1='" + cmdArg1 + '\'' +
                ", cmdArg2='" + cmdArg2 + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
