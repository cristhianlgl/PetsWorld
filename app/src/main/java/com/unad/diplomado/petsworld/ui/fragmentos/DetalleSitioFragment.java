package com.unad.diplomado.petsworld.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.unad.diplomado.petsworld.ui.actividades.MapsActivity;

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
    private Button button;
    private Sitio sitio;
    private String extra;
    private Gson gson = new Gson();


    public static DetalleSitioFragment createInstance(Sitio sitio_param) {
        DetalleSitioFragment detailFragment = new DetalleSitioFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ID, sitio_param);
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
        button = (Button) v.findViewById(R.id.ver_mapa);

        // Obtener extra del intent de envío
        extra = getArguments().getString(EXTRA_ID);
        sitio = (Sitio)getArguments().getSerializable(EXTRA_ID);

        if (sitio != null) {
            nombre.setText(sitio.getNombre());
            descripcion.setText(sitio.getDescripcion());
            ciudad.setText(sitio.getCiudad());
            telefono.setText(sitio.getTelefono());
            ubicacion.setText(sitio.getUbicacion());
        }else{
            Toast.makeText( getActivity(), "No se ha podido obtener los datos del sitio",
                    Toast.LENGTH_LONG).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra(Constantes.EXTRA_SITIO_MAPS, sitio);
                startActivity(intent);
            }
        });

        return v;
    }
}