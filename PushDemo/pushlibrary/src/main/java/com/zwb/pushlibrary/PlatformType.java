package com.zwb.pushlibrary;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/14 14:25.
 * @ desc: 推送平台类型
 **/
@StringDef({PlatformType.NONE, PlatformType.AUTO, PlatformType.XIAOMI, PlatformType.JIGUANG,
        PlatformType.HUAWEI, PlatformType.OPPO, PlatformType.VIVO})
@Retention(RetentionPolicy.SOURCE)
public @interface PlatformType {
    String NONE = "NONE";                   // 无推送平台
    String AUTO = "AUTO";                   // 自动选择平台推送
    String XIAOMI = "XIAOMI";               // 注册小米推送
    String JIGUANG = "JIGUANG";             // 注册极光推送
    String HUAWEI = "HUAWEI";               // 注册华为推送
    String OPPO = "OPPO";                   // 注册OPPO推送
    String VIVO = "VIVO";                   // 注册VIVO推送
}
