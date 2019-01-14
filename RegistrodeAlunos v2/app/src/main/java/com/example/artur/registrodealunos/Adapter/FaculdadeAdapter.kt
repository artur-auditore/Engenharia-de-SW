package com.example.artur.registrodealunos.Adapter

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.artur.registrodealunos.FormularioFaculdadeActivity
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.Modelo.Faculdade
import com.example.artur.registrodealunos.R
import io.objectbox.Box
import kotlinx.android.synthetic.main.item_faculdade.view.*

class FaculdadeAdapter(private val context: Context,
                       private val faculdadeBox: Box<Faculdade>): RecyclerView.Adapter<FaculdadeAdapter.ViewHolder>(){

    companion object {
        const val ID = "idFaculdade"
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(faculdade: Faculdade){
            val nome = itemView.text_nome_faculdade
            val email= itemView.text_email_faculdade

            nome.text = faculdade.nome
            email.text = faculdade.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_faculdade, parent, false))
    }

    override fun getItemCount(): Int {
        return faculdadeBox.all.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faculdade = faculdadeBox.all[position]
        holder.bind(faculdade)

        imageMenus(holder.itemView, faculdade, position)
        popMenu(holder.itemView, faculdade, position)
    }

    private fun popMenu(itemView: View, faculdade: Faculdade, position: Int){
        itemView.setOnLongClickListener { view ->
            val popup = PopupMenu(context, view)
            popup.menuInflater.inflate(com.example.artur.registrodealunos.R.menu.pop_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.op_editar -> popEditar(faculdade, position)
                    R.id.op_remover -> popExcluir(faculdade, position)

                }
                false
            }
            popup.show()

            true
        }
    }

    private fun popEditar(faculdade: Faculdade, position: Int){
        val intent = Intent(context, FormularioFaculdadeActivity::class.java)
        intent.putExtra(ID, faculdade.id)
        context.startActivity(intent)
        notifyItemChanged(position)
    }

    private fun popExcluir(faculdade: Faculdade, position: Int){
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Excluir")
            .setMessage("Deseja realmente excluir ${faculdade.nome}?")
            .setPositiveButton("SIM"){_, _ ->
                this.faculdadeBox.remove(faculdade)
                notifyItemRemoved(position) //atualiza a lista
                notifyItemRangeChanged(position, itemCount)
                Toast.makeText(context, "${faculdade.nome} removida", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("NÃO"){_, _ ->}
            .create()
            .show()
    }

    private fun imageMenus(itemView: View, faculdade: Faculdade, position: Int){
        val editar = itemView.icon_editar_faculdade
        val excluir = itemView.icon_excluir_faculdade

        editar.setOnClickListener { viewEditar(faculdade, position) }

        excluir.setOnClickListener { viewExcluir(faculdade, position) }
    }

    private fun viewEditar(faculdade: Faculdade, position: Int){
        val intent = Intent(context, FormularioFaculdadeActivity::class.java)
        intent.putExtra(ID, faculdade.id)
        context.startActivity(intent)
        notifyItemChanged(position)
    }

    private fun viewExcluir(faculdade: Faculdade, position: Int){
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Excluir")
            .setMessage("Deseja realmente excluir ${faculdade.nome}?")
            .setPositiveButton("SIM"){_, _ ->
                this.faculdadeBox.remove(faculdade)
                notifyItemRemoved(position) //atualiza a lista
                notifyItemRangeChanged(position, itemCount)
                Toast.makeText(context, "${faculdade.nome} removida", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("NÃO"){_, _ ->}
            .create()
            .show()
    }

}