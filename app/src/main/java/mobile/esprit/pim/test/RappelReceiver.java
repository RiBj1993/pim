package mobile.esprit.pim.test;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import mobile.esprit.pim.R;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.hexagonerecycle.MainActivity;


public class RappelReceiver extends BroadcastReceiver {

    public RappelReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder.setContentTitle("Alerte Ruche")
                .setContentText("la temperature de la ruche " + SessionManager.ReferenceNotif + " a atteint\n un niveau critque de "
                        + SessionManager.temperatureNotif)
                .setTicker(" temperature tres elever ruch Alert!")
                .setSmallIcon(R.drawable.bee)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }
}
