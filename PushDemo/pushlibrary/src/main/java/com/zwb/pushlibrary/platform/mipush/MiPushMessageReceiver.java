package com.zwb.pushlibrary.platform.mipush;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import com.zwb.pushlibrary.MsgConstants;
import com.zwb.pushlibrary.PlatformType;
import com.zwb.pushlibrary.bean.PushCommandMessage;
import com.zwb.pushlibrary.bean.PushMessage;
import com.zwb.pushlibrary.BaseMessageReceiver;

import java.util.List;

/**
 * 1、PushMessageReceiver 是个抽象类，该类继承了 BroadcastReceiver。<br/>
 * 2、需要将自定义的 DemoMessageReceiver 注册在 AndroidManifest.xml 文件中：
 * <pre>
 * {@code
 *  <receiver
 *      android:name="com.xiaomi.mipushdemo.DemoMessageReceiver"
 *      android:exported="true">
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
 *      </intent-filter>
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
 *      </intent-filter>
 *      <intent-filter>
 *          <action android:name="com.xiaomi.mipush.ERROR" />
 *      </intent-filter>
 *  </receiver>
 *  }</pre>
 * 3、DemoMessageReceiver 的 onReceivePassThroughMessage 方法用来接收服务器向客户端发送的透传消息。<br/>
 * 4、DemoMessageReceiver 的 onNotificationMessageClicked 方法用来接收服务器向客户端发送的通知消息，
 * 这个回调方法会在用户手动点击通知后触发。<br/>
 * 5、DemoMessageReceiver 的 onNotificationMessageArrived 方法用来接收服务器向客户端发送的通知消息，
 * 这个回调方法是在通知消息到达客户端时触发。另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数。<br/>
 * 6、DemoMessageReceiver 的 onCommandResult 方法用来接收客户端向服务器发送命令后的响应结果。<br/>
 * 7、DemoMessageReceiver 的 onReceiveRegisterResult 方法用来接收客户端向服务器发送注册命令后的响应结果。<br/>
 * 8、以上这些方法运行在非 UI 线程中。
 *
 * @author mayixiang
 */
public class MiPushMessageReceiver extends PushMessageReceiver {

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.msgId = message.getMessageId();
        pushMessage.title = message.getTitle();
        pushMessage.content = message.getDescription();
        if (message.getExtra() != null) {
            pushMessage.extra = JSON.toJSONString(message.getExtra());
        }
        pushMessage.platform = PlatformType.XIAOMI;
        BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.passThroughMessage);
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.msgId = message.getMessageId();
        pushMessage.title = message.getTitle();
        pushMessage.content = message.getDescription();
        if (message.getExtra() != null) {
            pushMessage.extra = JSON.toJSONString(message.getExtra());
        }
        pushMessage.platform = PlatformType.XIAOMI;
        BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.notificationMessageClicked);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        PushMessage pushMessage = new PushMessage();
        pushMessage.msgId = message.getMessageId();
        pushMessage.title = message.getTitle();
        pushMessage.content = message.getDescription();
        if (message.getExtra() != null) {
            pushMessage.extra = JSON.toJSONString(message.getExtra());
        }
        pushMessage.platform = PlatformType.XIAOMI;
        BaseMessageReceiver.sendMessage(context, pushMessage, MsgConstants.notificationMessageArrived);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);

        PushCommandMessage commandMessage = new PushCommandMessage();
        commandMessage.cmdArg1 = cmdArg1;
        commandMessage.cmdArg2 = cmdArg2;
        commandMessage.resultCode = message.getResultCode();
        commandMessage.reason = message.getReason();
        commandMessage.originCommand = command;
        commandMessage.platform = PlatformType.XIAOMI;
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
            commandMessage.token = cmdArg1;
        } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_SET_ALIAS;
        } else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_UNSET_ALIAS;
        } else if (MiPushClient.COMMAND_SET_ACCOUNT.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_SET_ACCOUNT;
        } else if (MiPushClient.COMMAND_UNSET_ACCOUNT.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_UNSET_ACCOUNT;
        } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_SUBSCRIBE_TOPIC;
        } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_UNSUBSCRIBE_TOPIC;
        } else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
            commandMessage.command = PushCommandMessage.COMMAND_SET_ACCEPT_TIME;
        } else {
            commandMessage.command = PushCommandMessage.UNKNOWN;
        }
        BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        List<String> arguments = message.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        PushCommandMessage commandMessage = new PushCommandMessage();
        commandMessage.cmdArg1 = cmdArg1;
        commandMessage.resultCode = message.getResultCode();
        commandMessage.reason = message.getReason();
        commandMessage.originCommand = command;
        commandMessage.platform = PlatformType.XIAOMI;
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            commandMessage.token = cmdArg1;
            commandMessage.command = PushCommandMessage.COMMAND_REGISTER;
        } else {
            commandMessage.command = PushCommandMessage.UNKNOWN;
        }
        BaseMessageReceiver.sendMessage(context, commandMessage, MsgConstants.commandMessage);
    }

    @Override
    public void onRequirePermissions(Context context, String[] permissions) {
        super.onRequirePermissions(context, permissions);
//        if (Build.VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
//            Intent intent = new Intent();
//            intent.putExtra("permissions", permissions);
//            intent.setComponent(new ComponentName(context.getPackageName(), PermissionActivity.class.getCanonicalName()));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(intent);
//        }
    }
}
