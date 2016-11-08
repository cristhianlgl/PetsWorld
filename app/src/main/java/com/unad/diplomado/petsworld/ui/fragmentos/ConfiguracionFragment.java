package com.unad.diplomado.petsworld.ui.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.unad.diplomado.petsworld.R;

/**
 * Created by Fizz on 01/11/2016.
 */
public class ConfiguracionFragment extends PreferenceFragmentCompat {

    public ConfiguracionFragment() {
        // Constructor Por Defecto
       }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Configuracion");
    }

}
