package com.unad.diplomado.petsworld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unad.diplomado.petsworld.domain.Categoria;

import java.util.List;

/**
 * Created by cristhian on 22/10/2016.
 */
public class CategoriaAdater extends RecyclerView.Adapter<CategoriaAdater.CategroriaViewHolder>{
    private List<Categoria> categorias;

    public static class CategroriaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;

        public CategroriaViewHolder(View v){
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen_categoria);
            nombre = (TextView) v.findViewById(R.id.nombre_categroria);
        }
    }

    public CategoriaAdater(List<Categoria> categorias) {this.categorias = categorias; }

    @Override
    public  int getItemCount(){ return categorias.size(); }

    @Override
    public  CategroriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categoria_card, viewGroup, false);
        return new CategroriaViewHolder(v);
    }

    @Override
    public  void onBindViewHolder (CategroriaViewHolder viewHolder, int i){
        viewHolder.imagen.setImageResource(categorias.get(i).getImagen());
        viewHolder.nombre.setText(categorias.get(i).getNombre());
    }
}
