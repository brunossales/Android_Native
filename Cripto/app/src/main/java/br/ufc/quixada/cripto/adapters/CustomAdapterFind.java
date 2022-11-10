package br.ufc.quixada.cripto.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.model.Criptomoeda;
import br.ufc.quixada.cripto.views.Find_activity;

public class CustomAdapterFind extends  RecyclerView.Adapter<CustomAdapterFind.ViewHolder>{
    ArrayList<Criptomoeda> dataSet;
    Find_activity activity;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome;
        TextView textViewSimbolo;
        TextView textViewValor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewSimbolo = itemView.findViewById(R.id.textViewSImbolo);
            textViewValor = itemView.findViewById(R.id.textViewValor);
        }

        public TextView getTextViewNome() {return textViewNome;}
        public TextView getTextViewSimbolo() {return textViewSimbolo;}
        public TextView getTextViewValor() {return textViewValor;}
    }

    public CustomAdapterFind(Find_activity activity, ArrayList<Criptomoeda> data){
        this.dataSet = data;
        this.activity = activity;
    }


    @NonNull
    @Override
    public CustomAdapterFind.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.cripto_start_layout,
                parent,
                false
        );
        return new CustomAdapterFind.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapterFind.ViewHolder holder, int position) {
        Criptomoeda cripto = dataSet.get(position);

        holder.getTextViewNome().setText("Nome: "  +cripto.getNome());
        holder.getTextViewValor().setText("R$: " + cripto.getValor());
        holder.getTextViewSimbolo().setText(cripto.getSimbolo());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
