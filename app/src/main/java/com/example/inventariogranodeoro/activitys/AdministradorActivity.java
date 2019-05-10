package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventariogranodeoro.R;
import com.example.inventariogranodeoro.dao.ArchivoDAO;
import com.example.inventariogranodeoro.dao.Conexion;
import com.example.inventariogranodeoro.dao.LimpiarBD;
import com.example.inventariogranodeoro.entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


public class AdministradorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }


    public void onClickGenerar(View view) {
        AlertDialog.Builder dialogo_guardar = new AlertDialog.Builder(this);
        dialogo_guardar.setTitle("Importante");
        dialogo_guardar.setMessage("¿Desea generar el reporte?");
        dialogo_guardar.setCancelable(false);
        dialogo_guardar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                ArchivoDAO archivoGenerado = new ArchivoDAO();
                int aux = archivoGenerado.guardarArchivo();

                if (aux == 1) {
                    Toast.makeText(getApplicationContext(), "Reporte generado con exito", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Reporte no generado", Toast.LENGTH_LONG).show();
            }
        });
        dialogo_guardar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo_guardar.show();
    }

    public void onClickLimpiar(View view) {


        AlertDialog.Builder dialogo_guardar = new AlertDialog.Builder(this);
        dialogo_guardar.setTitle("Importante");
        dialogo_guardar.setMessage("¿Desea ajustar a 0 las existencias?");
        dialogo_guardar.setCancelable(false);
        dialogo_guardar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                LimpiarBD limpieza = new LimpiarBD();
                int aux = limpieza.ajustarCero();

                if (aux == 1) {
                    Toast.makeText(getApplicationContext(), "Ajuste realizado con exito", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Ajuste no realizado", Toast.LENGTH_LONG).show();
            }
        });
        dialogo_guardar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo_guardar.show();
    }
}

