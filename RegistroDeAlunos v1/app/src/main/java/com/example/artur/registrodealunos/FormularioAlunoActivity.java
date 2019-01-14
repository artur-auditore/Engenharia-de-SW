package com.example.artur.registrodealunos;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.artur.registrodealunos.Modelo.Aluno;
import com.example.artur.registrodealunos.dal.ObjectBox;
import io.objectbox.Box;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static String ID = "id";
    public static long DEFAULT_VALUE = -1;

    private EditText editNome;
    private EditText editCurso;
    private Spinner spinnerFaculdade;

    private Box<Aluno> alunoBox;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        alunoBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Aluno.class);
        setUpViews();
        aluno = new Aluno("", "");

        long id = getIntent().getLongExtra(ID, DEFAULT_VALUE);

        if (id != DEFAULT_VALUE){
            aluno = alunoBox.get(id);
            editNome.setText(aluno.getNome());
            editCurso.setText(aluno.getCurso());
        }

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
            case R.id.op_salvar:
                salvar();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        String nome = editNome.getText().toString();
        String curso = editCurso.getText().toString();

        if (nome.trim().equals("") || curso.trim().equals("")){
            Toast.makeText(this, "Insira dados válidos!", Toast.LENGTH_LONG).show();
        } else {

            aluno.setNome(nome);
            aluno.setCurso(curso);
            alunoBox.put(aluno);
            finish();
        }

    }
}
