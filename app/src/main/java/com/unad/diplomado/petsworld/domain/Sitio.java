package com.unad.diplomado.petsworld.domain;

/**
 * Created by cristhian on 25/10/2016.
 */
public class Sitio {
    private int id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String telefono;
    private String latitud;
    private String longitud;
    private int idCategoria;
    private int idCiudad;

    public Sitio() {

    }

    public Sitio(int id, String nombre, String descripcion, String ubicacion, String telefono, String latitud, String longitud, int idCategoria, int idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCategoria = idCategoria;
        this.idCiudad = idCiudad;
    }

    public int getId() {
        return id;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
}
