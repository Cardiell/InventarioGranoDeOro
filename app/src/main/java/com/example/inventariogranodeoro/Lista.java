package com.example.inventariogranodeoro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.inventariogranodeoro.Entidades.Articulo;
import java.util.ArrayList;

public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder> {

    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public Lista(){ }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public void addProducto(Articulo articulo) {
        articulos.add(articulo);
        notifyDataSetChanged();
    }

    public void removeProducto(int index){
        articulos.remove(index);
    }

    public void modify(int index,Articulo articulo){
        articulos.set(index, articulo);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        viewHolder.postId.setText(articulos.get(i).getIdProducto());
        viewHolder.postName.setText(articulos.get(i).getNombre());
        viewHolder.postCount.setText(Float.toString(articulos.get(i).getExistencia()));
    }
    // Return the size of your dataset (invoked by the layout manager)

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView postId, postName, postCount;
        public MyViewHolder(View itemView){
            super(itemView);
            postId = itemView.findViewById(R.id.postId);
            postName = itemView.findViewById(R.id.postName);
            postCount = itemView.findViewById(R.id.postCount);
        }
    }
}
