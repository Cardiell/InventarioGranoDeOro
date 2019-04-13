package com.example.inventariogranodeoro.dao;


import com.example.inventariogranodeoro.entidades.Articulo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;


/* ****************************************************
 *                                                    *
 *   CLASE PARA REALIZAR CONSULTAS A ARTICULOS POR    *
 *   CODIGO Y NOMBRE                                  *
 *                                                    *
 ******************************************************/
public class ArticuloDAO{
    Conexion conexion = new Conexion();
    public ArticuloDAO(){}

    public Articulo consultaCodigo(String id){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo articulo = null;
        String query = "exec consultaCodigo '"+id+"'";

        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                articulo = new Articulo();
                articulo.setIdProducto(rs.getString("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setExistencia(rs.getFloat("existencia"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(articulo);
    }

    public Articulo consultaNombre(String nombre){
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        Articulo articulo = null;
        String query = "exec consultaNombre '"+nombre+"'";
        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            if(rs.next()){
                articulo = new Articulo();
                articulo.setIdProducto(rs.getString("idProducto"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setExistencia(rs.getFloat("existencia"));
            }
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());

        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return(articulo);
    }
}
