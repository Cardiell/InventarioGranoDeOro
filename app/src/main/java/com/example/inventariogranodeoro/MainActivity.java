package com.example.inventariogranodeoro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    Button btn_get;
    Connection conectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_get=(Button)findViewById(R.id.button_0);

       /*PRUEBA DE CONEXION
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionBDClass con=new ConexionBDClass();
                conectar=con.Conexion();
                if (conectar ==null){
                    Toast.makeText(getApplicationContext(),"Fuera de red",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Dentro de red",Toast.LENGTH_LONG).show();
                }

            }
        });
*/

    }
}
