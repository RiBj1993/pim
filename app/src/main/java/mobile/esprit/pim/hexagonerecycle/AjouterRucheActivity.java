package mobile.esprit.pim.hexagonerecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import mobile.esprit.pim.AsyncTaskClass.AddHives;
import mobile.esprit.pim.Entities.Hive;
import mobile.esprit.pim.R;
import mobile.esprit.pim.USER.SessionManager;

public class AjouterRucheActivity extends Activity {

    private EditText  referenceField;
    private EditText  input_ruche;
    private EditText  ipField;
    private Button    registerButton;
    private Button btnRegister;
   //  MediaPlayer mp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initUI();
        btnRegister = (Button) findViewById(R.id.btn_register);
        SessionManager. mp = MediaPlayer.create(getApplicationContext(), R.raw.audiotest11);
        btnRegister.startAnimation(AnimationUtils.loadAnimation(this, R.anim.buttonbig));


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( SessionManager.mp.isPlaying()) {
                    SessionManager.mp.pause();
                    // btnRegister.setBackgroundResource(R.drawable.speaker);
                    btnRegister.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.buttonbig));

                } else {
                    SessionManager. mp.start();
                    // btnRegister.setBackgroundResource(R.drawable.high);
                    btnRegister.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.buttonbig));


                }



            }
        });
    }

    private void initUI()
    {
        referenceField = (EditText) findViewById( R.id.reference);
        input_ruche= (EditText) findViewById( R.id.id_ruche);
        ipField= (EditText) findViewById( R.id.input_ip);
        registerButton = (Button) findViewById( R.id.btn_signup );

        registerButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {

                onRegisterButtonClicked();

            }
        } );
    }

    private void onRegisterButtonClicked()  {
        String id_ruche = input_ruche.getText().toString().trim();
        String ip = ipField.getText().toString().trim();
        String referenceText = referenceField.getText().toString().trim();

        Hive hive= new Hive();

        if ( id_ruche.isEmpty() )
        {
            showToast( "Field 'id_ruche' cannot be empty." );
            return;
        }

        if ( ip.isEmpty() )
        {
            showToast( "Field 'ip' cannot be empty." );
            return;
        }

        if ( referenceText.isEmpty() )
        {
            showToast( "Field 'reference' cannot be empty." );
            return;
        }




        if( !id_ruche.isEmpty() )
        {
            hive.setId_ruche(Integer.parseInt(id_ruche));

        }

        if( !ip.isEmpty() )
        {
            hive.setIp(ip);
        }

        if( !referenceText.isEmpty() )
        {
            hive.setReference(Integer.parseInt(referenceText));
        }

        AddHives p=  new  AddHives();
        p.Addhive(hive ,this);

        input_ruche.setText("") ;
        ipField.setText("") ;
       referenceField.setText("") ;
        showToast( "votre ruche a ete ajouter ");
        getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
    }

    private void showToast( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //  handler.removeCallbacksAndMessages(null);
        if(SessionManager. mp !=  null ){if(SessionManager. mp.isPlaying()) {SessionManager. mp.stop();}}

        Intent intent = new Intent(getApplicationContext(), mobile.esprit.pim.hexagonerecycle.MainActivity.class);
        startActivity(intent);
        finish();;


    }
}