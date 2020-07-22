package com.zwb.pushlibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import static com.zwb.pushlibrary.MsgConstants.META_PREFIX;

/**
 * @ author : zhouweibin
 * @ time: 2020/7/13 19:42.
 * @ desc:
 **/
public class MetaUtil {

    /**
     * 纯数字的会被过滤 读出的为null  所以meta-data在配置的时候 需要截断之前预留的字符串
     *
     * @param context
     * @param key
     * @return
     */
    public static String getValue(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String result = appInfo.metaData.getString(key);
            if (!TextUtils.isEmpty(result) && result.startsWith(META_PREFIX)) {
                result = result.replace(META_PREFIX, "");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
