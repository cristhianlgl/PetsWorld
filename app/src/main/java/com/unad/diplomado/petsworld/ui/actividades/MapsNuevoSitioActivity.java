package com.unad.diplomado.petsworld.ui.actividades;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Sitio;
import com.unad.diplomado.petsworld.io.GetAddressTask;
import com.unad.diplomado.petsworld.tools.Constantes;

public class MapsNuevoSitioActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker markerNuevoSitio;
    private TextView locationText;
    private TextView addressText;
    private Sitio sitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_nuevo_sitio);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        }

//        sitio = (Sitio)getIntent().getExtras().getSerializable(Constantes.EXTRA_SITIO_MAPS);
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
                this.setResult(1);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng position = locacionDefaultSitio(1);
        markerNuevoSitio = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Nuevo Sitio")
                .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

        mMap.getUiSettings().setZoomGesturesEnabled(true);

        //set Map TYPE
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        int permissionCheck = ContextCompat.checkSelfPermission(MapsNuevoSitioActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == 0) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapClickListener(this);
        //set "listener" for changing my location
        //mMap.setOnMyLocationChangeListener(myLocationChangeListener());

    }



    @Override
    public boolean onMarkerClick(Marker marker) {
        double latitude =  marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;
        if (marker.equals(markerNuevoSitio)){
            locationText.setText("Latitud: " + latitude + "  y Longitud: " + longitude );
        } else {
            markerNuevoSitio.setPosition(marker.getPosition());
        }

        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if ( latLng != markerNuevoSitio.getPosition()){
            markerNuevoSitio.setPosition(latLng);
        }
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
        return new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                Marker marker;
                marker = mMap.addMarker(new MarkerOptions().position(loc));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                locationText.setText("You are at [" + longitude + " ; " + latitude + " ]");

                //get current address by invoke an AsyncTask object
                //new GetAddressTask(MapsNuevoSitioActivity.this).execute(String.valueOf(latitude), String.valueOf(longitude));
            }
        };
    }

    private LatLng locacionDefaultSitio(int idCiudad){
        //por defecto la direccion de cartego
        LatLng posicion = new LatLng(4.749659, -75.913154);
        switch (idCiudad){
            case 1:  posicion = new LatLng(4.538771, -75.672570); break; //aremenia
            case 2:  posicion = new LatLng(4.749659, -75.913154); break; //cartago
            case 3:  posicion = new LatLng(5.066744, -75.516603); break; //manizales
            case 4:  posicion = new LatLng(4.813943, -75.693091); break; //pereira
        }
        return posicion;
    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


}
