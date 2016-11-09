package com.unad.diplomado.petsworld.ui.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Sitio;
import com.unad.diplomado.petsworld.io.VolleySingleton;
import com.unad.diplomado.petsworld.tools.Constantes;

import org.json.JSONException;
import org.json.JSONObject;


public class DetalleSitioFragment extends Fragment {
    /*
    Etiqueta de valor extra
     */
    private static final String EXTRA_ID = "IDMETA";

    /**
     * Etiqueta de depuración
     */
    private static final String TAG = DetalleSitioFragment.class.getSimpleName();

    /*
    Instancias de Views
     */
    private TextView nombre;
    private TextView descripcion;
    private TextView ubicacion;
    private TextView telefono;
    private TextView ciudad;
    private String extra;
    private Gson gson = new Gson();


    public static DetalleSitioFragment createInstance(String idSitio) {
        DetalleSitioFragment detailFragment = new DetalleSitioFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, idSitio);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sitios_detalle, container, false);

        // Obtención de views
        nombre = (TextView) v.findViewById(R.id.detalle_sitios_nombre);
        descripcion = (TextView) v.findViewById(R.id.detalle_sitio_descripcion);
        telefono = (TextView) v.findViewById(R.id.detalle_sitio_telefono);
        ubicacion = (TextView) v.findViewById(R.id.detalle_sitio_ubicacion);
        ciudad = (TextView) v.findViewById(R.id.detalle_sitio_ciudad);

        // Obtener extra del intent de envío
        extra = getArguments().getString(EXTRA_ID);

        // Cargar datos desde el web service
        cargarDatos();

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    public void cargarDatos() {

        // Añadir parámetro a la URL del web service
        String newURL = Constantes.GET_SITIO_BY_ID + "?idSitio=" + extra;

        // Realizar petición GET_BY_ID
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        newURL,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar respuesta Json
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }
                )
        );
    }

    /**
     * Procesa cada uno de los estados posibles de la
     * respuesta enviada desde el servidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {

        try {
            // Obtener atributo "mensaje"
            String mensaje = response.getString("estado");

            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONObject object = response.getJSONObject("Sitio");

                    //Parsear objeto
                    Sitio sitio = gson.fromJson(object.toString(), Sitio.class);

                    /*
                    // Asignar color del fondo
                    switch (meta.getCategoria()) {
                        case "Salud":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.saludColor));
                            break;
                        case "Finanzas":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.finanzasColor));
                            break;
                        case "Espiritual":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.espiritualColor));
                            break;
                        case "Profesional":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.profesionalColor));
                            break;
                        case "Material":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.materialColor));
                            break;
                    }
                    */

                    // Seteando valores en los views
                    nombre.setText(sitio.getNombre());
                    descripcion.setText(sitio.getDescripcion());
                    ciudad.setText(sitio.getCiudad());
                    telefono.setText(sitio.getTelefono());
                    ubicacion.setText(sitio.getUbicacion());

                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}