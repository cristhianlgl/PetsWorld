package com.unad.diplomado.petsworld.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Sitio;
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
        viewHolder.descripcion.setText(items.get(i).getDescripcion());
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
    }

    @Override
    public void onItemClick(View view, int i) {
        DetailSitioActivity.launch(
                (Activity) context, items.get(i).getId());
    }

    public static class SitioViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView nombre;
        public TextView descripcion;
        public TextView ciudad;
        public ImageView imagen;
        public ItemClickListener listener;

        public SitioViewHolder (View v, ItemClickListener listener){
            super(v);
            nombre = (TextView) v.findViewById(R.id.item_sitio_nombre);
            descripcion = (TextView) v.findViewById(R.id.item_sitio_descripcion);
            ciudad = (TextView) v.findViewById(R.id.item_sitio_ciudad);
            imagen = (ImageView) v.findViewById(R.id.item_sitio_imagen);
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
