package com.example.inventariogranodeoro.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.inventariogranodeoro.Entidades.Articulo;
import com.example.inventariogranodeoro.R;

public class ModificarActivity extends Activity{
    Button btnCambiar;
    Articulo articulo;
    NumberPicker np;
    TextView txtId, txtNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        initComponents();
    }

    private void initComponents(){
        articulo = (Articulo) getIntent().getSerializableExtra("ART");
        btnCambiar = findViewById(R.id.btnModificar);
        txtId = findViewById(R.id.mtxtId);
        txtNombre = findViewById(R.id.mtxtNombre);
        txtId.setText("Código: " + articulo.getIdProducto());
        txtNombre.setText("Nombre: " + articulo.getNombre());
        np = findViewById(R.id.mnp);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
    }

    public void onClickGuardar(View view) {
        articulo.setExistencia(Float.valueOf(np.getValue()));
        Intent intent = new Intent();
        intent.putExtra("ART", articulo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
