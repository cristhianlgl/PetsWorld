package com.unad.diplomado.petsworld.ui.fragmentos;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.io.VolleySingleton;
import com.unad.diplomado.petsworld.tools.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class NuevoSitioFragment extends Fragment {

    private static final String TAG = NuevoSitioFragment.class.getSimpleName();

    EditText nombre_input;
    EditText descripcion_input;
    EditText ubicacion_input;
    EditText telefono_input;
    EditText longitud_input;
    EditText latitud_input;
    Spinner ciudad_spinner;
    String idCategoria;

    public NuevoSitioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nuevo_sitio, container, false);
        nombre_input = (EditText) v.findViewById(R.id.nombre_input);
        descripcion_input = (EditText) v.findViewById(R.id.descripcion_input);
        ubicacion_input = (EditText) v.findViewById(R.id.ubicacion_input);
        telefono_input = (EditText) v.findViewById(R.id.telefono_input);
        longitud_input = (EditText) v.findViewById(R.id.longitud_input);
        latitud_input = (EditText) v.findViewById(R.id.latitud_input);
        ciudad_spinner = (Spinner) v.findViewById(R.id.ciudad_spinner);
        return  v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:// CONFIRMAR
                if (!camposVacios())
                    guardarSitio();
                else
                    Toast.makeText( getActivity(), "Completa los campos", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_discard:// DESCARTAR
                if (!camposVacios())
                    mostrarDialogo();
                else
                    getActivity().finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void guardarSitio() {

        // Obtener valores actuales de los controles
        final String nombre = nombre_input.getText().toString();
        final String descripcion = descripcion_input.getText().toString();
        final String telefono = telefono_input.getText().toString();
        final String ubicacion = ubicacion_input.getText().toString();
        final String longitud = longitud_input.getText().toString();
        final String latitud = latitud_input.getText().toString();
        final String idCiudad = getIdCiudad( ciudad_spinner.getSelectedItem().toString());

        HashMap<String, String> map = new HashMap<>();// Mapeo previo

        map.put("nombre", nombre);
        map.put("descripcion", descripcion);
        map.put("ubicacion", ubicacion);
        map.put("telefono", telefono);
        map.put("idCategoria", descripcion);
        map.put("idCiudad", idCiudad);
        map.put("latitud", latitud);
        map.put("longitud", longitud);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d(TAG, jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constantes.INSERT,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }

                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );

    }

    /**
     * Procesa la respuesta obtenida desde el sevidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {

        try {
            // Obtener estado
            String estado = response.getString("estado");
            // Obtener mensaje
            String mensaje = response.getString("mensaje");

            switch (estado) {
                case "1":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de éxito
                    getActivity().setResult(Activity.RESULT_OK);
                    // Terminar actividad
                    getActivity().finish();
                    break;

                case "2":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de falla
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    // Terminar actividad
                    getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public boolean camposVacios() {
        String nombre = nombre_input.getText().toString();
        String ubicacion = ubicacion_input.getText().toString();
        String longitud = longitud_input.getText().toString();
        String latitud = latitud_input.getText().toString();

        return (nombre.isEmpty() || ubicacion.isEmpty() || longitud.isEmpty() || latitud.isEmpty());
    }


    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {
        DialogFragment dialogo = ConfirmDialogFragment.
                createInstance(
                        getResources().
                                getString(R.string.dialog_discard_msg));
        dialogo.show(getFragmentManager(), "ConfirmDialog");
    }

    private String getIdCiudad(String ciudad) {
        switch (ciudad) {
            case "Armenia":  return  "1";
            case "Cartago": return  "2";
            case "Manizales": return  "3";
            case "Pereira": return  "4";
        }
        return "1";
    }
}
