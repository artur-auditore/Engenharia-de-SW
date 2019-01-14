package com.example.artur.registrodealunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.artur.registrodealunos.Modelo.Aluno;
import com.example.artur.registrodealunos.Modelo.Faculdade;
import com.example.artur.registrodealunos.dal.ObjectBox;
import io.objectbox.Box;

import java.util.ArrayList;
import java.util.List;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static String ID = "id";
    public static long DEFAULT_VALUE = -1;

    private EditText editNome;
    private EditText editCurso;
    private Spinner spinnerFaculdade;

    private Box<Aluno> alunoBox;
    private Aluno aluno;
    private Box<Faculdade> faculdadeBox;
    Faculdade faculdadeAtual;

    List<String> listaDeItens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        alunoBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Aluno.class);
        faculdadeBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Faculdade.class);
        setUpViews();
        aluno = new Aluno("", "");


        for (int i = 0; i < faculdadeBox.getAll().size(); i++) {
            faculdadeAtual = this.faculdadeBox.getAll().get(i);
            listaDeItens.add(faculdadeAtual.getNome());
        }
        faculdadeAtual = faculdadeAtual();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, listaDeItens);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFaculdade.setAdapter(dataAdapter);

        long id = getIntent().getLongExtra(ID, DEFAULT_VALUE);

        if (id != DEFAULT_VALUE) {
            aluno = alunoBox.get(id);
            editNome.setText(aluno.getNome());
            editCurso.setText(aluno.getCurso());
        }
    }

    private Faculdade faculdadeAtual(){
        spinnerFaculdade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                faculdadeAtual = faculdadeBox.getAll().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return faculdadeAtual;
    }

    private void setUpViews(){
        editNome = findViewById(R.id.edit_nome);
        editCurso = findViewById(R.id.edit_curso);
        spinnerFaculdade = findViewById(R.id.spinner_faculdade);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.op_salvar: salvar();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvar(){

        String nome = editNome.getText().toString();
        String curso = editCurso.getText().toString();

        if (nome.trim().equals("") || curso.trim().equals("") || spinnerFaculdade.isSelected()){
            Toast.makeText(this, "Insira dados válidos!", Toast.LENGTH_LONG).show();
        } else {

            aluno.setNome(nome);
            aluno.setCurso(curso);
            aluno.getFaculdade().setTarget(faculdadeAtual);
            faculdadeAtual.getAlunos().add(aluno);
            alunoBox.put(aluno);
            finish();
        }

    }
}
