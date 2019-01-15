package com.example.brendon.registrodealunosv1.Formularios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brendon.registrodealunosv1.Models.Aluno;
import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.R;
import com.example.brendon.registrodealunosv1.dal.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.objectbox.Box;

public class FormularioAddAluno extends AppCompatActivity {

    public static String ID = "idAluno";
    public static long DEFAULT_VALUE = -1;

    EditText nome_aluno, curso;
    Spinner spin_faculdades;
    Box<Faculdade> boxFaculdades;
    Box<Aluno> boxAlunos;
    List<String> lista_de_items = new ArrayList<>();
    Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_add_aluno);

        boxFaculdades = ((App)getApplication()).getBoxStore().boxFor(Faculdade.class);
        boxAlunos = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        aluno = new Aluno("", "", "");

        for (int i =0; i< boxFaculdades.getAll().size(); i++){
            Faculdade faculdadeAtual = this.boxFaculdades.getAll().get(i);
            lista_de_items.add(faculdadeAtual.getNome());
        }


        nome_aluno = findViewById(R.id.nome_aluno);
        curso = findViewById(R.id.curso);
        spin_faculdades = findViewById(R.id.spinner_faculdades);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista_de_items );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_faculdades.setAdapter(dataAdapter);

        //Editar
        long id = getIntent().getLongExtra(ID, DEFAULT_VALUE);

        if (id != DEFAULT_VALUE){
            aluno = boxAlunos.get(id);
            nome_aluno.setText(aluno.getNome());
            curso.setText(aluno.getCurso());
        }

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
                String nome_do_aluno = nome_aluno.getText().toString();
                String nome_curso = curso.getText().toString();
                String faculdade = spin_faculdades.getSelectedItem().toString();

                if (nome_do_aluno.equals("") || nome_curso.equals("") ){
                    Toast.makeText(this, "Dados insuficientes", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent();
                    aluno.setNome(nome_do_aluno);
                    aluno.setCurso(nome_curso);
                    aluno.setFaculdade(faculdade);
                    boxAlunos.put(aluno);
                    Toast.makeText(this, "Novo aluno adicionado", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
        }

        return super.onOptionsItemSelected(item);
    }

}



