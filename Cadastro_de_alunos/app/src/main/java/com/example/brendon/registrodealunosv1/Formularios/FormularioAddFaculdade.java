package com.example.brendon.registrodealunosv1.Formularios;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;


import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.dal.App;
import com.example.brendon.registrodealunosv1.R;
import io.objectbox.Box;


public class FormularioAddFaculdade extends AppCompatActivity {

    public static String ID = "idFaculdade";
    public static long DEFAULT_VALUE = -1;

    EditText nome_faculdade, email, contato;
    Box<Faculdade> boxFaculdades;

    Faculdade faculdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_add_faculdade);

        boxFaculdades = ((App)getApplication()).getBoxStore().boxFor(Faculdade.class);
        faculdade = new Faculdade("", "", "");

        nome_faculdade = findViewById(R.id.edt_nome_faculdade);
        email = findViewById(R.id.edt_email);
        contato = findViewById(R.id.edt_contato);

        long id = getIntent().getLongExtra(ID, DEFAULT_VALUE);

        if (id != DEFAULT_VALUE){
            faculdade = boxFaculdades.get(id);
            nome_faculdade.setText(faculdade.getNome());
            email.setText(faculdade.getEmail());
            contato.setText(faculdade.getContatoPrincipal());
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
                String nome_da_faculdade = nome_faculdade.getText().toString();
                String email_faculdade = email.getText().toString();
                String contato_faculdade = contato.getText().toString();

                if (nome_da_faculdade.equals("") || email_faculdade.equals("")){
                    Toast.makeText(this, "Dados insuficientes", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent();
                    boxFaculdades.put(new Faculdade(nome_da_faculdade,email_faculdade,contato_faculdade));
                    Toast.makeText(this, "Nova Faculdade adicionada", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
