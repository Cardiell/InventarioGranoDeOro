package com.example.inventariogranodeoro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    Button btn_get;
    Connection conectar;
    EditText username;
    EditText password;
    Button iniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        iniciar = findViewById(R.id.iniciar);
        //deshabilitar boton de inicio si no hay texto en los campos
        username.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
        btn_get = findViewById(R.id.scan);

       /*PRUEBA DE CONEXION*/
        btn_get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ConexionBDClass con = new ConexionBDClass();
                conectar = con.Conexion();
                if(conectar != null){
                    //Toast.makeText(getApplicationContext(),"Dentro de red", Toast.LENGTH_LONG).show();
                    iniciarUsuario(); //Iniciar sesion si la red esta disponible
                }else{
                    Toast.makeText(getApplicationContext(),"Fuera de red", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void iniciarUsuario(){
        Intent intent = new Intent(this, Usuario.class);
        startActivity(intent);
    }
    private TextWatcher loginTextWatcher = new TextWatcher(){ //Revisar si campos estan vacios
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            String usernameInput = username.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            iniciar.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s){
        }
    };
    public void login(View view){ //Revisar si el texto contiene espacios antes de iniciar sesion
        if(username.getText().toString().contains(" ")){
            username.setError("¡No espacios por favor!");
            Toast.makeText(MainActivity.this, "¡No espacios por favor!", Toast.LENGTH_LONG).show();
        }
        if(password.getText().toString().contains(" ")){
            password.setError("¡No espacios por favor!");
            Toast.makeText(MainActivity.this, "¡No espacios por favor!", Toast.LENGTH_LONG).show();
        }
    }
}
