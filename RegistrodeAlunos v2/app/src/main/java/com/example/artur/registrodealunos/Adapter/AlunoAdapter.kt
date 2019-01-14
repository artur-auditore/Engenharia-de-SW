package com.example.artur.registrodealunos.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.artur.registrodealunos.FormularioAlunoActivity
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.R
import io.objectbox.Box
import kotlinx.android.synthetic.main.item_aluno.view.*

class AlunoAdapter (private val context: Context,
                    private var alunos: MutableList<Aluno>,
                    private val alunosBox: Box<Aluno>): RecyclerView.Adapter<AlunoAdapter.ViewHolder>(){

    companion object {
        const val ID = "id"

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(aluno: Aluno){
            val nome = itemView.text_nome
            val curso = itemView.text_curso
            val faculdade = itemView.text_faculdade

            nome.text = aluno.nome
            curso.text = aluno.curso
            faculdade.text = aluno.faculdade.target.nome
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_aluno, parent,false))
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aluno = this.alunosBox.all[position]
        holder.bind(aluno)

        imageMenus(holder.itemView, aluno, position)
        popMenu(holder.itemView, aluno, position)
    }

    //Menu pop up (caso fique díficil tocar numa imageButton para editar ou excluir)
    private fun popMenu(itemView: View, aluno: Aluno, position: Int){
        itemView.setOnLongClickListener { view ->
            val popup = PopupMenu(context, view)
            popup.menuInflater.inflate(com.example.artur.registrodealunos.R.menu.pop_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.op_editar -> popEditar(aluno, position)
                    R.id.op_remover -> popExcluir(aluno, position)

                }
                false
            }
            popup.show()

            true
        }
    }

    private fun popEditar(aluno: Aluno, position: Int){
        val intent = Intent(context, FormularioAlunoActivity::class.java)
        intent.putExtra(ID, aluno.id)
        context.startActivity(intent)
        notifyItemChanged(position) //atualiza a lista
    }

    private fun popExcluir(aluno: Aluno, position: Int){
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Remover")
            .setMessage("Deseja realmente remover ${aluno.nome}?")
            .setPositiveButton("SIM"){ _, _ ->
                this.alunos.remove(aluno)
                this.alunosBox.remove(aluno)
                notifyItemRemoved(position) //atualiza a lista
                notifyItemRangeChanged(position, itemCount)
                Toast.makeText(context, "${aluno.nome} apagado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("NÃO"){_, _ ->}
            .create()
            .show()
    }

    //Image menus (menus rápidos)
    private fun imageMenus(itemView: View, aluno: Aluno, position: Int){
        val editar = itemView.icon_editar
        val excluir = itemView.icon_excluir

        editar.setOnClickListener {
            viewEditar(aluno, position)
        }

        excluir.setOnClickListener {
            viewExcluir(aluno, position)
        }

    }

    private fun viewEditar(aluno: Aluno, position: Int){
        val intent = Intent(context, FormularioAlunoActivity::class.java)
        intent.putExtra(ID, aluno.id)
        context.startActivity(intent)
        notifyItemChanged(position) //atualiza a lista
    }

    private fun viewExcluir(aluno: Aluno, position: Int){
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Remover")
            .setMessage("Deseja realmente remover ${aluno.nome}?")
            .setPositiveButton("SIM"){ _, _ ->
                this.alunos.remove(aluno)
                this.alunosBox.remove(aluno)
                notifyItemRemoved(position) //atualiza a lista
                notifyItemRangeChanged(position, itemCount)
                Toast.makeText(context, "${aluno.nome} apagado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("NÃO"){_, _ ->}
            .create()
            .show()
    }

    fun setAlunos(alunos: MutableList<Aluno>){
        this.alunos = alunos
    }
}