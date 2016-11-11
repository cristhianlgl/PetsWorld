package com.unad.diplomado.petsworld.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.unad.diplomado.petsworld.MainActivity;
import com.unad.diplomado.petsworld.R;
import com.unad.diplomado.petsworld.ui.actividades.MapsActivity;

/**
 * Created by Fizz on 30/10/2016.
 */
public class AcercadeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        View v  = inflater.inflate(R.layout.fragment_acercade, container, false);
        Button button = (Button) v.findViewById(R.id.button_mapa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Acerca de");
    }
}


