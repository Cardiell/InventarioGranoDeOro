package com.example.inventariogranodeoro.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventariogranodeoro.DAO.ArticuloDAO;
import com.example.inventariogranodeoro.Entidades.Articulo;
import com.example.inventariogranodeoro.R;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD PARA CONSULTAR PRODUCTOS POR CODIGO    *
 *                                                    *
 ******************************************************/
public class ConsultaCodigoActivity extends Activity {

    ArticuloDAO consultaCodigo; //Objeto para realizar la consulta por codigo
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_codigo);
        initComponents();                                          // Simulacion de Java al agregar todos los componentes en un inicializador
    }

    public void onClickConsultar(View view) {
        text = edtid.getText().toString();
        if(text.matches("")){                                // Si campo esta vacio, agregar texto no valido
            text = "lol";
        }
        Articulo articulo = consultaCodigo.consultaCodigo(text);   // Obteniendo el articulo
        if(articulo == null) {
            Toast.makeText(getApplicationContext(), "Codigo incorrecto.", Toast.LENGTH_LONG).show();
        }else {
            txtId.setText("Código: " + articulo.getIdProducto()); //Desplegar IdProducto en pantalla
            txtNombre.setText("Nombre: " + articulo.getNombre()); //Desplegar Nombre en pantalla
        }

    }

    private void initComponents() {
        consultaCodigo = new ArticuloDAO();
        edtid = findViewById(R.id.edtCProducto);
        btnConsultar = findViewById(R.id.btnConsultar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtExistencia = findViewById(R.id.txtExistencia);

        //Seleccionador de cantidad de producto
        NumberPicker np = findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
    }

    private EditText edtid;
    private Button btnConsultar;
    private TextView txtId;
    private TextView txtNombre;
    private TextView txtExistencia;
}
