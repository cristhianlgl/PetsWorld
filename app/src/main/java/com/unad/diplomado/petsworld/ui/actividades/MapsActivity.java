package com.unad.diplomado.petsworld.ui.actividades;

import android.Manifest;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Sitio;
import com.unad.diplomado.petsworld.tools.Constantes;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Sitio sitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        sitio = (Sitio)getIntent().getExtras().getSerializable(Constantes.EXTRA_SITIO_MAPS);
        Log.i("prueba maps",sitio.getLatitud());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng miPuntoSitio = new LatLng( sitio.getLatitudDouble(), sitio.getLongitudDouble());
        mMap.addMarker(new MarkerOptions().position(miPuntoSitio).title(sitio.getNombre()).snippet(sitio.getDescripcion()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miPuntoSitio));

        int permissionCheck = ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == 0){
            mMap.setMyLocationEnabled(true);
        }
    }
}
