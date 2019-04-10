package com.example.inventariogranodeoro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class Usuario extends Activity {
    Button bNombre, bCodigo, bScan;
    private RecyclerView recyclerView;
    private Lista lista;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        lista = new Lista();
        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(lista);

        bNombre = findViewById(R.id.nombreProducto);
        bCodigo = findViewById(R.id.idProducto);
        bNombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){consultarNombre();
            }
        });
        bCodigo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){consultarCodigo();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                lista.addProducto(data.getStringExtra("ID"),data.getStringExtra("NAME"),data.getStringExtra("COUNT"));
            }
            if(resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    public void consultarNombre(){
        Intent intent = new Intent(this, ConsultaNombre.class);
        startActivityForResult(intent, 1);
    }
    public void consultarCodigo(){
        Intent intent = new Intent(this, ConsultaCodigo.class);
        startActivity(intent);
    }
}
