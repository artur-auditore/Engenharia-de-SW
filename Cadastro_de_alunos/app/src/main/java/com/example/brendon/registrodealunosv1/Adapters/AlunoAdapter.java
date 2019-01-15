package com.example.brendon.registrodealunosv1.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brendon.registrodealunosv1.Formularios.FormularioAddAluno;
import com.example.brendon.registrodealunosv1.Models.Aluno;
import com.example.brendon.registrodealunosv1.R;

import io.objectbox.Box;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    public static String ID = "idAluno";

    private Context context;
    private Box<Aluno> listAlunos;


    public AlunoAdapter(Context context, Box<Aluno> listAlunos) {
        this.listAlunos = listAlunos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_aluno, viewGroup, false);
        return new AlunoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, final int position) {
        final Aluno alunoAtual = this.listAlunos.getAll().get(position);

        holder.txtNome.setText("Nome: " + alunoAtual.getNome());
        holder.txtCurso.setText("Curso: " + alunoAtual.getCurso());
        holder.txtFaculdade.setText("Faculdade " + alunoAtual.getFaculdade());

        imageMenus(holder.itemView, alunoAtual, position);

        popMenu(holder.itemView, alunoAtual, position);
    }

    private void popMenu(View itemView, Aluno aluno, int position){
        itemView.setOnLongClickListener((view ) -> {
            PopupMenu popup = new PopupMenu(context, view);
            popup.getMenuInflater().inflate(R.menu.pop_menu, popup.getMenu());

            popup.setOnMenuItemClickListener((item) -> {

                switch (item.getItemId()){
                    case R.id.op_editar:

                        Intent intent = new Intent(context, FormularioAddAluno.class);
                        intent.putExtra(ID, aluno.id);
                        context.startActivity(intent);
                        notifyItemChanged(position);
                        break;

                    case R.id.op_remover:

                        AlertDialog alerta;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Remover");
                        builder.setMessage("Deseja remover aluno(a): " + aluno.getNome()+" ?");
                        builder.setPositiveButton("Sim", (arg0, arg1) -> {
                            listAlunos.remove(aluno);
                            notifyItemRemoved(position);
                            Snackbar.make(view,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
                        });
                        builder.setNegativeButton("Nao", (arg0, arg1) -> {
                        });
                        alerta = builder.create();
                        alerta.show();
                        break;
                }

                return false;
            });

            popup.show();

            return true;
        });
    }

    private void imageMenus(View itemView, Aluno aluno, int position){
        ImageButton excluir = itemView.findViewById(R.id.excluir_aluno);
        ImageButton editar = itemView.findViewById(R.id.editar_aluno);

        editar.setOnClickListener(l ->{
            Intent intent = new Intent(context, FormularioAddAluno.class);
            intent.putExtra(ID, aluno.id);
            context.startActivity(intent);
            notifyItemChanged(position);

        });

        editar.setOnLongClickListener(l->{
            Toast.makeText(context, "Editar", Toast.LENGTH_LONG).show();
            return true;
        });

        excluir.setOnLongClickListener(l ->{
            Toast.makeText(context, "Excluir", Toast.LENGTH_LONG).show();
            return true;
        });

        excluir.setOnClickListener(l ->{
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Remover");
            builder.setMessage("Deseja remover aluno(a): " + aluno.getNome()+" ?");
            builder.setPositiveButton("Sim", (arg0, arg1) -> {
                listAlunos.remove(aluno);
                notifyItemRemoved(position);
                Snackbar.make(l,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
            });
            builder.setNegativeButton("Nao", (arg0, arg1) -> {
            });
            alerta = builder.create();
            alerta.show();
        });
    }

    @Override
    public int getItemCount() {
        return this.listAlunos.getAll().size();
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtCurso, txtFaculdade;

        public AlunoViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_aluno);
            txtCurso = view.findViewById(R.id.view_curso);
            txtFaculdade = view.findViewById(R.id.view_faculdade);

        }
    }
}




