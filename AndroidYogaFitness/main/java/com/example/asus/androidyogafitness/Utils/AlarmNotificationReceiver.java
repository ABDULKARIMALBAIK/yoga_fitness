package com.example.asus.androidyogafitness.Utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import  android.support.v4.app.NotificationCompat;

import com.example.asus.androidyogafitness.ExercisesActivity;
import com.example.asus.androidyogafitness.R;
import com.example.asus.androidyogafitness.ViewExerciseActiviy;

import java.util.Random;

public class AlarmNotificationReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            sendNotificationAPI26();
        else
            sendNotification();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotificationAPI26() {

        PendingIntent pendingIntent;
        NotificationHelper helper;
        Notification.Builder builder;

        Intent intent = new Intent(context , ExercisesActivity.class);
        //intent.putExtra(Common.PHONE_TEXT , Common.currentUser.getPhone());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(context , 0 , intent ,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        helper = new NotificationHelper(context);
        builder = helper.getYogaFitnessChannelNotification(pendingIntent, defaultSoundUri);

        helper.getManager().notify(new Random().nextInt() , builder.build());

    }


    private void sendNotification() {

        Intent intent = new Intent(context , ExercisesActivity.class);
        //intent.putExtra(Common.PHONE_TEXT , Common.currentUser.getPhone());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0 , intent ,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_yoga)
                .setContentTitle("It is time of exercise !")
                .setContentText("You should do some trainings for building your body")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
    }

}
