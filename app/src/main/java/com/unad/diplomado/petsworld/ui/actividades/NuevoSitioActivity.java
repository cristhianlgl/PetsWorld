package com.unad.diplomado.petsworld.ui.actividades;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.fragmentos.ConfirmDialogFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.NuevoSitioFragment;

public class NuevoSitioActivity extends AppCompatActivity implements ConfirmDialogFragment.ConfirmDialogListener{

    private String idCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sitio);

        // Retener instancia
        if(getIntent().getExtras().getSerializable(Constantes.EXTRA_ID_CATEGORIA) != null) {
            idCategoria =  getIntent().getExtras().getString(Constantes.EXTRA_ID_CATEGORIA);
        }

          // Creación del fragmento de inserción
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_nuevo_sitio, NuevoSitioFragment.createInstance(idCategoria), "NuevoSitioFragment")
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
