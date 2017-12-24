package mobile.esprit.pim;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import mobile.esprit.pim.AsyncTaskClass.PerformBackgroundTask;



public class MyService extends Service {
    NotificationManager notif;
    Notification notify;
    @Override
    public IBinder onBind(Intent intent) {
        // This is triggered by the binder action intent.
        // You can return your binder here if you want...
        return null;
    }


    @Override
    public void onCreate() {
        System.out.println( "*************************************");   }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // This is triggered by the service action start intent
        callAsynchronousTask();

        return super.onStartCommand(intent, flags, startId);
    }


    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            PerformBackgroundTask performBackgroundTask =   new PerformBackgroundTask(getBaseContext()) ;
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            performBackgroundTask.execute();


                            try {
                                performBackgroundTask.get();
                                 System.out.println(  "*******************************************" );
                                System.out.println(  "*******************************************" );


                                System.out.println(  performBackgroundTask.hives.get(2).getTemperature()+"°C" );



                                String tittle="attetion";
                                String subject="temperature>>>25 ";
                                String body="consultee la ruche ";

                                notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                                notify=new Notification.Builder
                                        (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                                        setContentTitle(subject).setSmallIcon(R.drawable.bee).build();
                                notify.flags |= Notification.FLAG_AUTO_CANCEL;

                                notif.notify(0, notify);
                                System.out.println(  "je suis notifier*" );

                                System.out.println(  "*******************************************" );
                                System.out.println(  "*******************************************" );

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }





                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 50000); //execute in every 50000 ms
    }

}