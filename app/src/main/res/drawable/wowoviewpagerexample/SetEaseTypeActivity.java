package com.nightonke.wowoviewpagerexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

public class SetEaseTypeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ease_type);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new com.nightonke.wowoviewpagerexample.SetEaseTypeAdapter(this));
        listView.setOnItemClickListener(this);

        checkBox = (CheckBox)findViewById(R.id.checkbox);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (getIntent().getStringExtra("AnimationType")) {
            case "WoWoPositionAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoPositionAnimationActivity.class);
                break;
            case "WoWoTranslationAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoTranslationAnimationActivity.class);
                break;
            case "WoWoScaleAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoScaleAnimationActivity.class);
                break;
            case "WoWoAlphaAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoAlphaAnimationActivity.class);
                break;
            case "WoWoRotationAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoRotationAnimationActivity.class);
                break;
            case "WoWoElevationAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoElevationAnimationActivity.class);
                break;
            case "WoWoTextViewTextSizeAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoTextViewTextSizeAnimationActivity.class);
                break;
            case "WoWoTextViewColorAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoTextViewColorAnimationActivity.class);
                break;
            case "WoWoTextViewTextAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoTextViewTextAnimationActivity.class);
                break;
            case "WoWoBackgroundColorAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoBackgroundColorAnimationActivity.class);
                break;
            case "WoWoShapeColorAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoShapeColorAnimationActivity.class);
                break;
            case "WoWoLayerListColorAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoLayerListColorAnimationActivity.class);
                break;
            case "WoWoStateListColorAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoStateListColorAnimationActivity.class);
                break;
            case "WoWoPathAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.WoWoPathAnimationActivity.class);
                break;
            case "CustomAnimation":
                intent = new Intent(this, com.nightonke.wowoviewpagerexample.CustomAnimationActivity.class);
                break;
            default: return;
        }
        switch (parent.getId()) {
            case R.id.listview:
                intent.putExtra("easeType", position);
                intent.putExtra("useSameEaseTypeBack", checkBox.isChecked());
                startActivity(intent);
                break;
        }
    }
}
