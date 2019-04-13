package com.example.inventariogranodeoro.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inventariogranodeoro.Entidades.Articulo;
import com.example.inventariogranodeoro.Lista;
import com.example.inventariogranodeoro.R;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD PARA MOSTRAR LA TABLA DE LOS PRODUCTOS *
 *   AGREGADOS EN CONSULTA CODIGO,CONSULTA NOMBRE Y   *
 *   CONSULTA SCANNER, ASI COMO TAMBIEN LOS BOTONES   *
 *   PARA ACCEDER A CONSULTA CODIGO,CONSULTA NOMBRE Y *
 *   CONSULTA SCANNER                                 *
 *                                                    *
 ******************************************************/
public class UsuarioActivity extends AppCompatActivity{
    private int index;
    private Lista lista;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        initComponents();
    }

    private void initComponents(){
        lista = new Lista(this);
        //lista.addProducto(new Articulo("123", "EJEMPLO", 45));
        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(lista);
        btnNombre = findViewById(R.id.btnNombreProducto);
        btnCodigo = findViewById(R.id.btnIdProducto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Articulo arti = (Articulo) data.getSerializableExtra("ARTICULO");
                lista.addProducto(arti);
            }
        }
        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                //Toast.makeText(getApplicationContext(),"Modificado", Toast.LENGTH_LONG).show();
                Articulo art = (Articulo) data.getSerializableExtra("ART");
                lista.modify(index, art);
            }
        }
    }

    public int getIndex(){
        return this.index;
    }
    public void setIndex(int i){
        this.index = i;
    }
    public void onClickNombre(View view){
        Intent intent = new Intent(this, ConsultaNombreActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickCodigo(View view){
        Intent intent = new Intent(this, ConsultaCodigoActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickScanner(View view) {
        Intent intent = new Intent(this,ConsultaScannerActivity.class);
        startActivityForResult(intent, 1);
    }
    private Button btnNombre;
    private Button btnCodigo;
    private Button btnScan;
    private RecyclerView recyclerView;
}
