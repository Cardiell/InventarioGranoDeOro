package com.example.inventariogranodeoro.dao;

import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LimpiarBD {
    public LimpiarBD(){}

    public int ajustarCero (){

        Conexion conexion = new Conexion();
        Connection con = null;
        Statement cstm = null;
        int exito=0;

        String query = "update Producto set existencia=0 where existencia>0;";

        try{
            con = conexion.getConexion();
            cstm = con.createStatement();
            cstm.executeUpdate(query);
            exito=1;
            con.close();
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }

        return exito;

    }
}
