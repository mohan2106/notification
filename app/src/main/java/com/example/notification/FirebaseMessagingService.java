package com.example.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String messgaetitle=remoteMessage.getNotification().getTitle();
        String messageBody=remoteMessage.getNotification().getBody();

        String click_action =remoteMessage.getNotification().getClickAction();
        String mess=remoteMessage.getData().get("message");
        String dat=remoteMessage.getData().get("from_id");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this,getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(messgaetitle)
                .setContentText(messageBody);

        Intent intent=new Intent(click_action);
        intent.putExtra("message",mess);
        intent.putExtra("from_id",dat);
        PendingIntent result=
                PendingIntent.getActivities(
                        this,
                        0,
                        new Intent[]{intent},
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        int mNotificationId = (int)System.currentTimeMillis();
        NotificationManager mNotifyMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId,mBuilder.build());
    }
}
