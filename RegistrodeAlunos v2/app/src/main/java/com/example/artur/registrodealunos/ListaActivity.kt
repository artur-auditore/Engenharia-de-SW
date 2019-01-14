package com.example.artur.registrodealunos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.artur.registrodealunos.Adapter.AlunoAdapter
import com.example.artur.registrodealunos.Adapter.FaculdadeAdapter
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.Modelo.Faculdade
import com.example.artur.registrodealunos.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.app_bar_lista.*
import kotlinx.android.synthetic.main.content_lista.*
import android.app.SearchManager
import android.content.Context
import com.example.artur.registrodealunos.Modelo.Aluno_

class ListaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var alunosBox: Box<Aluno>
    private lateinit var fabNovoAluno: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var alunoAdapter: AlunoAdapter

    private lateinit var faculdadeBox: Box<Faculdade>
    private lateinit var faculdade: Faculdade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        alunosBox = ObjectBox.boxStore.boxFor(Aluno::class.java)
        faculdadeBox = ObjectBox.boxStore.boxFor(Faculdade::class.java)
        recyclerView = rv_alunos
        fabNovoAluno = fab

        handlerIntent(intent)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        alunoAdapter = AlunoAdapter(this, alunosBox.all, alunosBox)
        recyclerView.adapter = alunoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
    }

    private fun handlerIntent(intent: Intent?){
        if (Intent.ACTION_SEARCH == intent!!.action){
            val query = intent.getStringExtra(SearchManager.QUERY)

            val queryBuilder = alunosBox.query()
            queryBuilder.contains(Aluno_.nome, query)
            queryBuilder.contains(Aluno_.curso, query)

            val queryAluno = queryBuilder.build()
            val alunos = queryAluno.find()

            alunoAdapter.setAlunos(alunos)
            alunoAdapter.notifyDataSetChanged()

            Toast.makeText(this, "Resultados: " + alunos.size, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        handlerIntent(intent)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.lista, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as android.widget.SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        menu.findItem(R.id.search).setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                return true
            }
        })

        searchView.setOnClickListener {
            alunoAdapter.setAlunos(alunosBox.all)
            alunoAdapter.notifyDataSetChanged()
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.nova_faculdade -> novaFaculdade()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("InflateParams")
    private fun novaFaculdade(){
       startActivity(Intent(this, FormularioFaculdadeActivity::class.java))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.inicio -> {

            }
            R.id.lista_alunos -> {
                recyclerView.adapter = AlunoAdapter(this, alunosBox.all, alunosBox)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.hasFixedSize()
            }
            R.id.lista_faculdades -> {
                recyclerView.adapter = FaculdadeAdapter(this, faculdadeBox)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.hasFixedSize()
            }
            R.id.configurações -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
