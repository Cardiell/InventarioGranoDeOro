package com.example.inventariogranodeoro.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.inventariogranodeoro.entidades.Articulo;
import com.example.inventariogranodeoro.R;

public class ModificarActivity extends Activity{
    Button btnCambiar;
    Articulo articulo;
    EditText np;
    TextView txtId, txtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        initComponents();
    }

    private void initComponents(){
        articulo = (Articulo) getIntent().getSerializableExtra("ART");
        btnCambiar = findViewById(R.id.btnModificar);
        txtId = findViewById(R.id.mtxtId);
        txtNombre = findViewById(R.id.mtxtNombre);
        txtId.setText(String.format("Código: ").concat(articulo.getIdProducto()));
        txtNombre.setText(String.format("Nombre: ").concat(articulo.getNombre()));
        np = findViewById(R.id.mnp);
    }

    public void onClickGuardar(View view) {
        if(!np.getText().toString().isEmpty()){
            articulo.setExistencia(Float.valueOf(np.getText().toString()));
        }
        else{
            articulo.setExistencia(Float.valueOf(np.getHint().toString()));
        }
        Intent intent = new Intent();
        intent.putExtra("ART", articulo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
