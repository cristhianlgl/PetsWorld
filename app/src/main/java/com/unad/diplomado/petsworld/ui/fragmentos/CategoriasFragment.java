package com.unad.diplomado.petsworld.ui.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unad.diplomado.petsworld.ui.adapter.CategoriaAdater;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.domain.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UserSistemas on 28/10/2016.
 */
public class CategoriasFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categorias,container,false);

        //obtener el recycler
        mRecyclerView = (RecyclerView) root.findViewById(R.id.menu_reciclador);
        mRecyclerView.setHasFixedSize(true);

        //usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(lManager);

        //crear un nuevo adaptador
        adapter = new CategoriaAdater(datosSetup());
        mRecyclerView.setAdapter(adapter);

        return  root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Pets World");
    }

    private List<Categoria> datosSetup() {
        // inicializa las categorias
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("1", R.drawable.parques, "Parques", R.drawable.button_naranja));
        categorias.add(new Categoria("2", R.drawable.comidas, "Comida", R.drawable.button_azul));
        categorias.add(new Categoria("3", R.drawable.veterinarias, "Veterinarias", R.drawable.buttton_violeta));
        categorias.add(new Categoria("4", R.drawable.tiendas, "Tiendas Pets", R.drawable.button_verde));
        categorias.add(new Categoria("5", R.drawable.servicios, "Servicios", R.drawable.button_rojo));
        return categorias;
    }
}
