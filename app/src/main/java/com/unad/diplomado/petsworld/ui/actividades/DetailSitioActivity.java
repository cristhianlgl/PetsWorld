package com.unad.diplomado.petsworld.ui.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Categoria;
import com.unad.diplomado.petsworld.domain.Sitio;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.fragmentos.DetalleSitioFragment;

public class DetailSitioActivity extends AppCompatActivity {

    private Sitio sitio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitios_detalle);

        // Retener instancia
        if(getIntent().getExtras().getSerializable(Constantes.EXTRA_SITIO) != null) {
            sitio = (Sitio) getIntent().getExtras().getSerializable(Constantes.EXTRA_SITIO);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layout_sitios_detalle, DetalleSitioFragment.createInstance(sitio), "DetailFragment")
                    .commit();
        }
    }

}

