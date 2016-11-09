package com.unad.diplomado.petsworld.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.unad.diplomado.petsworld.ui.adapter.SitioAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by UserSistemas on 28/10/2016.
 */

public class SitiosFragment extends Fragment {

    private static final String TAG = SitiosFragment.class.getName();
    private SitioAdapter adapter;
    private RecyclerView lista;
    private RecyclerView.LayoutManager lManager;
    private Gson gson = new Gson();
    private String idCategoriaExtra;

    public SitiosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sitios, container, false);

        lista = (RecyclerView) v.findViewById(R.id.recilador_sitios);
        lista.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);

        idCategoriaExtra = getArguments().getString(Constantes.EXTRA_ID_CATEGORIA);

        Log.i(TAG,idCategoriaExtra);
        // Cargar datos en el adaptador

        cargarAdaptador();

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
       // getActivity().setTitle("Categoria");
    }

    /**
     * Carga el adaptador con las metas obtenidas
     * en la respuesta
     */
    public void cargarAdaptador() {
        // Petición GET
        VolleySingleton.
                getInstance(getActivity()).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GET_SITIOS,
                                null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "Error Volley: " + error.toString());
                                        Toast.makeText(
                                                getActivity(),
                                                "Error de Conexion",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                        )
                );
    }

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    private void procesarRespuesta(JSONObject response) {
        try {
            // Obtener atributo "estado"
            String estado = response.getString("estado");

            switch (estado) {
                case "1": // EXITO
                    // Obtener array "metas" Json
                    JSONArray mensaje = response.getJSONArray("Sitios");
                    // Parsear con Gson
                    Sitio[] sitios = gson.fromJson(mensaje.toString(), Sitio[].class);
                    // Inicializar adaptador
                    adapter = new SitioAdapter(Arrays.asList(sitios), getActivity());
                    // Setear adaptador a la lista
                    lista.setAdapter(adapter);
                    break;
                case "2": // FALLIDO
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;
            }

        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }

    }


}
