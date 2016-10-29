package com.unad.diplomado.petsworld.tools;

/**
 * Created by Fizz on 29/10/2016.
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;

    private static final String URL_BASE = "http://petsworld.netau.net";


    /**
     * URLs del Web Service
     */

    public static final String GET_SITIOS = URL_BASE  + "/obtener_sitios.php";
    public static final String GET_SITIO_BY_ID = URL_BASE + "/obtener_sitio_por_id.php";
    public static final String GET_SITIO_BY_CATEGORIA = URL_BASE + "obtener_sitio_por_categoria.php";
    public static final String INSERT = URL_BASE  + "/insertar_meta.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID_SITIO = "IDEXTRA";

    public static final String EXTRA_ID_CATEGORIA = "";

}
