配置：
manifestPlaceholders = [
                JPUSH_PKGNAME   : deps.ism.applicationId,
                JPUSH_APPKEY    : deps.ism.JPUSH_APPKEY,
                JPUSH_CHANNEL   : "developer-default",
                XIAOMI_APPID    : deps.ism.XIAOMI_APPID,
                XIAOMI_APPKEY   : deps.ism.XIAOMI_APPKEY,
                OPPO_APPSECRET  : deps.ism.OPPO_APPSECRET, // OPPO推送appSecret
                OPPO_APPKEY     : deps.ism.OPPO_APPKEY, // OPPO推送appKey
                VIVO_APPID      : deps.ism.VIVO_APPID, // VIVO推送 appId
                VIVO_APPKEY     : deps.ism.VIVO_APPKEY, // VIVO推送 appKey
                VIVO_APPSECRET  : deps.ism.VIVO_APPSECRET, // VIVO推送 appSecret
        ]


消息接收： 继承BaseMessageReceiver 实现相关方法
在AndroidManifest文件中配置action：com.push.ACTION_MSG_RECEIVER

例如：
        <receiver
            android:name=".push.PushMessageReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="com.push.ACTION_MSG_RECEIVER" />
            </intent-filter>
        </receiver>



添加相关平台的权限（公共部分，特有部分在library中声明）
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.GET_TASKS" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.SYSTEM_OVERLAY_WINDOW" />

针对华为推送：
1.需要在APP build.gradle文件顶部加入apply plugin: 'com.huawei.agco
2.工程build.gradle文件中加上classpath 'com.huawei.agconnect:agcp:1.2.1.301'
maven {url 'http://developer.huawei.com/repo/'}
3.下载agconnect-services.json文件  放在app目录下（与build.gradle平级）


其中
极光  小米 vivo（最低版本android6.0）推送支持获取通知的点击事件，app自己处理参数再跳转
华为，oppo 通知的action动作完全由服务器控制-启动到目标界面之后再处理参数


华为传参：intent://goto?#Intent;scheme=tisamanapp;launchFlags=0x4000000;S.comic_id=12345;S.page=dir;end