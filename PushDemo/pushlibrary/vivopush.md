vivo推送

权限:



服务（library控制）:

<!--Vivo Push需要配置的service、activity-->
<service
            android:name="com.vivo.push.sdk.service.CommandClientService"
        android:exported="true"/>

<!--Vivo Push开放平台中应用的appid 和api key-->
<meta-data
        android:name="com.vivo.push.api_key"
        android:value="xxxxxxxx"/>

<meta-data
        android:name="com.vivo.push.app_id"
        android:value="xxxx"/>



消息接收：
在当前工程中新建一个类 PushMessageReceiverImpl继承OpenClientPushMessageReceiver 并重载实现相关方法。并在当前工程的AndroidManifest.xml文件中，添加自定义Receiver信息，代码如下：

<!-- push应用定义消息receiver声明 -->

<receiver     android:name="xxx.xxx.xxx.PushMessageReceiverImpl" >
           <intent-filter>
             <!-- 接收push消息 -->
             <action   android:name="com.vivo.pushclient.action.RECEIVE"   />
           </intent-filter>
</receiver>