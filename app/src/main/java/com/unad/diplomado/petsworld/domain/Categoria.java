package com.unad.diplomado.petsworld.domain;

import android.graphics.Color;

/**
 * Created by cristhian on 22/10/2016.
 */
public class Categoria {
    private String nombre;
    private int imagen;
    private int color;

    public Categoria (int imagen, String nombre, int color) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
    }

    public String getNombre(){return nombre;}
    public int getImagen(){ return imagen; }
    public int getColor() { return  color; }

}