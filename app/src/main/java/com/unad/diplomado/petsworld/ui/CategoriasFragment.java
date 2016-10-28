package com.unad.diplomado.petsworld.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unad.diplomado.petsworld.R;

/**
 * Created by UserSistemas on 28/10/2016.
 */
public class CategoriasFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content_main,container,false);
        return  root;
    }
}
