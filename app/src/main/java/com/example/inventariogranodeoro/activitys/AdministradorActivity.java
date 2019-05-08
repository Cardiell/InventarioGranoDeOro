package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventariogranodeoro.R;
import com.example.inventariogranodeoro.dao.ArchivoDAO;


public class AdministradorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }


    public void onClickGenerar(View view) {
        ArchivoDAO archivoGenerado=new ArchivoDAO();
        int aux=archivoGenerado.guardarArchivo();

        if (aux==1) {
            Toast.makeText(getApplicationContext(),"Se Guardo con Exito", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(),"No se Guardo", Toast.LENGTH_LONG).show();
    }

}
