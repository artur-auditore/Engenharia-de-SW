package com.example.artur.registrodealunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.example.artur.registrodealunos.Modelo.Faculdade;
import com.example.artur.registrodealunos.dal.ObjectBox;
import io.objectbox.Box;

public class FormularioFaculdadeActivity extends AppCompatActivity {

    private EditText editNomeFaculdade;
    private EditText editEmailFaculdade;
    private EditText editContato;

    private Box<Faculdade> faculdadeBox;
    private Faculdade faculdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_faculdade);

        bind();

    }

    private void bind() {
        faculdade = new Faculdade("", "", "");

        faculdadeBox = ObjectBox.INSTANCE.getBoxStore().boxFor(Faculdade.class);

        editNomeFaculdade = findViewById(R.id.edt_nome_faculdade);
        editEmailFaculdade = findViewById(R.id.edt_email);
        editContato = findViewById(R.id.edt_contato);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.op_salvar: salvarFaculdade();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarFaculdade(){
        String nome = editNomeFaculdade.getText().toString();
        String email = editEmailFaculdade.getText().toString();
        String contato = editContato.getText().toString();

        if (nome.trim().equals("") || email.trim().equals("") || contato.trim().equals("")){
            Toast.makeText(this, "Dados insuficientes", Toast.LENGTH_LONG).show();
        } else {

            faculdade.setNome(nome);
            faculdade.setEmail(email);
            faculdade.setContatoPrincipal(contato);
            faculdadeBox.put(faculdade);
            finish();
        }
    }
}
