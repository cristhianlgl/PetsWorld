package com.unad.diplomado.petsworld.ui.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.tools.Constantes;

/**
 * Created by Fizz on 29/10/2016.
 */
public class SitiosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SitiosFrament(), "SitiosFrament")
                    .commit();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == Constantes.CODIGO_DETALLE || requestCode == 3) {
                if (resultCode == RESULT_OK || resultCode == 203) {
                    SitiosFrament fragment = (SitiosFrament) getSupportFragmentManager().
                            findFragmentByTag("SitiosFrament");
                    // fragment.cargarAdaptador();
                }
            }
        }
    }
}
