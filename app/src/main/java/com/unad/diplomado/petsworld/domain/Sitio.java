package com.unad.diplomado.petsworld.domain;

import java.io.Serializable;

/**
 * Created by cristhian on 25/10/2016.
 */

@SuppressWarnings("serial")

public class Sitio implements Serializable {
    private String idSitio;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String telefono;
    private String latitud;
    private String longitud;
    private String idCategoria;
    private String Categoria;
    private String idCiudad;
    private String Ciudad;
    private String verificado;

     public Sitio(String idSitio, String nombre, String descripcion, String ubicacion, String telefono, String latitud, String longitud, String idCategoria, String Categoria, String idCiudad, String Ciudad, String verificado) {
        this.idSitio = idSitio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCategoria = idCategoria;
        this.Categoria = Categoria;
        this.idCiudad = idCiudad;
        this.Ciudad = Ciudad;
        this.verificado = verificado;
    }

    public String getId() {
        return idSitio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public Double getLatitudDouble(){ return Double.parseDouble(latitud);}

    public Double getLongitudDouble(){ return Double.parseDouble(longitud);}

    public String getIdCategoria() {
        return idCategoria;
    }

    public String  getIdCiudad() {
        return idCiudad;
    }

    public String getCategoria() { return Categoria; }

    public String getCiudad() { return Ciudad; }

    public String getVerificado() {return  verificado; }

    public void setId(String idSitio) {
        this.idSitio = idSitio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setCategoria(String categoria) { this.Categoria = categoria; }

    public void setCiudad(String ciudad) { this.Ciudad = ciudad; }

    public void setVerificado(String verificado) { this.verificado = verificado; }
}
