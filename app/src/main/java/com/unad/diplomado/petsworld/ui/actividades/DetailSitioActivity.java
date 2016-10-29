package com.unad.diplomado.petsworld.ui.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.tools.Constantes;

public class DetailSitioActivity extends AppCompatActivity {

    private String idSitio;

    public static void launch (Activity activity, String idSitio) {
        Intent intent = getLaunchIntent(activity, idSitio);
        activity.startActivityForResult(intent, Constantes.CODIGO_DETALLE);
    }

    public static  Intent getLaunchIntent(Context context, String idSitio) {
        Intent intent = new Intent(context, DetailSitioActivity.class);
        intent.putExtra(Constantes.EXTRA_ID_SITIO, idSitio);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getStringExtra(Constantes.EXTRA_ID_SITIO) != null ){
            idSitio=getIntent().getStringExtra(Constantes.EXTRA_ID_SITIO);
        }

        if(savedInstanceState == null){
          //  getSupportFragmentManager().beginTransaction()
          //     .add(R.id.c,Detai)
        }
    }


}
