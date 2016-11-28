package com.unad.diplomado.petsworld.ui.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unad.diplomado.petsworld.R;

public class MapsNuevoSitioActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker markerNuevoSitio;
    private TextView locationText;
    private String ciudad;
    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_nuevo_sitio);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        }

        Bundle extras = getIntent().getExtras();
        ciudad = extras.getString("IdCiudad","1");
        longitud = Double.parseDouble(extras.getString("Longitud","0"));
        latitud = Double.parseDouble(extras.getString("Latitud","0"));

        locationText = (TextView) findViewById(R.id.location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent data = new Intent();
                data.putExtra("Latitud",latitud);
                data.putExtra("Longitud",longitud);
                this.setResult(RESULT_OK, data);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng position;
        if(longitud == 0 && latitud == 0){
            position = locacionDefaultSitio(ciudad);
        } else {
            position = new LatLng(latitud,longitud);
        }
        setUbicacionActual(position);
        markerNuevoSitio = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Nuevo Sitio")
                .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,15));
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        int permissionCheck = ContextCompat.checkSelfPermission(MapsNuevoSitioActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == 0) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerNuevoSitio)){
            setUbicacionActual(marker.getPosition());
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if ( latLng != markerNuevoSitio.getPosition()){
            markerNuevoSitio.setPosition(latLng);
            setUbicacionActual(latLng);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerNuevoSitio)){
            setUbicacionActual(marker.getPosition());
        }
    }

    private LatLng locacionDefaultSitio(String idCiudad){
        //por defecto la direccion de cartego
        LatLng posicion = new LatLng(4.749659, -75.913154);
        switch (idCiudad){
            case "Armenia":  posicion = new LatLng(4.538771, -75.672570); break;
            case "Cartago":  posicion = new LatLng(4.749659, -75.913154); break;
            case "Manizales":  posicion = new LatLng(5.066744, -75.516603); break;
            case "Pereira":  posicion = new LatLng(4.813943, -75.693091); break;
        }
        return posicion;
    }

    private void setUbicacionActual(LatLng latLng ){
        latitud = latLng.latitude;
        longitud = latLng.longitude;
        locationText.setText("Latitud: " + latitud + "  y Longitud: " + longitud );
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

}
