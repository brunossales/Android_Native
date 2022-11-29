package br.ufc.quixada.navbetweenscreens.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.R;
import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Criptomoeda> dataSet;
    MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome;
        TextView textViewSimbolo;
        TextView textViewValor;

        ImageView imageViewUpdate;
        ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewSimbolo = itemView.findViewById(R.id.textViewSImbolo);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }

        public TextView getTextViewNome() {return textViewNome;}
        public TextView getTextViewSimbolo() {return textViewSimbolo;}
        public TextView getTextViewValor() {return textViewValor;}

        public ImageView getImageViewDelete() {return imageViewDelete;}
    }

    public CustomAdapter(MainActivity activity, ArrayList<Criptomoeda> data){
        this.dataSet = data;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.criptomoedas_layout, parent, false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CustomAdapter.ViewHolder holder, int position) {
        Criptomoeda cripto = dataSet.get(position);

        holder.getTextViewNome().setText("ID: " + position + ", Nome: "  +cripto.getNome());
        holder.getTextViewValor().setText("R$: " + cripto.getValor());
        holder.getTextViewSimbolo().setText(cripto.getSimbolo());

        holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.removerCripto(cripto.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }
}
