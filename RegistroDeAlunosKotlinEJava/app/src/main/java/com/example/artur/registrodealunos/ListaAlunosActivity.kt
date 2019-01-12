package com.example.artur.registrodealunos

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import com.example.artur.registrodealunos.Adapter.AlunoAdapter
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.Modelo.Faculdade
import com.example.artur.registrodealunos.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_lista_alunos.*
import kotlinx.android.synthetic.main.formulario_faculdade.view.*

class ListaAlunosActivity : AppCompatActivity() {

    private lateinit var alunosBox: Box<Aluno>
    private lateinit var fabNovoAluno: FloatingActionButton
    private lateinit var rvAlunos: RecyclerView

    private lateinit var faculdadeBox: Box<Faculdade>
    private lateinit var faculdade: Faculdade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        alunosBox = ObjectBox.boxStore.boxFor(Aluno::class.java)
        faculdadeBox = ObjectBox.boxStore.boxFor(Faculdade::class.java)
        rvAlunos = rv_alunos
        fabNovoAluno = fab_novo_aluno

        fabNovoAluno.setOnClickListener { it ->
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()

        rvAlunos.adapter = AlunoAdapter(this, alunosBox)
        rvAlunos.layoutManager = LinearLayoutManager(this)
        rvAlunos.hasFixedSize()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_variado, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId){
            R.id.nova_faculdade -> novaFaculdade()
            R.id.lista_faculdades -> listarFaculdades()
            R.id.alunos_diplomados -> alunosDiplomados()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("InflateParams")
    private fun novaFaculdade(){
        val alertDialog = AlertDialog.Builder(this)

        val viewDialog = layoutInflater.inflate(R.layout.formulario_faculdade, null)
        alertDialog.setView(viewDialog)
            .setTitle("Nova Faculdade")
            .setPositiveButton("Salvar"){dialog, which ->
                val editNome = viewDialog.edit_nome_faculdade
                val editEmail = viewDialog.edit_email_faculdade
                val editContato = viewDialog.edit_contato_principal

                val nome = editNome.text.toString()
                val email = editEmail.text.toString()
                val contatoPrincipal = editContato.text.toString()

                if (nome.trim() == "" || email.trim() == "" || contatoPrincipal.trim() == ""){
                    Toast.makeText(this, "Insira dados válidos", Toast.LENGTH_LONG).show()
                } else {

                    faculdade = Faculdade(nome, email, contatoPrincipal)
                    faculdadeBox.put(faculdade)
                }

            }
            .setNegativeButton("Cancelar"){_, _ ->}
            .create()
            .show()
    }

    private fun listarFaculdades(){
        val alertDialog = AlertDialog.Builder(this)

    }

    private fun alunosDiplomados(){

    }
}
