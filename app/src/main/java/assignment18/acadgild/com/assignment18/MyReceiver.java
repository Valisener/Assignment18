package assignment18.acadgild.com.assignment18;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {

//Override the on receive
    @Override
    public void onReceive(Context context, Intent intent) {
        //create intent filter
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //make intent to find battery status
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        //variable to get the level from battery status intent
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        //variable to get the scale from battery status intent
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //This tells the battery percentage of what the battery is currently at.
        float batteryPct = (level / (float)scale) *100;
        //Setup the notification that displays the battery percentage
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Battery")
                .setContentText("Battery Level Remaining: " + (int) batteryPct +"%")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //Create the notification manager that handles the notifications
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Display the notification
        mNotificationManager.notify(1, mBuilder.build());
    }
}