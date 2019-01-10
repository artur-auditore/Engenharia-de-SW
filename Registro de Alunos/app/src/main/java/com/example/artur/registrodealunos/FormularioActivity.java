package com.example.artur.registrodealunos;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.artur.registrodealunos.Modelo.Aluno;
import com.example.artur.registrodealunos.dal.App;
import com.example.artur.registrodealunos.dal.ObjectBox;

import io.objectbox.Box;

public class FormularioActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editCurso;

    private Box<Aluno> alunoBox;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        alunoBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Aluno.class);

        //Pra editar depois
        aluno = new Aluno("", "");
        setUpViews();

    }

    private void setUpViews(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.op_salvar:
                salvar();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        String nome = editNome.getText().toString();
        String curso = editCurso.getText().toString();

        aluno = new Aluno(nome, curso);
        //faculdade selecionada pelo spinner aqui (faculdade.alunos.add(aluno);
        alunoBox.put(aluno);
        finish();
    }

}
