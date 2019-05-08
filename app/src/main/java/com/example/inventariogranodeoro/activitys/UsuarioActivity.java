package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;
import com.example.inventariogranodeoro.dao.ArticuloDAO;
import com.example.inventariogranodeoro.entidades.Articulo;
import com.example.inventariogranodeoro.Lista;
import com.example.inventariogranodeoro.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    private SharedPreferences sharedPreferences;
    private int index;
    private int resultado;
    Lista lista;
    ArticuloDAO guardarArticulo = new ArticuloDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
        initComponents();

    }

    private void initComponents(){
        lista = new Lista(this);
        leerValor("key");
        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(lista);
        btnNombre = findViewById(R.id.imgBtnAdd);
        btnCodigo = findViewById(R.id.imgBtnIdProduct);
        btnActualizar= findViewById(R.id.imgBtnActualizar);
    }
    private void leerValor(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        if (json.isEmpty()) {
            System.out.println("ERRORRR...");
        } else {
            Type type = new TypeToken<List<Articulo>>() {
            }.getType();
            Lista.articulos = gson.fromJson(json, type);
        }
    }

    private String getJson(){
        Gson gson = new Gson();
        return gson.toJson(Lista.articulos);
    }

    private void guardarValor(String key,String json){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,json );
        editor.commit();
    }

    @Override
    public void onStop(){
        super.onStop();
        guardarValor("key",getJson());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Lista.articulos.clear();
        guardarValor("key","");

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

    public void onClickActualizar(View view) {
        AlertDialog.Builder dialogo_guardar = new AlertDialog.Builder(this);
        dialogo_guardar.setTitle("Importante");
        dialogo_guardar.setMessage("¿Desea actualizar las existencias?");
        dialogo_guardar.setCancelable(false);
        dialogo_guardar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                if(lista.getItemCount()>=0){
                    for (int i = 0; i < lista.getItemCount(); i++) {
                        resultado=guardarArticulo.guardarArticulo(lista.getArticulo(i).getIdProducto(), lista.getArticulo(i).getExistencia());
                    }
                    if(resultado==1){
                        Toast.makeText(getApplicationContext(),"¡Base de Datos Actualizada!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"¡No se logro subir lista!", Toast.LENGTH_SHORT).show();
                        guardarValor("key",getJson());
                        System.exit(0);
                    }
                }
            }
        });
        dialogo_guardar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo_guardar.show();
    }


    public void onBackPressed(){
        AlertDialog.Builder dialogo_salir = new AlertDialog.Builder(this);
        dialogo_salir.setTitle("Espera");
        dialogo_salir.setMessage("¿Quieres salir?");
        dialogo_salir.setCancelable(false);
        dialogo_salir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id){
                aceptar();
            }
        });
        dialogo_salir.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        dialogo_salir.show();

    }
    public void aceptar(){
        super.onBackPressed();
    }

    private ImageButton btnNombre;
    private ImageButton btnCodigo;
    private ImageButton btnScan;
    private ImageButton btnActualizar;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
}
