package com.example.artur.registrodealunos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.example.artur.registrodealunos.Adapter.AlunoAdapter
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {

    private lateinit var alunosBox: Box<Aluno>
    private lateinit var fabNovoAluno: FloatingActionButton
    private lateinit var rvAlunos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        alunosBox = ObjectBox.boxStore.boxFor(Aluno::class.java)
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
}
