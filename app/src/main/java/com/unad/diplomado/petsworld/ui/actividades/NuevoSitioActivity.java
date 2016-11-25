package com.unad.diplomado.petsworld.ui.actividades;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.fragmentos.ConfirmDialogFragment;
import com.unad.diplomado.petsworld.ui.fragmentos.NuevoSitioFragment;

public class NuevoSitioActivity extends AppCompatActivity {

    private String idCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_sitio);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevor_sitio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
            case R.id.action_discard:
                this.setResult(1);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
