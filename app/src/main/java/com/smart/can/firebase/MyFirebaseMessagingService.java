package com.smart.can.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.smart.can.Activity.SplashScreen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.

        Log.e(TAG, "Notification : " + remoteMessage.getData());
        Log.e(TAG, "Notification : " + remoteMessage.getData().get("title"));
        Log.e(TAG, "Notification : " + remoteMessage.getData().get("message"));


        Intent resultIntent = new Intent(getApplicationContext(), SplashScreen.class);

        showNotificationMessage(getApplicationContext(), remoteMessage.getData().get("title"),
                remoteMessage.getData().get("message"),
                currentApiPostDate(), resultIntent);

    }


    private void showNotificationMessage(Context context, String title, String message,
                                         String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }


    public static String currentApiPostDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);

        return df.format(c);
    }
}
