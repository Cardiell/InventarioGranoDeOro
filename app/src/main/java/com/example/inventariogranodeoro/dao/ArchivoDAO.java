package com.example.inventariogranodeoro.dao;

/* ****************************************************
 *                                                    *
 *   CLASE PARA GENERAR Y GUARDAR ARCHIVO             *
 *                                                    *
 ******************************************************/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ArchivoDAO {
    Conexion conexion=new Conexion();

        public ArchivoDAO(){}

    public int guardarArchivo() {
        Connection con = null;
        ResultSet rs = null;
        CallableStatement cstm = null;
        String query = "EXEC xp_cmdshell 'bcp \"use GDO; SELECT idProducto,existencia FROM Producto where existencia>0\" queryout \"C:\\Archivo\\Producto.txt\" -T -c -t,' ";
        int exito=0;
        try{
            con = conexion.getConexion();
            cstm = con.prepareCall(query);
            rs = cstm.executeQuery();
            exito=1;
            System.out.println("Guardo con Exito");
        }catch(Exception e) {
            System.err.println("Tenemos una excepcion: "+e.getMessage());
        }finally {
            conexion.Cerrar2(cstm,rs); //Cerrar conexion
        }
        return exito;
    }


}
