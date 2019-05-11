package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventariogranodeoro.dao.ArticuloDAO;
import com.example.inventariogranodeoro.entidades.Articulo;
import com.example.inventariogranodeoro.R;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD PARA CONSULTAR PRODUCTOS POR CODIGO    *
 *                                                    *
 ******************************************************/
public class ConsultaCodigoActivity extends Activity {

    ArticuloDAO consultaCodigo;                                    //Objeto para realizar la consulta por codigo
    String text;
    Articulo articulo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_codigo);
        initComponents();
    }

    public void onClickConsultar(View view) {
        text = edtid.getText().toString();
        if(text.matches("")){                                // Si campo esta vacio, agregar texto no valido
            text = "";
        }
        articulo = consultaCodigo.consultaCodigo(text);            // Obteniendo el articulo
        if(articulo == null){                                      //Si el resultado del query esta vacio
            btnAgregar.setEnabled(false);
            txtId.setText(String.format("Código:"));
            txtNombre.setText(String.format("Nombre:"));
            Toast.makeText(getApplicationContext(),"El articulo: "+text+"\nNo esta disponible", Toast.LENGTH_LONG).show();
        }else{
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
        consultaCodigo = new ArticuloDAO();
        edtid = findViewById(R.id.edtCProducto);
        btnConsultar = findViewById(R.id.btnConsultar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        btnAgregar = findViewById(R.id.btnAgregar);

        //Seleccionador de cantidad de producto
        np = findViewById(R.id.np);
    }

    private EditText edtid;
    private Button btnConsultar;
    private TextView txtId;
    private TextView txtNombre;
    private Button btnAgregar;
    private  EditText  np;

}
