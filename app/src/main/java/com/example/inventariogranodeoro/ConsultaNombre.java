package com.example.inventariogranodeoro;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//Actividad para consultar productos por nombre
public class ConsultaNombre extends Activity{
    Button consultar;
    Connection conectar;
    String text;
    EditText nombre;
    TextView displayId, displayNombre, displayExistencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_nombre);
        nombre = findViewById(R.id.nProducto);
        consultar = findViewById(R.id.consultar);
        displayId = findViewById(R.id.viewId);
        displayNombre = findViewById(R.id.viewNombre);
        displayExistencia = findViewById(R.id.viewExistencia);
        //Cuando se selecciona el boton de consultar
        consultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                consultarProducto();
            }
        });
        //Seleccionador de cantidad de producto
        NumberPicker np = findViewById(R.id.np);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
    }
    public void consultarProducto(){
        text = nombre.getText().toString();
        if(text.matches("")){ //Si campo esta vacio, agregar texto no valido
            text = "lol";
        }
        try{ //Hacer conexion a la base de datos
            ConexionBDClass con = new ConexionBDClass();
            conectar = con.Conexion();
            Statement stmt = conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs; //Resultado del query se guarda aqui
            String first = "select * from Producto$ where [Nombre ] = '"; //Primera parte del query
            String last = "'"; //Ultima parte del query
            String selectsql = first + text + last; //Combinar texto para crear el statement
            rs = stmt.executeQuery(selectsql); //Hacer el query

            if(!rs.isBeforeFirst()){ //Si el resultado del query esta vacio
                Toast.makeText(getApplicationContext(),"Producto NO disponible", Toast.LENGTH_LONG).show();
            }else{
                rs.last(); //Apuntar al renglon del resultado
                String disId = rs.getString(1); //Guardar texto IdProducto
                String disNombre = rs.getString(2); //Guardar texto Nombre
                displayId.setText("Código: " + disId); //Desplegar IdProducto en pantalla
                displayNombre.setText("Nombre: " + disNombre); //Desplegar Nombre en pantalla
            }
            conectar.close(); //Cerrar conexion
        }catch(Exception e){
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}