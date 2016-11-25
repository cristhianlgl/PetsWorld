package com.unad.diplomado.petsworld.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Sitio;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.actividades.DetailSitioActivity;

import java.util.List;

/**
 * Created by Fizz on 29/10/2016.
 */
public class SitioAdapter extends RecyclerView.Adapter<SitioAdapter.SitioViewHolder>
        implements ItemClickListener {

    private List<Sitio> items;
    private Context context;

    public SitioAdapter(List<Sitio> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {  return items.size(); }

    @Override
    public SitioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sitios, viewGroup, false);
        return new SitioViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(SitioAdapter.SitioViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.ciudad.setText(items.get(i).getCiudad());
        //mostrar imagen segun la categoria
        int imagen= R.drawable.parques;
        int fondo = R.drawable.button_circle_naranja;
        switch (items.get(i).getIdCategoria()) {
            case "2": fondo = R.drawable.button_circle_azul; imagen = R.drawable.comidas;
                break;
            case "3": fondo = R.drawable.button_circle_violeta; imagen = R.drawable.veterinarias;
                break;
            case "4": fondo = R.drawable.button_circle_verde; imagen = R.drawable.tiendas;
                break;
            case "5": fondo = R.drawable.button_circle_rojo; imagen = R.drawable.servicios;
                break;
        }

        viewHolder.imagen.setBackgroundResource(fondo);
        viewHolder.imagen.setImageResource(imagen);

        //cambia el color de la imagen de verificado para indicar si el sitio ya fue verificado o no
        if(items.get(i).getVerificado().equals("1")){
            viewHolder.imagenVerificado.setBackgroundResource(R.drawable.button_circle_primary);
        } else {
            viewHolder.imagenVerificado.setBackgroundResource(R.drawable.button_circle_gris);
        }
    }

    @Override
    public void onItemClick(View view, int i) {
        Intent intent = new Intent(view.getContext(), DetailSitioActivity.class);
        intent.putExtra(Constantes.EXTRA_SITIO, items.get(i));
        view.getContext().startActivity(intent);
    }

    public static class SitioViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView nombre;
        public TextView ciudad;
        public ImageView imagen;
        public ImageView imagenVerificado;
        public ItemClickListener listener;

        public SitioViewHolder (View v, ItemClickListener listener){
            super(v);
            nombre = (TextView) v.findViewById(R.id.item_sitio_nombre);
            ciudad = (TextView) v.findViewById(R.id.item_sitio_ciudad);
            imagen = (ImageView) v.findViewById(R.id.item_sitio_imagen);
            imagenVerificado = (ImageView) v.findViewById(R.id.imagen_verificado);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void  onClick (View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}

interface ItemClickListener {
    void onItemClick(View view, int position);
}
