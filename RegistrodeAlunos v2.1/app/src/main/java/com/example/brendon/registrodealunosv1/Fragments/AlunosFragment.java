package com.example.brendon.registrodealunosv1.Fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.content.Intent;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;

import com.example.brendon.registrodealunosv1.Formularios.FormularioAddAluno;
import com.example.brendon.registrodealunosv1.Adapters.AlunoAdapter;
import com.example.brendon.registrodealunosv1.Models.Aluno;
import com.example.brendon.registrodealunosv1.dal.App;
import com.example.brendon.registrodealunosv1.R;
import static android.app.Activity.RESULT_OK;
import io.objectbox.Box;

public class AlunosFragment extends Fragment {

    View myView;
    Box<Aluno> boxAlunos;
    RecyclerView recicleAlunos;
    public static final int REQUEST_CODE = 1001;
    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.alunos_layout,container,false);
        boxAlunos = ((App) myView.getContext().getApplicationContext()).getBoxStore().boxFor(Aluno.class);

        recicleAlunos = myView.findViewById(R.id.recicle_alunos);
        AlunoAdapter adapter = new AlunoAdapter(getContext(), boxAlunos);
        recicleAlunos.setLayoutManager(new LinearLayoutManager(getContext()));
        recicleAlunos.setAdapter(adapter);

        fab = myView.findViewById(R.id.fab_add_aluno);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(myView.getContext(), FormularioAddAluno.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        return myView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                AlunoAdapter adapter = new AlunoAdapter(getContext(), boxAlunos);
                recicleAlunos.setLayoutManager(new LinearLayoutManager(getContext()));
                recicleAlunos.setAdapter(adapter);

            }
        }
    }

}

