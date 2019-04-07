package com.example.inventariogranodeoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Usuario extends Activity {
    Button bNombre, bCodigo, bScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        bNombre = findViewById(R.id.nombreProducto);

        bNombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                consultarNombre();
            }
        });
    }
    public void consultarNombre(){
        Intent intent = new Intent(this, ConsultaNombre.class);
        startActivity(intent);
    }
}
