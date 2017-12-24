package mobile.esprit.pim.hexagonerecycle;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.abed.hexagonrecyclerview.view.HexagonRecyclerView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mobile.esprit.pim.AsyncTaskClass.GetAllHives;
import mobile.esprit.pim.AsyncTaskClass.PerformBackgroundTask;
import mobile.esprit.pim.Course.ActivityCart;
import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.R;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.googleplus.DetailActivity;
import mobile.esprit.pim.googleplus.DetailActivityL;
import mobile.esprit.pim.timeline.ListDemoActivity;
import mobile.esprit.pim.ui.Utils;
import mobile.esprit.pim.webview.WebViewActivity;
import mobile.esprit.pim.wowview.one;


public class MainActivity extends AppCompatActivity implements ImagesAdapter.ViewHolderClicks {
    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(4);
    public static ArrayList<Hive> hives;


    private BoomMenuButton bmb;
    private GoogleApiClient client;
    private List<String> images;
    private int[] tableimage;
    private ImagesAdapter imagesAdapter;
    private HexagonRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainrecycle);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent i = new Intent(getApplicationContext(), AjouterRucheActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i);


                    }
                });

     /*
*/
        SimpleCircleButton.Builder builder1 = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {


                        Intent i = new Intent(getApplicationContext(), ActivityCart.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        getApplicationContext().startActivity(i);
                    }


                });
        builder.normalImageRes(R.drawable.log_off);



        SimpleCircleButton.Builder builder3 = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {


                        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        getApplicationContext().startActivity(i);
                    }


                });
        SimpleCircleButton.Builder builder4 = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {


                        Intent i = new Intent(getApplicationContext(), ListDemoActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        getApplicationContext().startActivity(i);


                    }


                });
        SimpleCircleButton.Builder builder5 = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        SharedPreferences shpr = getApplicationContext().getSharedPreferences("connexion", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shpr.edit();
                        editor.putString("login", "a");
                        editor.putString("mdp", "b");

                        editor.commit();

                        SessionManager.logoutUser( getApplicationContext());
                        Intent i = new Intent(getApplicationContext(), one.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i);


                    }


                });
        SimpleCircleButton.Builder builder6 = new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {

                        if (SessionManager.mp != null) {
                            SessionManager.mp.stop();
                            SessionManager. mp.release();
                            SessionManager.mp = null;

                        }
                        SessionManager. mp = MediaPlayer.create(MainActivity.this, R.raw.audio2);
                        SessionManager. mp.start();



                    }


                });




        builder.normalImageRes(R.drawable.bee);
        builder1.normalImageRes(R.drawable.cour);
        builder3.normalImageRes(R.drawable.camera);
        builder4.normalImageRes(R.drawable.calender);
        builder5.normalImageRes(R.drawable.log_off);
        builder6.normalImageRes(R.drawable.high);


        bmb.addBuilder(builder);
        bmb.addBuilder(builder1);
        bmb.addBuilder(builder3);
        bmb.addBuilder(builder4);
        bmb.addBuilder(builder5);
        bmb.addBuilder(builder6);


        tableimage = new int[]{
                R.drawable.r1,
                R.drawable.r2,
                R.drawable.r3,
                R.drawable.r4,
                R.drawable.r5,
                R.drawable.r6,
                R.drawable.r7,
                R.drawable.r8,
                R.drawable.r9,
                R.drawable.r10,
                R.drawable.r11,
                R.drawable.r12
        };
        bmb.setBoomEnum(BoomEnum.HORIZONTAL_THROW_1);

        hives = new ArrayList<Hive>();
       // loadViews();
        StartTimer();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        final View parent = findViewById(R.id.parentt);
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListenerCompat(parent, this);
                setImageBitmap((ImageView) findViewById(R.id.card_photo_11).findViewById(R.id.photo), R.drawable.photo1);

            }
        });

        callAsynchronousTask();
        notifayed();



    }
    private Handler handler;
    private Runnable handlerTask;

  public   void StartTimer(){
        handler = new Handler();
      final boolean stop = false;

      handlerTask = new Runnable()
        {
            @Override
            public void run() {
                // do something
               // textView.setText("some text");
                loadViews();


                handler.postDelayed(handlerTask, 1000);
            }




        };
        handlerTask.run();
    }



    private void loadViews() {
        LinkedList p = new LinkedList<String>();

        imagesAdapter = new ImagesAdapter(p, this);
        recyclerView = (HexagonRecyclerView) findViewById(R.id.rvItems);
        AsyncTask gethivesTask = new GetAllHives(getBaseContext()).execute();

        try {
            gethivesTask.get();
            hives = GetAllHives.hives;


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        images = new LinkedList<>();

        System.out.println("size " + hives.size());
        for (int i = 0; i < hives.size(); i++) {

            images.add("" + tableimage[i]);

        }




        imagesAdapter.updateList(images);
        recyclerView.setAdapter(imagesAdapter);


    }



    public void InternetAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Etat de connexion")
                .setMessage("Connexion Indisponible !\nVerifier votre connexion !")
                .setNegativeButton("Reglages", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));

                    }
                })
                .setPositiveButton("Retour", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    @Override
    protected void onResume() {
        super.onResume();

        if (!Utils.hasLollipop()) {
            // The activity transition animates the clicked image alpha to zero, reset that value when
            // you come back to this activity
            ViewHelper.setAlpha(findViewById(R.id.card_photo_11), 1.0f);

        }




    }

    /**
     * Loads drawables into the given image view efficiently. Uses the method described
     * <a href="http://developer.android.com/training/displaying-bitmaps/load-bitmap.html">here.</a>
     *
     * @param imageView
     * @param resId     Resource identifier of the drawable to load from memory
     */
    private void setImageBitmap(ImageView imageView, int resId) {
        Bitmap bitmap = Utils.decodeSampledBitmapFromResource(getResources(),
                resId, imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
        System.out.println();
        sPhotoCache.put(resId, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onStorySelected(View view, int position, String image) {


        int resId = 0;

        Intent intent = new Intent();
        intent.setClass(this, DetailActivity.class);
        intent.putExtra("lat", 37.6329946)
                .putExtra("lng", -122.4938344)
                .putExtra("zoom", 14.0f)
                .putExtra("title", "Pacifica Pier")
                .putExtra("description", getResources().getText(R.string.lorem))
                .putExtra("photo", R.drawable.photo4);
        resId = R.id.card_photo_11;
        Bundle b = new Bundle();

        b.putInt("key", position); //Your id
        intent.putExtras(b);


        if (Utils.hasLollipop()) {
            startActivityLollipop(view, intent);
        } else {
            startActivityGingerBread(view, intent, resId);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startActivityLollipop(View view, Intent intent) {
        intent.setClass(this, DetailActivityL.class);
        ImageView hero = (ImageView) ((View) view.getParent()).findViewById(R.id.photo);
        ((ViewGroup) hero.getParent()).setTransitionGroup(false);

        sPhotoCache.put(intent.getIntExtra("photo", -1),
                ((BitmapDrawable) hero.getDrawable()).getBitmap());

        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this, hero, "photo_hero");
        startActivity(intent, options.toBundle());
    }

    private void startActivityGingerBread(View view, Intent intent, int resId) {
        int[] screenLocation = new int[2];
        view.getLocationOnScreen(screenLocation);
        intent.
                putExtra("left", screenLocation[0]).
                putExtra("top", screenLocation[1]).
                putExtra("width", view.getWidth()).
                putExtra("height", view.getHeight());

        startActivity(intent);

        // Override transitions: we don't want the normal window animation in addition to our
        // custom one
        overridePendingTransition(0, 0);

        // The detail activity handles the enter and exit animations. Both animations involve a
        // ghost view animating into its final or initial position respectively. Since the detail
        // activity starts translucent, the clicked view needs to be invisible in order for the
        // animation to look correct.
        ViewPropertyAnimator.animate(findViewById(resId)).alpha(0.0f);
    }

    public void notifayed() {


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        System.out.println("mee");
        alarmManager.set(AlarmManager.RTC_WAKEUP, 0, broadcast);


    }


    public void callAsynchronousTask() {


        if (isOnline()) {
            final Handler handler = new Handler();
            Timer timer = new Timer();
            TimerTask doAsynchronousTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                PerformBackgroundTask performBackgroundTask = new PerformBackgroundTask(getBaseContext());
                                // PerformBackgroundTask this class is the class that extends AsynchTask
                                performBackgroundTask.execute();


                                try {
                                    performBackgroundTask.get();
                                    System.out.println("*******************************************");
                                    System.out.println("*******************************************");


                                 //   System.out.println(performBackgroundTask.hives.get(2).getTemperature() + "°C");
                                    for (Hive h : hives) {
                                        if (h.getTemperature() > 25) {
                                            notifayed();
                                            SessionManager.temperatureNotif = h.getTemperature() + "";
                                            SessionManager.ReferenceNotif = h.getReference() + "";
                                        }
                                    }


                                    System.out.println("*******************************************");
                                    System.out.println("*******************************************");

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
        timer.schedule(doAsynchronousTask, 0, 3600000); //execute in every 50000 ms

        } else {


            InternetAlert();
        }

    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}