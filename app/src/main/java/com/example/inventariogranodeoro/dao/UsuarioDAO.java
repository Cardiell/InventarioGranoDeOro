package com.example.inventariogranodeoro.dao;

import com.example.inventariogranodeoro.entidades.Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


/* ****************************************************
 *                                                    *
 *   CLASE PARA REALIZAR CONSULTA DE USUARIO          *
 *                                                    *
 ******************************************************/
public class UsuarioDAO {

    Conexion conexion = new Conexion();

    public UsuarioDAO(){}

    public Usuario consultaUsuario(String user,String pass){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Usuario usuario = null;
        String query = "exec consultaUsuario '"+user+"','"+pass+"'";

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                usuario = new Usuario();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(usuario);
    }
}
