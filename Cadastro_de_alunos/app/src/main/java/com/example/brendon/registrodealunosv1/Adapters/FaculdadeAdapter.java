package com.example.brendon.registrodealunosv1.Adapters;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;

import com.example.brendon.registrodealunosv1.Formularios.FormularioAddAluno;
import com.example.brendon.registrodealunosv1.Formularios.FormularioAddFaculdade;
import com.example.brendon.registrodealunosv1.Models.Faculdade;
import com.example.brendon.registrodealunosv1.R;
import io.objectbox.Box;


public class FaculdadeAdapter extends RecyclerView.Adapter<FaculdadeAdapter.FaculdadeViewHolder> {

    public static String ID = "idFaculdade";

    private Context context;
    private Box<Faculdade> listFaculdades;

    public FaculdadeAdapter(Context context, Box<Faculdade> listFaculdades) {
        this.listFaculdades = listFaculdades;
        this.context = context;
    }

    @NonNull
    @Override
    public FaculdadeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_faculdade, viewGroup, false);
        return new FaculdadeViewHolder(view);
    }

    @SuppressLint({"SetTextI18n","RecyclerView"})
    @Override
    public void onBindViewHolder(@NonNull FaculdadeViewHolder holder, final int position) {
        final Faculdade faculdadeAtual = this.listFaculdades.getAll().get(position);

        holder.txtNome.setText("Nome: " + faculdadeAtual.getNome());
        holder.txtEmail.setText("Email: " + faculdadeAtual.getEmail());
        holder.txtContato.setText("Contato " + faculdadeAtual.getContatoPrincipal());

        imageMenus(holder.itemView, faculdadeAtual, position);
        popMenu(holder.itemView, faculdadeAtual, position);
    }

    private void imageMenus(View view, Faculdade faculdade, int position){

        ImageButton excluir_faculdade = view.findViewById(R.id.excluir_faculdade);
        ImageButton editar_faculdade = view.findViewById(R.id.editar_faculdade);

        editar_faculdade.setOnClickListener(l ->{
            Intent intent = new Intent(context, FormularioAddFaculdade.class);
            intent.putExtra(ID, faculdade.id);
            context.startActivity(intent);
            notifyItemChanged(position);
        });

        excluir_faculdade.setOnClickListener(l ->{
            AlertDialog alerta;
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Remover");
            builder.setMessage("Deseja remover : " + faculdade.getNome()+" ?");
            builder.setPositiveButton("Sim", (arg0, arg1) -> {
                listFaculdades.remove(faculdade);
                notifyItemRemoved(position);
                Snackbar.make(view,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
            });
            builder.setNegativeButton("Nao", (arg0, arg1) -> {
            });
            alerta = builder.create();
            alerta.show();
        });
    }

    private void popMenu(View view, Faculdade faculdade, int position){
        view.setOnLongClickListener((l ) -> {
            PopupMenu popup = new PopupMenu(context, view);
            popup.getMenuInflater().inflate(R.menu.pop_menu, popup.getMenu());

            popup.setOnMenuItemClickListener((item) -> {

                switch (item.getItemId()){
                    case R.id.op_editar:

                        Intent intent = new Intent(context, FormularioAddFaculdade.class);
                        intent.putExtra(ID, faculdade.id);
                        context.startActivity(intent);
                        notifyItemChanged(position);
                        break;

                    case R.id.op_remover:

                        AlertDialog alerta;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Remover");
                        builder.setMessage("Deseja remover a faculdade: " + faculdade.getNome()+" ?");
                        builder.setPositiveButton("Sim", (arg0, arg1) -> {
                            listFaculdades.remove(faculdade);
                            notifyItemRemoved(position);
                            Snackbar.make(l,"Removido com sucesso!", Snackbar.LENGTH_LONG).show();
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

    @Override
    public int getItemCount() {
        return this.listFaculdades.getAll().size();
    }

    public class FaculdadeViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtEmail, txtContato;

        public FaculdadeViewHolder(View view) {
            super(view);
            txtNome = view.findViewById(R.id.view_nome_faculdade);
            txtEmail = view.findViewById(R.id.view_email);
            txtContato = view.findViewById(R.id.view_contato);

        }
}
}
