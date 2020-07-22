oppo推送

权限（配置在app工程）:
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.SYSTEM_OVERLAY_WINDOW" />



    这两个权限 配置在library当中
   <uses-permission android:name="com.coloros.mcs.permission.RECIEVE_MCS_MESSAGE" />
   <uses-permission android:name="com.heytap.mcs.permission.RECIEVE_MCS_MESSAGE"/>


服务（配置在library工程）:

<service
            android:name="com.heytap.mcssdk.PushService"
            android:permission="com.coloros.mcs.permission.SEND_MCS_MESSAGE">
            <intent-filter>
                <action android:name="com.coloros.mcs.action.RECEIVE_MCS_MESSAGE" />
            </intent-filter>
        </service>

        <service
            android:name="com.heytap.mcssdk.AppPushService"
            android:permission="com.heytap.mcs.permission.SEND_MCS_MESSAGE">
            <intent-filter>
                <action android:name="com.heytap.mcs.action.RECEIVE_MCS_MESSAGE" />
            </intent-filter>
        </service>

消息接收：

目前oppo只有通知无透传消息 且只能通过通知内置的action去打开指定界面 无法获取通知的点击事件  完全由服务器去控制