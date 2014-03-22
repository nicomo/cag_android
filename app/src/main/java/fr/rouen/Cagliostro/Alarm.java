package fr.rouen.Cagliostro;


import android.app.AlarmManager;
//import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends BroadcastReceiver {

    private static final String TAG = "nicomo";

    // members for the preferences and the current episode
    private SharedPreferences prefs;
    private long timestamp;
    private Boolean delayedEps;
    private int lastEp; // last ep read
    private int lastEpNotified; // last episode to have been notified
    private int epToBeNotified; // farthest episode available;

    // AlarmManager and intents to be used in the alarm
    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            Log.i(TAG, "Service loaded at start");

            // get preferences
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            timestamp = prefs.getLong("timestamp", 0);
            delayedEps = prefs.getBoolean("delayedEps", true);
            lastEp = prefs.getInt("lastEp", 0);
            lastEpNotified = prefs.getInt("lastEpNotified", 0);

            // compare timestamp with now to know if we have new episodes
            Date now = new Date();
            final double minElapsed = ( now.getTime() - timestamp ) / 60000.0;
            if (minElapsed > 52) {
                epToBeNotified = 52;
            } else {
                epToBeNotified = (int) minElapsed;
            }

            Log.i(TAG, "minElapsed is " + (int) minElapsed);
            Log.i(TAG, "lastep is " + lastEp);
            Log.i(TAG, "delayedEps is " + delayedEps);
            Log.i(TAG, "lastEpNotified is " + lastEpNotified);
            Log.i(TAG, "epToBeNotified is " + epToBeNotified);

            // if the episodes pref is on,
            // this is not the 1st episode
            // there is new episodes to display
            // those episodes have not been notified yet
            if (delayedEps && (lastEp > 0) && (lastEp < (int) minElapsed) && (lastEpNotified < epToBeNotified)) {

                // set time to 6AM
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 6);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                // Get the AlarmManager Service
                mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                // Create PendingIntent to start the AlarmNotificationReceiver
                mNotificationReceiverIntent = new Intent(context, Notification.class);
                mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(context, 1, mNotificationReceiverIntent, 0);

                // set alarm
                mAlarmManager.set(AlarmManager.RTC,
                        calendar.getTimeInMillis(),
                        mNotificationReceiverPendingIntent);

                // update lastEpNotified
                SharedPreferences.Editor editorPref = prefs.edit();
                editorPref.putInt("lastEpNotified", epToBeNotified);
                editorPref.commit();

            }

        }

    }

}


