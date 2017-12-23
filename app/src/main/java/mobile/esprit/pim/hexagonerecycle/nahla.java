package mobile.esprit.pim.hexagonerecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abed.hexagonrecyclerview.view.HexagonRecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import mobile.esprit.pim.AsyncTaskClass.GetAllHives;
import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.R;
//import mobile.esprit.pim.googleplus.*;
import mobile.esprit.pim.googleplus.DetailActivity;
import mobile.esprit.pim. hexagonerecycle.*;
import tyrantgit.explosionfield.ExplosionField;

public class nahla extends AppCompatActivity {
    private ExplosionField mExplosionField;
    private TextView tempview, textupdate;
    //List<Hive> hives;
    private Hive hive;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nahla);
        Bundle b = getIntent().getExtras();
        value = -1; // or other values
        if (b != null)
            value = b.getInt("key");
        hive = new Hive();
      /*  this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), (int) MainActivity.hives.get(value).getTemperature(), Toast.LENGTH_LONG).show();
            }
        });*/

        tempview = (TextView) findViewById(R.id.tv_cuvvrrent_temp);
        textupdate = (TextView) findViewById(R.id.tv_last_updated);




        System.out.println("pk^k"+MainActivity.hives.get(value).getTemperature() );
        tempview.setText(MainActivity.hives.get(value).getTemperature() + "°C");
        textupdate.setText("Ruche N°" + MainActivity.hives.get(value).getReference() + "");

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
            }

            if (root.getId() == R.id.eau) {
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

                                        // Interesting data to pass across are the thumbnail location, the map parameters,
                                        // the picture title & description, and the key to retrieve the bitmap from the cache


                                        intent.putExtra("lat", 37.6329946)
                                                .putExtra("lng", -122.4938344)
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


    }


}
