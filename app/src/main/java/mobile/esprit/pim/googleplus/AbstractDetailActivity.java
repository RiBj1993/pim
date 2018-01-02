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

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewPropertyAnimator;

import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.R;
import mobile.esprit.pim.USER.SessionManager;
import mobile.esprit.pim.ui.AnimatedPathView;
import mobile.esprit.pim.ui.AnimatorListener;
import mobile.esprit.pim.ui.Utils;
import tyrantgit.explosionfield.ExplosionField;


public abstract class AbstractDetailActivity extends FragmentActivity {

    public ImageView hero;
    public Bitmap photo;
    public View container;
    private ExplosionField mExplosionField;
    private TextView tempview, textupdate;
    private Hive hive;
    int value = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SessionManager.mp = MediaPlayer.create(getApplicationContext(), R.raw.audio3);
        hero = (ImageView) findViewById(R.id.photo);
        container = findViewById(R.id.container);

        photo = setupPhoto(getIntent().getIntExtra("photo", R.drawable.photo1));

        colorize(photo);

        setupMap();
        setupText();

        postCreate();

        setupEnterAnimation();
        Bundle b = getIntent().getExtras();
        // or other values
        if (b != null)
            value = b.getInt("key");
        hive = new Hive();
        hive = mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value);
        System.out.println("here " + hive);
        tempview = (TextView) findViewById(R.id.tv_cuvvrrent_temp);
        textupdate = (TextView) findViewById(R.id.tv_last_updated);

        tempview.setText(hive.getTemperature() + "°C");
        textupdate.setText("Ruche N°" + hive.getReference() + "");
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.root));
    }

    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            if (root.getId() == R.id.imageVew) {
                root.setClickable(true);
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mExplosionField.explode(v);
                        v.setOnClickListener(null);
                    }
                });

                if (root.getId() == R.id.eau) {
                    root.setClickable(true);
                    root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mExplosionField.explode(v);
                            v.setOnClickListener(null);
                        }
                    });

                }

                if (root.getId() == R.id.imagemap) {
                    root.setClickable(true);
                    root.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            Intent intent = new Intent();
                                            intent.setClass(v.getContext(), DetailActivity.class);


                                            intent.putExtra("lat", mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude())
                                                    .putExtra("lng", mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude())
                                                    .putExtra("zoom", 14.0f)
                                                    .putExtra("title", "Pacifica Pier")
                                                    .putExtra("description", getResources().getText(R.string.lorem))
                                                    .putExtra("photo", R.drawable.photo1);


                                        }
                                    }


                            );


                            mExplosionField.explode(v);

                        }
                    });
                }


            }
        }}


    public abstract void postCreate();

    @Override
    public void onBackPressed() {
        setupExitAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupText() {
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(getIntent().getStringExtra("title"));

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(getIntent().getStringExtra("description"));
    }

    private void setupMap() {
        final GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        Bundle b = getIntent().getExtras();
        // or other values
        if (b != null)
        { value = b.getInt("key");
        double lat = getIntent().getDoubleExtra("lat",  mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude());
        double lng = getIntent().getDoubleExtra("lng", mobile.esprit.pim.hexagonerecycle.MainActivity.hives.get(value).getLatitude());
        float zoom = getIntent().getFloatExtra("zoom", 15);

        LatLng position = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, zoom));
        map.addMarker(new MarkerOptions().position(position));

        // We need the snapshot of the map to prepare the shader for the circular reveal.
        // So the map is visible on activity start and then once the snapshot is taken, quickly hidden.
        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                map.snapshot(new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(Bitmap bitmap) {
                        mapLoaded(bitmap);
                    }
                });
            }
        });}
    }

    public abstract void mapLoaded(Bitmap bitmap);

    private void colorize(Bitmap photo) {


        Bitmap bitmap = Utils.decodeSampledBitmapFromResource(getResources(),
                R.drawable.photo1, 5, 5);

        System.out.println("String.valueOf(R.drawable.photo1)" + R.id.card_photo_1);
        Palette palette = Palette.generate(bitmap);
        applyPalette(palette);
    }

    public void applyPalette(Palette palette) {
        Resources res = getResources();

        container.setBackgroundColor(palette.getDarkMutedColor(res.getColor(R.color.default_dark_muted)));
        ImageView tpho = (ImageView) findViewById(R.id.photo);
        android.view.ViewGroup.LayoutParams layoutParams = tpho.getLayoutParams();
        layoutParams.height = 400;
        tpho.setLayoutParams(layoutParams);

        tpho.setImageResource(R.drawable.photo1);
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setTextColor(palette.getVibrantColor(res.getColor(R.color.default_vibrant)));

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setTextColor(palette.getLightVibrantColor(res.getColor(R.color.default_light_vibrant)));

        colorButton(R.id.info_button, palette.getDarkMutedColor(res.getColor(R.color.default_dark_muted)),
                palette.getDarkVibrantColor(res.getColor(R.color.default_dark_vibrant)));
        colorButton(R.id.star_button, palette.getMutedColor(res.getColor(R.color.default_muted)),
                palette.getVibrantColor(res.getColor(R.color.default_vibrant)));

        AnimatedPathView star = (AnimatedPathView) findViewById(R.id.star_container);
        star.setFillColor(palette.getVibrantColor(R.color.default_vibrant));
        star.setStrokeColor(palette.getLightVibrantColor(res.getColor(R.color.default_light_vibrant)));
    }

    public abstract void colorButton(int id, int bgColor, int tintColor);

    private Bitmap setupPhoto(int resource) {
        Bitmap bitmap = MainActivity.sPhotoCache.get(resource);
        hero.setImageBitmap(bitmap);
        return bitmap;
    }

    public void showStar(View view) {
        toggleStarView();
    }


    private void toggleStarView() {
        final AnimatedPathView starContainer = (AnimatedPathView) findViewById(R.id.star_container);
        if (SessionManager.mp != null) {
            SessionManager.mp.stop();
            SessionManager. mp.release();
            SessionManager.mp = null;
        }
        SessionManager. mp = MediaPlayer.create(AbstractDetailActivity.this, R.raw.audio3);
        SessionManager. mp.start();

        if (starContainer.getVisibility() == View.INVISIBLE) {
            ViewPropertyAnimator.animate(hero).alpha(0.2f);
            ViewPropertyAnimator.animate(starContainer).alpha(1);
            starContainer.setVisibility(View.VISIBLE);
            starContainer.reveal();


        } else {
            ViewPropertyAnimator.animate(hero).alpha(1);
            ViewPropertyAnimator.animate(starContainer).alpha(0).setListener(new AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    starContainer.setVisibility(View.INVISIBLE);
                    ViewPropertyAnimator.animate(starContainer).setListener(null);
                }
            });
        }
    }

    public void showInformation(View view) {
        toggleInformationView(view);
    }

    public abstract void toggleInformationView(View view);

    public abstract void setupEnterAnimation();

    public abstract void setupExitAnimation();
}