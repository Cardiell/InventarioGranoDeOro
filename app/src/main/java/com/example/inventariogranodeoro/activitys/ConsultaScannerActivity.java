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
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ConsultaScannerActivity extends Activity implements ZXingScannerView.ResultHandler{

    Articulo articulo;
    ArticuloDAO consulta;
    ZXingScannerView vistaescaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_scanner);
        scaner();
    }

    private void initComponents() {
        btnAgregar = findViewById(R.id.btnAgregar);
        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        np = findViewById(R.id.np);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void handleResult(Result rawResult){
        String id = rawResult.getText(); //Obteniendo el codigo escanerado de la camara
        setContentView(R.layout.activity_consulta_scanner);
        vistaescaner.stopCamera();
        consulta = new ArticuloDAO();
        articulo = consulta.consultaCodigo(id);
        initComponents();
        if (articulo == null){ //Si el resultado del query esta vacio
            Toast.makeText(getApplicationContext(), "El articulo: "+id+"\nNo esta disponible", Toast.LENGTH_LONG).show();
            finish();
        } else {
            txtId.setText(String.format("Código: ").concat(articulo.getIdProducto()));
            txtNombre.setText(String.format("Nombre: ").concat(articulo.getNombre()));
        }
    }

    private TextView txtNombre;
    private TextView txtId;
    private Button btnAgregar;
    private EditText np;
}
