package com.example.artur.registrodealunos.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artur.registrodealunos.Modelo.Aluno
import com.example.artur.registrodealunos.R

class AlunoAdapter(private val alunos: MutableList<Aluno>,
                   private val context: Context): RecyclerView.Adapter<AlunoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(aluno: Aluno){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false))
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.bind(aluno)
    }
}