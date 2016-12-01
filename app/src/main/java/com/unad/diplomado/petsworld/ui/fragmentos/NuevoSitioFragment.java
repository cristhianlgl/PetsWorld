package com.unad.diplomado.petsworld.ui.fragmentos;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.unad.diplomado.petsworld.ui.actividades.MapsNuevoSitioActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class NuevoSitioFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = NuevoSitioFragment.class.getSimpleName();
    private static final String EXTRA_ID = "IDCATEGORIA";

    private EditText nombre_input;
    private EditText descripcion_input;
    private EditText ubicacion_input;
    private EditText telefono_input;
    private TextView longitud_input;
    private TextView latitud_input;
    private Spinner ciudad_spinner;
    private Button button_save;
    private ImageButton button_mapa;
    private String idCategoria;

    public NuevoSitioFragment() {
    }

    public static NuevoSitioFragment createInstance(String idCategoria) {
        NuevoSitioFragment nuevoSitioFragment = new NuevoSitioFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, idCategoria);
        nuevoSitioFragment.setArguments(bundle);
        return nuevoSitioFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nuevo_sitio, container, false);
        nombre_input = (EditText) v.findViewById(R.id.nombre_input);
        descripcion_input = (EditText) v.findViewById(R.id.descripcion_input);
        ubicacion_input = (EditText) v.findViewById(R.id.ubicacion_input);
        telefono_input = (EditText) v.findViewById(R.id.telefono_input);
        longitud_input = (TextView) v.findViewById(R.id.longitud_input);
        latitud_input = (TextView) v.findViewById(R.id.latitud_input);
        ciudad_spinner = (Spinner) v.findViewById(R.id.ciudad_spinner);
        button_save = (Button) v.findViewById(R.id.save_input);
        button_mapa = (ImageButton) v.findViewById(R.id.map_input);
        idCategoria = getArguments().getString(EXTRA_ID);

        ciudad_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                latitud_input.setText("0");
                longitud_input.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button_mapa.setOnClickListener(this);
        button_save.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.save_input:
                if (!camposVacios()) {
                    guardarSitio();
                }
                break;
            case R.id.map_input:
                Intent intent = new Intent (getActivity(), MapsNuevoSitioActivity.class);
                intent.putExtra("IdCiudad", ciudad_spinner.getSelectedItem().toString());
                String logText = longitud_input.getText().toString();
                if(!logText.isEmpty() || !logText.equals("0") ) {
                    intent.putExtra("Longitud", longitud_input.getText().toString());
                    intent.putExtra("Latitud", latitud_input.getText().toString());
                }
                startActivityForResult(intent,Constantes.CODIGO_MAPS);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constantes.CODIGO_MAPS ) {
            if(resultCode == RESULT_OK) {
                longitud_input.setText(String.valueOf(data.getExtras().getDouble("Longitud")));
                latitud_input.setText(String.valueOf(data.getExtras().getDouble("Latitud")));
            }
        }
    }

    public void guardarSitio() {

        // Obtener valores actuales de los controles
        final String nombre = nombre_input.getText().toString();
        final String descripcion = descripcion_input.getText().toString();
        final String telefono = telefono_input.getText().toString();
        final String ubicacion = ubicacion_input.getText().toString();
        final String longitud = longitud_input.getText().toString();
        final String latitud = latitud_input.getText().toString();
        final String idCiudad = getIdCiudad(ciudad_spinner.getSelectedItem().toString());

        HashMap<String, String> map = new HashMap<>();// Mapeo previo

        map.put("nombre", nombre);
        map.put("descripcion", descripcion);
        map.put("ubicacion", ubicacion);
        map.put("telefono", telefono);
        map.put("idCategoria", idCategoria);
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
                                Toast.makeText(getActivity(),"Error de conexion, no se puedo guardar el registro",
                                        Toast.LENGTH_LONG).show();
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
                    getActivity().setResult(RESULT_OK);
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
        Boolean flat = false;
        if(nombre_input.getText().toString().isEmpty()) {
            nombre_input.setError("Campo requerido");
            flat =true;
        }
        if(ubicacion_input.getText().toString().isEmpty()) {
            ubicacion_input.setError("Campo requerido");
            flat =true;
        }
        if(latitud_input.getText().toString().equals("0")) {
            latitud_input.setError("Diferente de Cero");
            flat =true;
        }
        if(longitud_input.getText().toString().equals("0")) {
            longitud_input.setError("Diferente de Cero");
            flat =true;
        }
        return flat;
    }

    private String getIdCiudad(String ciudad) {
        switch (ciudad) {
            case "Armenia":     return "1";
            case "Cartago":     return "2";
            case "Manizales":   return "3";
            case "Pereira":     return "4";
        }
        return "1";
    }

}
