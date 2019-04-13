package com.example.inventariogranodeoro.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.example.inventariogranodeoro.DAO.ArticuloDAO;
import com.example.inventariogranodeoro.Entidades.Articulo;
import com.example.inventariogranodeoro.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ConsultaScannerActivity extends Activity implements ZXingScannerView.ResultHandler{

    Articulo articulo;
    ArticuloDAO consulta;
    ZXingScannerView vistaescaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_scanner);
        scaner();
    }


    private void initComponents() {
        btnAgregar = findViewById(R.id.btnAgregar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        np = findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articulo.setExistencia(Float.valueOf(np.getValue()));
                Intent intent = new Intent();
                intent.putExtra("ARTICULO", articulo);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    /* inicializando el scanner al inicializar la camara se va a handleResult*/
    public void scaner() {
        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(this);
        setContentView(vistaescaner);
        vistaescaner.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String id = rawResult.getText();                                        //Obteniendo el codigo escanerado de la camara
        setContentView(R.layout.activity_consulta_scanner);
        vistaescaner.stopCamera();
        consulta = new ArticuloDAO();
        articulo = consulta.consultaCodigo(id);
        initComponents();
        if (articulo == null) {                                                 //Si el resultado del query esta vacio
            Toast.makeText(getApplicationContext(), "El articulo: "+id+"\nNo esta disponible", Toast.LENGTH_LONG).show();
            finish();
        } else {
            txtId.setText("Código: " + articulo.getIdProducto());
            txtNombre.setText("Nombre: " + articulo.getNombre());

        }
    }


    private TextView txtNombre;
    private TextView txtId;
    private Button btnAgregar;
    private NumberPicker np;
}
