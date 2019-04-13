package com.example.inventariogranodeoro.Entidades;

import java.io.Serializable;

public class Articulo implements Serializable{
    private String idProducto;
    private String nombre;
    private float existencia;

    public Articulo(String idProducto, String nombre, float existencia){
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.existencia = existencia;
    }

    public Articulo(){}

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getExistencia() {
        return existencia;
    }

    public void setExistencia(float existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString(){
        return this.idProducto;
    }
}
