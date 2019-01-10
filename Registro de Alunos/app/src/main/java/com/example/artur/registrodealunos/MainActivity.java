package com.example.artur.registrodealunos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.artur.registrodealunos.Adapter.AlunoAdapter;
import com.example.artur.registrodealunos.Modelo.Aluno;
import com.example.artur.registrodealunos.dal.ObjectBox;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {

    private Box<Aluno> alunoBox;
    private RecyclerView rvAlunos;

    FloatingActionButton fabNovoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alunoBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Aluno.class);
        setUpViews();

        fabNovoAluno.setOnClickListener(view ->{
            startActivity(new Intent(this, FormularioActivity.class));
        });

        rvAlunos.setAdapter(new AlunoAdapter(alunoBox.getAll(), this));
        rvAlunos.setLayoutManager(new LinearLayoutManager(this));
        rvAlunos.hasFixedSize();
    }

    private void setUpViews(){
        rvAlunos = findViewById(R.id.rv_alunos);
        fabNovoAluno = findViewById(R.id.fab_novo_aluno);
    }
}
