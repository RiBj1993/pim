/**
 * Copyright 2014 Rahul Parsani
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mobile.esprit.pim.googleplus;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.squareup.leakcanary.LeakCanary;

import java.util.List;
import java.util.concurrent.ExecutionException;

import mobile.esprit.pim.AsyncTaskClass.GetAllHives;
import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.R;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.ui.Utils;

public class MainActivity extends ActionBarActivity {

    // Bitmaps will only be decoded once and stored in this cache
    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(4);
    private Handler handler;
    private Runnable handlerTask;
    private TextView tempview, textupdate;

    List<Hive> hives;
    private Hive hive;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maingoogle);

        if (LeakCanary.isInAnalyzerProcess(getApplication ())) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.

            getSupportActionBar().setHomeButtonEnabled(false);

        // Used to get the dimensions of the image views to load scaled down bitmaps
        final View parent = findViewById(R.id.parent);
        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.removeOnGlobalLayoutListenerCompat(parent, this);
                setImageBitmap((ImageView) findViewById(R.id.card_photo_1).findViewById(R.id.photo), R.drawable.photo1);
                setImageBitmap((ImageView) findViewById(R.id.card_photo_2).findViewById(R.id.photo), R.drawable.photo2);
                setImageBitmap((ImageView) findViewById(R.id.card_photo_3).findViewById(R.id.photo), R.drawable.photo3);
                setImageBitmap((ImageView) findViewById(R.id.card_photo_4).findViewById(R.id.photo), R.drawable.photo4);
            }
        });
        Bundle b = getIntent().getExtras();
        value = -1; // or other values



        if (b != null)
            value = b.getInt("key");
        hive = new Hive();

        tempview = (TextView) findViewById(R.id.tv_cuvvrrent_temp);
        textupdate = (TextView) findViewById(R.id.tv_last_updated);

        StartTimer()    ;

     return;
}
        LeakCanary.install((Application) getApplication ());
                // Normal app init code...



                }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Utils.hasLollipop()) {
            // The activity transition animates the clicked image alpha to zero, reset that value when
            // you come back to this activity
            ViewHelper.setAlpha(findViewById(R.id.card_photo_1), 1.0f);
            ViewHelper.setAlpha(findViewById(R.id.card_photo_2), 1.0f);
            ViewHelper.setAlpha(findViewById(R.id.card_photo_3), 1.0f);
            ViewHelper.setAlpha(findViewById(R.id.card_photo_4), 1.0f);
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
        sPhotoCache.put(resId, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * When the user clicks a thumbnail, bundle up information about it and launch the
     * details activity.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void showPhoto(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DetailActivity.class);
        Bundle b = getIntent().getExtras();
int value;
        if (b != null)
        { value = b.getInt("key");

        // Interesting data to pass across are the thumbnail location, the map parameters,
        // the picture title & description, and the key to retrieve the bitmap from the cache
        int resId = 0;
        switch (view.getId()) {
            case R.id.show_photo_1:
                intent.putExtra("lat",mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude())
                        .putExtra("lng",mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude())
                        .putExtra("zoom", 14.0f)
                        .putExtra("title", "Pacifica Pier")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.photo4);
                resId = R.id.card_photo_1;
                break;
            case R.id.show_photo_2:
                intent.putExtra("lat", 37.73284)
                        .putExtra("lng", -122.503065)
                        .putExtra("zoom", 15.0f)
                        .putExtra("title", "Pink Flamingo")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.photo4);
                resId = R.id.card_photo_2;
                break;
            case R.id.show_photo_3:
                intent.putExtra("lat", 36.861897)
                        .putExtra("lng", -111.374438)
                        .putExtra("zoom", 11.0f)
                        .putExtra("title", "Antelope Canyon")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.photo4);
                resId = R.id.card_photo_3;
                break;
            case R.id.show_photo_4:
                intent.putExtra("lat", 36.596125)
                        .putExtra("lng", -118.1604282)
                        .putExtra("zoom", 9.0f)
                        .putExtra("title", "Lone Pine")
                        .putExtra("description", getResources().getText(R.string.lorem))
                        .putExtra("photo", R.drawable.photo4);
                resId = R.id.card_photo_4;
                break;
        }

        if (Utils.hasLollipop()) {
            startActivityLollipop(view, intent);
        } else {
            startActivityGingerBread(view, intent, resId);
        }}
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
    public   void StartTimer(){
        handler = new Handler();

        handlerTask = new Runnable()
        {
            @Override
            public void run() {
                AsyncTask gethivesTask = new GetAllHives(getApplicationContext()).execute();
                System.out.println( "^^^^");

                try {
                    gethivesTask.get();
                    hives = GetAllHives.hives;
                    System.out.println(hives.get(value).getTemperature() );
                    tempview.setText(hives.get(value).getTemperature() + "°C");
                    textupdate.setText(hives.get(value).getReference() + "");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                handler.postDelayed(handlerTask, 1000);
            }




        };
        handlerTask.run();
    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
        if(SessionManager. mp !=  null ){if(SessionManager. mp.isPlaying()) {SessionManager. mp.stop();}}
        Intent intent = new Intent(getApplicationContext(), mobile.esprit.pim.hexagonerecycle.MainActivity.class);
        startActivity(intent);

    }
}