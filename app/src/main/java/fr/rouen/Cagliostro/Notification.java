package fr.rouen.Cagliostro;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;



public class Notification extends BroadcastReceiver {

    private static final String TAG = "nicomo";

    // Notification Text Elements
    private final CharSequence tickerText = "La comtesse de Cagliostro";
    private final CharSequence contentTitle = "La comtesse de Cagliostro";
    private final CharSequence contentText = "De nouveaux épisodes sont parus.";

    // members for the preferences and the current episode
    private SharedPreferences prefs;
    private Boolean delayedEps;
    private int lastEp;

    // Notification Action Elements
    private static final int MY_NOTIFICATION_ID = 1;
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "entering Notification onReceive");


        // set time to 6AM
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);


        // get preferences
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        delayedEps = prefs.getBoolean("delayedEps", true);
        lastEp = prefs.getInt("lastEp", 0);


        // if the episodes pref is still on
        if (delayedEps) {

            // create intent
            mNotificationIntent = new Intent(context, EpisodeActivity.class);
            // the episode to be opened passed as Extra
            mNotificationIntent.putExtra("epid", lastEp);
            // wrap intent in a pending intent
            mContentIntent = PendingIntent.getActivity(context, 0,
                    mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

            // Build the notification
            android.app.Notification.Builder notificationBuilder = new android.app.Notification.Builder(
                    context).setTicker(tickerText)
                    .setSmallIcon(android.R.drawable.stat_sys_warning)
                    .setAutoCancel(true).setContentTitle(contentTitle)
                    .setContentText(contentText).setContentIntent(mContentIntent);

            // Pass the Notification to the NotificationManager and notify
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(MY_NOTIFICATION_ID,
                    notificationBuilder.build());

            Log.i(TAG,"Sending notification"); // TODO remove

        }

    }

}