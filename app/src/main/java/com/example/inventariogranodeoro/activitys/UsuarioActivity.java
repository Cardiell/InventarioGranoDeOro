package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    private ImageButton btnNombre;
    private ImageButton btnCodigo;
    private ImageButton btnScan;
    private ImageButton btnActualizar;
    private RecyclerView recyclerView;

    private int resultado;
    private Toolbar toolbar;
    ArticuloDAO guardarArticulo=new ArticuloDAO();


    /*
    private Button btnNombre;
    private Button btnCodigo;
    private Button btnScan;
    private RecyclerView recyclerView;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        btnNombre = findViewById(R.id.imgBtnAdd);
        btnCodigo = findViewById(R.id.imgBtnIdProduct);
        btnActualizar= findViewById(R.id.imgBtnActualizar);
        //btnNombre = findViewById(R.id.btnNombreProducto);
        //btnCodigo = findViewById(R.id.btnIdProducto);
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
                System.out.println("modifica");
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
                    }else{
                        Toast.makeText(getApplicationContext(),"¡No se logro subir lista!", Toast.LENGTH_SHORT).show();
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
}
