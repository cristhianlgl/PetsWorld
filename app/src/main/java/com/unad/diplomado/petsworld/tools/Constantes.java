package com.unad.diplomado.petsworld.tools;

/**
 * Created by Fizz on 29/10/2016.
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;
    public static final int CODIGO_NUEVO_SITIO =101;
    public static final int CODIGO_MAPS =102;
    /**
     * Transición Detalle -> Actualización
     */

    private static final String URL_BASE = "http://petsworld.netau.net";

    /**
     * URLs del Web Service
     */

    public static final String GET_SITIOS = "http://petsworld.netau.net/obtener_sitios.php";
    public static final String GET_SITIO_BY_ID = URL_BASE + "/obtener_sitio_por_id.php";
    public static final String GET_SITIO_BY_CATEGORIA = URL_BASE + "/obtener_sitio_por_categoria.php";
    public static final String INSERT = URL_BASE  + "/insertar_sitio.php";

    public static final String EXTRA_SITIO = "IDSITIO";
    public static final String EXTRA_ID_CATEGORIA = "IDCATEGORIA";
    public static final String EXTRA_SITIO_MAPS = "SITIOMAP";

}
