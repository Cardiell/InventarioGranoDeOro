package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.inventariogranodeoro.R;

public class AdministradorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }
}
