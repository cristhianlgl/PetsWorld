package com.unad.diplomado.petsworld.io;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.unad.diplomado.petsworld.ui.actividades.MapsNuevoSitioActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Fizz on 27/11/2016.
 */

public class GetAddressTask extends AsyncTask<String, Void, String> {

    private MapsNuevoSitioActivity activity;

    public GetAddressTask(MapsNuevoSitioActivity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(params[0]), Double.parseDouble(params[1]), 1);

            //get current Street name
            String address = addresses.get(0).getAddressLine(0);

            //get current province/City
            String province = addresses.get(0).getAdminArea();

            //get country
            String country = addresses.get(0).getCountryName();

            //get postal code
            String postalCode = addresses.get(0).getPostalCode();

            //get place Name
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            return "Street: " + address + "\n" + "City/Province: " + province + "\nCountry: " + country
                    + "\nPostal CODE: " + postalCode + "\n" + "Place Name: " + knownName;

        } catch (IOException ex) {
            ex.printStackTrace();
            return "IOE EXCEPTION";

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return "IllegalArgument Exception";
        }

    }


}