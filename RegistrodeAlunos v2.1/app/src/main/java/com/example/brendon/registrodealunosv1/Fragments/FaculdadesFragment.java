package com.example.brendon.registrodealunosv1.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.brendon.registrodealunosv1.Adapters.FaculdadeAdapter;
import com.example.brendon.registrodealunosv1.Formularios.FormularioAddAluno;
import com.example.brendon.registrodealunosv1.Formularios.FormularioAddFaculdade;
import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.Models.MyObjectBox;
import com.example.brendon.registrodealunosv1.R;
import com.example.brendon.registrodealunosv1.dal.App;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

import static android.app.Activity.RESULT_OK;

public class FaculdadesFragment extends Fragment {

    View myView;
    Box<Faculdade> boxFaculdades;
    RecyclerView recicleFacudades;
    public static final int REQUEST_CODE = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.faculdades_layout, container, false);
        boxFaculdades = ((App) myView.getContext().getApplicationContext()).getBoxStore().boxFor(Faculdade.class);

        recicleFacudades = myView.findViewById(R.id.recicle_faculdades);
        FaculdadeAdapter adapter = new FaculdadeAdapter(getContext(), boxFaculdades);
        recicleFacudades.setLayoutManager(new LinearLayoutManager(getContext()));
        recicleFacudades.setAdapter(adapter);

        FloatingActionButton fab = myView.findViewById(R.id.fab_add_faculdade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myView.getContext(), FormularioAddFaculdade.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        return myView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                FaculdadeAdapter adapter = new FaculdadeAdapter(getContext(), boxFaculdades);
                recicleFacudades.setLayoutManager(new LinearLayoutManager(getContext()));
                recicleFacudades.setAdapter(adapter);

            }
        }
    }
}
