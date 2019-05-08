package com.example.inventariogranodeoro.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventariogranodeoro.dao.Conexion;
import com.example.inventariogranodeoro.dao.UsuarioDAO;
import com.example.inventariogranodeoro.permisos.CamaraPermisos;
import com.example.inventariogranodeoro.R;
import java.sql.Connection;

/* ****************************************************
 *                                                    *
 *   ACTIVIDAD DE LOGIN PARA AMBOS USUARIOS           *
 *                                                    *
 ******************************************************/
public class MainActivity extends AppCompatActivity {
    String user, pass;
    Connection conectar;
    UsuarioDAO login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new CamaraPermisos().solicitarPermisos(this);
        initComponents(); // Simulacion de Java al agregar todos los componentes en un inicializador
    }

    private void initComponents(){
        login = new UsuarioDAO();
        txtusername = findViewById(R.id.txtUsername);
        txtpassword = findViewById(R.id.txtPassword);
        btniniciar = findViewById(R.id.btnIniciar);
        btn_get = findViewById(R.id.btnScan);

        //deshabilitar boton de inicio si no hay texto en los campos
        txtusername.addTextChangedListener(loginTextWatcher);
        txtpassword.addTextChangedListener(loginTextWatcher);
    }
    public void onClickScan(View view) {
        Conexion con = new Conexion();
        conectar = con.getConexion();
        if(conectar != null){
            Intent intent = new Intent(this, UsuarioActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Fuera de red", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickLogin(View view){ //Revisar si el texto contiene espacios antes de iniciar sesion
        if(txtusername.getText().toString().contains(" ")){
            txtusername.setError("¡No espacios por favor!");
            Toast.makeText(MainActivity.this, "¡No espacios por favor!", Toast.LENGTH_LONG).show();
        }
        if(txtpassword.getText().toString().contains(" ")){
            txtpassword.setError("¡No espacios por favor!");
            Toast.makeText(MainActivity.this, "¡No espacios por favor!", Toast.LENGTH_LONG).show();
        }
        user = txtusername.getText().toString();
        if(user.matches("")){ //Si campo esta vacio, agregar texto no valido
            user = "lol";
        }
        pass = txtpassword.getText().toString();
            if(login.consultaUsuario(user,pass) == null){ //Si el resultado del query es nulo
                Toast.makeText(getApplicationContext(),"Datos incorrectos.", Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(this, AdministradorActivity.class);
                startActivity(intent);
            }
    }

    private TextWatcher loginTextWatcher = new TextWatcher(){ //Revisar si campos estan vacios
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after){
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){
            String usernameInput = txtusername.getText().toString().trim();
            String passwordInput = txtpassword.getText().toString().trim();
            btniniciar.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s){
        }
    };



    private Button btn_get;
    private Button btniniciar;
    private EditText txtusername;
    private EditText txtpassword;
}
