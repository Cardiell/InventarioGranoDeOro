package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventariogranodeoro.dao.ArticuloDAO;
import com.example.inventariogranodeoro.entidades.Articulo;
import com.example.inventariogranodeoro.R;

import java.util.ArrayList;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD PARA CONSULTAR PRODUCTOS POR NOMBRE    *
 *                                                    *
 ******************************************************/
public class ConsultaNombreActivity extends Activity{
    private int index;
    String text;
    ArticuloDAO consultaNombre;
    Articulo articulo;
    ArrayList<String> lista=new ArrayList();
    String part1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_nombre);
        initComponents();
    }

    public void onClickConsultar(View view) {
        text = edtNombre.getText().toString();
        if(text.matches("")){                                           //Si campo esta vacio, agregar texto no valido
            text = "";
        }
        lista = consultaNombre.consultaNombre2(text);
        if(lista.size()<1){                                             //Si el resultado del query esta vacio
            btnAgregar.setEnabled(false);
            txtId.setText(String.format("Código:"));
            txtNombre.setText(String.format("Nombre:"));
            Toast.makeText(getApplicationContext(),"El articulo: "+text+"\nNo esta disponible", Toast.LENGTH_LONG).show();
        }else if(lista.size()>1){

            this .lista=lista;
            String[] simpleArray = new String[lista.size()];
            simpleArray = lista.toArray(simpleArray);
            final String[] items=simpleArray;
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this);

            builder.setTitle("Selección")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {

                            String[] parts = items[item].split(" ", 2);
                            part1 = parts[0];
                            articulo=consultaNombre.consultaNombre(parts[1]);
                            txtId.setText(String.format("Código: ").concat(articulo.getIdProducto()));
                            txtNombre.setText(String.format("Nombre: ").concat(articulo.getNombre()));
                            btnAgregar.setEnabled(true);
                        }});

            builder.show();
        }else{
            String[] parts = lista.get(0).split(" ", 2);
            articulo=consultaNombre.consultaNombre(parts[1]);
            txtId.setText(String.format("Código: ").concat(articulo.getIdProducto()));
            txtNombre.setText(String.format("Nombre: ").concat(articulo.getNombre()));
            btnAgregar.setEnabled(true);
        }
    }

    /* Metodo para regresar los datos al Acitvity anterior en este caso UsuarioActivity */
    public void onClickAgregar(View view) {
        if(!np.getText().toString().isEmpty()){
            articulo.setExistencia(Float.valueOf(np.getText().toString()));
        }
        else{
            articulo.setExistencia(Float.valueOf(np.getHint().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("ARTICULO", articulo);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void initComponents() {
        index = getIntent().getIntExtra("INDEX", 0);
        consultaNombre = new ArticuloDAO();
        edtNombre = findViewById(R.id.edtnProducto);
        btnConsulta = findViewById(R.id.btnConsultar);
        btnAgregar = findViewById(R.id.btnAgregar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        np = findViewById(R.id.np);
    }

    private EditText edtNombre;
    private TextView txtId;
    private TextView txtNombre;
    private Button btnConsulta;
    private Button btnAgregar;
    private EditText np;
}