package br.ufc.quixada.basicscomponents.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.ufc.quixada.basicscomponents.R;
import br.ufc.quixada.basicscomponents.model.Contato;

public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private static ArrayList<Contato> dataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewNome;
        private final TextView textViewTelefone;
        private final TextView textViewEndereco;
        private final ImageView imageDelete;

        public ViewHolder( View itemView ) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewRecyclerNome);
            textViewTelefone = itemView.findViewById(R.id.textViewTelefoneRecycler);
            textViewEndereco = itemView.findViewById(R.id.textViewEndereçoRecycler);
            imageDelete = itemView.findViewById(R.id.imageViewRecycler);

        }

        public TextView getTextViewNome(){
            return textViewNome;
        }

        public TextView getTextViewTelefone(){
            return textViewTelefone;
        }

        public TextView getTextViewEndereco(){
            return textViewEndereco;
        }

        public ImageView getImageDelete(){
            return imageDelete;
        }

    }

    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //criando a view holder, inflando a classe no layout que criamos
        View view = LayoutInflater.from( parent.getContext()).inflate(
                                                                R.layout.contato_layout,
                                                                parent,
                                                                false
                                                                );
        return new ViewHolder(view);
    }

    public CustomAdapter (ArrayList<Contato> data){
        dataSet = data;
    }


    public void onBindViewHolder( ViewHolder holder, int position) {
        //Recycler view - recicla elementos em tela
        Contato contato = dataSet.get(position);

        holder.getTextViewNome().setText( contato.getNome() );
        holder.getTextViewTelefone().setText( contato.getTelefone() );
        holder.getTextViewEndereco().setText( contato.getEndereco() );

        holder.getImageDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //não usar o position, por questões de processamento
                dataSet.remove( holder.getAdapterPosition() );
            }
        });
    }


    public int getItemCount() {
        return dataSet.size();
    }

}
