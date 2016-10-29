package com.unad.diplomado.petsworld.domain;

/**
 * Created by cristhian on 25/10/2016.
 */
public class Sitio {
    private String idSitio;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String telefono;
    private String latitud;
    private String longitud;
    private String idCategoria;
    private String categoria;
    private String idCiudad;
    private String ciudad;

     public Sitio(String idSitio, String nombre, String descripcion, String ubicacion, String telefono, String latitud, String longitud, String idCategoria, String categoria, String idCiudad, String ciudad) {
        this.idSitio = idSitio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCategoria = idCategoria;
        this.idCiudad = idCiudad;
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

    public String getIdCategoria() {
        return idCategoria;
    }

    public String  getIdCiudad() {
        return idCiudad;
    }

    public String getCategoria() { return categoria; }

    public String getCiudad() { return ciudad; }

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

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}
