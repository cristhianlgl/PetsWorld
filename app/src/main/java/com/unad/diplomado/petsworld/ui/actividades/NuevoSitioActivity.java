package com.unad.diplomado.petsworld.ui.actividades;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.ui.fragmentos.ConfirmDialogFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.NuevoSitioFragment;

public class NuevoSitioActivity extends AppCompatActivity implements ConfirmDialogFragment.ConfirmDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sitio);

        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);


        // Creación del fragmento de inserción
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_nuevo_sitio, new NuevoSitioFragment(), "NuevoSitioFragment")
                    .commit();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        NuevoSitioFragment insertFragment = (NuevoSitioFragment)
                getSupportFragmentManager().findFragmentByTag("NuevoSitioFragment");

        if (insertFragment != null) {
            finish(); // Finalizar actividad descartando cambios
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        NuevoSitioFragment insertFragment = (NuevoSitioFragment)
                getSupportFragmentManager().findFragmentByTag("NuevoSitioFragment");

        if (insertFragment != null) {
            // Nada por el momento
        }
    }
}
