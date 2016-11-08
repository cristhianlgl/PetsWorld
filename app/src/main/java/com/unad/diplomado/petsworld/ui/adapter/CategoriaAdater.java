package com.unad.diplomado.petsworld.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unad.diplomado.petsworld.MainActivity;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Categoria;
import com.unad.diplomado.petsworld.tools.Constantes;
import com.unad.diplomado.petsworld.ui.fragmentos.SitiosFragment;

import java.util.List;

/**
 * Created by cristhian on 22/10/2016.
 */
public class CategoriaAdater extends RecyclerView.Adapter<CategoriaAdater.CategroriaViewHolder> {
    private List<Categoria> mCategorias;
    private Fragment mFragment;
    private Bundle mBundle;
    public Context mContext;


    public static class CategroriaViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView nombre;
        public LinearLayout linearLayout;

        public CategroriaViewHolder(View v){
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen_categoria);
            nombre = (TextView) v.findViewById(R.id.nombre_categroria);
            linearLayout = (LinearLayout) v.findViewById(R.id.menu_contenedor_fondo);
        }
    }

    public CategoriaAdater(List<Categoria> categorias) {this.mCategorias = categorias; }

    @Override
    public  int getItemCount(){ return mCategorias.size(); }

    @Override
    public  CategroriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_categoria, viewGroup, false);
        return new CategroriaViewHolder(v);
    }

    @Override
    public  void onBindViewHolder (CategroriaViewHolder viewHolder, int i){
        final Categoria item = mCategorias.get(i);
        viewHolder.imagen.setImageResource(item.getImagen());
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.linearLayout.setBackgroundResource(item.getColor());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                fragmentJump(item);
            }
        });
    }

    private void fragmentJump(Categoria mItemSelected) {
        mFragment = new SitiosFragment();
        mBundle = new Bundle();
        mBundle.putString(Constantes.EXTRA_ID_CATEGORIA, mItemSelected.getIdCategoria());
        mFragment.setArguments(mBundle);
        if (mContext == null)
            return;
        if (mContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.setTitle(mItemSelected.getNombre());
            mainActivity.cambiarItemMenuSelecionado(mItemSelected.getIdCategoria());
            mainActivity.mostrarFragment(mFragment);
        }
    }


}
