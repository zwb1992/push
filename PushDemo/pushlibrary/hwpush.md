华为推送

配置 agconnect-services.json 进入app
在app的build文件头部加上apply plugin: 'com.huawei.agconnect'

权限:



服务（library控制）:

DemoHmsMessageService类（类名由开发者自定义，此处仅是举例）继承于HmsMessageService类并实现其中的方法。
<service
    android:name=".DemoHmsMessageService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.huawei.push.action.MESSAGING_EVENT"/>
    </intent-filter>
</service>

消息接收：

