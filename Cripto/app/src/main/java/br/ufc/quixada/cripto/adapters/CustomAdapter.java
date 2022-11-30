package br.ufc.quixada.cripto.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.model.Criptomoeda;
import br.ufc.quixada.cripto.views.Feed_activity;

public class CustomAdapter  extends  RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    ArrayList<Criptomoeda> dataSet;
    Feed_activity activity;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome;
        TextView textViewSimbolo;
        TextView textViewValor;

        ImageView imageViewUpdate;
        ImageView imageViewDelete;

        Switch switchButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewSimbolo = itemView.findViewById(R.id.textViewSImbolo);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewUpdate = itemView.findViewById(R.id.imageViewUpdate);
            switchButton = itemView.findViewById(R.id.buttonSwitchStar);
        }

        public TextView getTextViewNome() {return textViewNome;}
        public TextView getTextViewSimbolo() {return textViewSimbolo;}
        public TextView getTextViewValor() {return textViewValor;}

        public ImageView getImageViewDelete() {return imageViewDelete;}
        public ImageView getImageViewUpdate() {return imageViewUpdate;}

        public Switch getSwitchButton() {return switchButton;}
    }

    public CustomAdapter(Feed_activity activity, ArrayList<Criptomoeda> data){
        this.dataSet = data;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                    R.layout.cripto_layout,
                    parent,
                    false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Criptomoeda cripto = dataSet.get(position);

        holder.getTextViewNome().setText("Nome: "  +cripto.getNome());
        holder.getTextViewValor().setText("R$: " + cripto.getValor());
        holder.getTextViewSimbolo().setText(cripto.getSimbolo());

        if (cripto.isStar()){holder.getSwitchButton().setChecked(true);}

        holder.getImageViewDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Criptomoeda cri = dataSet.get(holder.getAdapterPosition());
                activity.removerCripto(cri);
            }
        });

        holder.getImageViewUpdate().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Criptomoeda cri = dataSet.get(holder.getAdapterPosition());
                activity.updateCripto(cri);}
        });

        holder.getSwitchButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cripto.isStar()){
                    Criptomoeda cri = dataSet.get(holder.getAdapterPosition());
                    activity.setCriptoStarFeed(cri.getId());
                }
                else{
                    Criptomoeda cri = dataSet.get(holder.getAdapterPosition());
                    activity.setCriptoStarFeed(cri.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
