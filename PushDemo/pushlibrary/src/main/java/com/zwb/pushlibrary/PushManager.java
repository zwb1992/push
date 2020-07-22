package com.zwb.pushlibrary;

import android.content.Context;

import androidx.annotation.DrawableRes;

import com.zwb.pushlibrary.platform.hwpush.HwPushManager;
import com.zwb.pushlibrary.platform.jpush.JPushManager;
import com.zwb.pushlibrary.platform.mipush.MiPushManager;
import com.zwb.pushlibrary.platform.oppo.OPushManager;
import com.zwb.pushlibrary.platform.vivo.VivoPushManager;

import java.util.Set;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/10 16:58.
 * @ desc:
 **/
public class PushManager implements IPushManager {
    private static volatile PushManager INSTANCE = null;

    private PushManager() {
    }

    public static PushManager create() {
        if (INSTANCE == null) {
            synchronized (PushManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PushManager();
                }
            }
        }
        return INSTANCE;
    }

    private IPushManager pushManager;

    @PlatformType
    public String getPlatform() {
        if (pushManager instanceof MiPushManager) {
            return PlatformType.XIAOMI;
        } else if (pushManager instanceof HwPushManager) {
            return PlatformType.HUAWEI;
        } else if (pushManager instanceof OPushManager) {
            return PlatformType.OPPO;
        } else if (pushManager instanceof VivoPushManager) {
            return PlatformType.VIVO;
        } else if (pushManager instanceof JPushManager) {
            return PlatformType.JIGUANG;
        } else {
            return PlatformType.NONE;
        }
    }

    @Override
    public boolean register(Context context) {
        return register(context, PlatformType.AUTO);
    }

    // 注册推送--指定注册类型
    public boolean register(Context context, @PlatformType String platformType) {
        boolean registerFlag = false;
        if (PlatformType.XIAOMI.equals(platformType)) {
            pushManager = new MiPushManager();
            registerFlag = pushManager.register(context);
        } else if (PlatformType.HUAWEI.equals(platformType)) {
            pushManager = new HwPushManager();
            registerFlag = pushManager.register(context);
        } else if (PlatformType.OPPO.equals(platformType)) {
            pushManager = new OPushManager();
            registerFlag = pushManager.register(context);
        } else if (PlatformType.JIGUANG.equals(platformType)) {
            pushManager = new JPushManager();
            registerFlag = pushManager.register(context);
        } else if (PlatformType.AUTO.equals(platformType)) {
            if (Rom.isMiui()) {
                pushManager = new MiPushManager();
                registerFlag = pushManager.register(context);
            } else if (Rom.isEmui()) {
                pushManager = new HwPushManager();
                registerFlag = pushManager.register(context);
            } else if (Rom.isOppo()) {
                pushManager = new OPushManager();
                registerFlag = pushManager.register(context);
            } else if (Rom.isVivo()) {
                pushManager = new VivoPushManager();
                registerFlag = pushManager.register(context);
            }
            // 前面的注册都失败了，走默认的极光推送
            if (!registerFlag) {
                pushManager = new JPushManager();
                registerFlag = pushManager.register(context);
            }
        }
        if (!registerFlag) {
            pushManager = null;
        }
        return registerFlag;
    }


    public void onResume(Context context) {
        if (pushManager != null) {
            pushManager.onResume(context);
        }
    }

    public void onPause(Context context) {
        if (pushManager != null) {
            pushManager.onPause(context);
        }
    }

    public void setDefaultPushNotificationBuilder(Context context, @DrawableRes int id) {
        if (pushManager != null) {
            pushManager.setDefaultPushNotificationBuilder(context, id);
        }
    }

    public void setChannel(Context context, String channel) {
        if (pushManager != null) {
            pushManager.setChannel(context, channel);
        }
    }


    // 暂停推送
    public void pausePush(Context context) {
        if (pushManager != null) {
            pushManager.pausePush(context);
        }
    }

    // 恢复推送
    public void resumePush(Context context) {
        if (pushManager != null) {
            pushManager.resumePush(context);
        }
    }

    // 设置别名
    public void setAlias(Context context, String alias) {
        if (pushManager != null) {
            pushManager.setAlias(context, alias);
        }
    }

    // 撤销别名
    public void unsetAlias(Context context, String alias) {
        if (pushManager != null) {
            pushManager.unsetAlias(context, alias);
        }
    }

    // 设置帐号
    public void setUserAccount(Context context, String account) {
        if (pushManager != null) {
            pushManager.setUserAccount(context, account);
        }
    }

    // 撤销帐号
    public void unsetUserAccount(Context context, String account) {
        if (pushManager != null) {
            pushManager.unsetUserAccount(context, account);
        }
    }

    // 设置标签
    public void setTag(Context context, String tag) {
        if (pushManager != null) {
            pushManager.setTag(context, tag);
        }
    }

    // 设置标签
    public void setTag(Context context, Set<String> tags) {
        if (pushManager != null) {
            pushManager.setTag(context, tags);
        }
    }

    @Override
    public void clearAllTags(Context context) {
        if (pushManager != null) {
            pushManager.clearAllTags(context);
        }
    }

    // 撤销标签
    public void unsetTag(Context context, String tag) {
        if (pushManager != null) {
            pushManager.unsetTag(context, tag);
        }
    }

    // 撤销标签
    public void unsetTag(Context context, Set<String> tags) {
        if (pushManager != null) {
            pushManager.unsetTag(context, tags);
        }
    }

    // 设置接收消息时间
    public void setAcceptTime(Context context, int startHour, int startMin, int endHour, int endMin) {
        if (pushManager != null) {
            pushManager.setAcceptTime(context, startHour, startMin, endHour, endMin);
        }
    }

}
