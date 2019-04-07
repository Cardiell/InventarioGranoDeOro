package com.example.inventariogranodeoro;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDClass{
    private String IP = "192.168.0.3";
    private String DB = "mydata";
    private String DBUser = "user";
    private String DBPWD = "1pAssW0rd";

    @SuppressLint("NewApi")

    public Connection Conexion(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conexion = null;
        String urlconexion;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            urlconexion = "jdbc:jtds:sqlserver://"+IP+"/"+DB+";user="+DBUser+";password="+DBPWD+";";
            conexion = DriverManager.getConnection(urlconexion);
        }catch(SQLException se){
            Log.e("Error here 1 : ", se.getMessage());
        }catch(ClassNotFoundException e){
            Log.e("Erron #2",e.getMessage());
        }catch (Exception e){
            Log.e("Error #3",e.getMessage());
        }
        // Log.e("Conexion exitosa","info");
        return conexion;
    }
}
