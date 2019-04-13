package com.example.inventariogranodeoro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.inventariogranodeoro.activitys.ModificarActivity;
import com.example.inventariogranodeoro.activitys.UsuarioActivity;
import com.example.inventariogranodeoro.entidades.Articulo;
import java.util.ArrayList;

interface CustomItemClickListener{
    public void onItemClick(View v, int position);
}

public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder>{
    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    private UsuarioActivity user;
    CustomItemClickListener listener;
    public Lista(UsuarioActivity u){
        user = u;
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public Articulo getArticulo(int i){
        return articulos.get(i);
    }

    public void addProducto(Articulo articulo){
        articulos.add(articulo);
        notifyDataSetChanged();
    }

    public void removeProducto(int index){
        articulos.remove(index);
        notifyDataSetChanged();
    }

    public void modify(int index, Articulo articulo){
        articulos.set(index, articulo);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
        final MyViewHolder vh = new MyViewHolder(itemView);
        vh.btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user, ModificarActivity.class);
                user.setIndex(vh.getLayoutPosition());
                intent.putExtra("ART", articulos.get(vh.getLayoutPosition()));
                user.startActivityForResult(intent, 2);
            }
        });
        vh.btnDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Toast.makeText(user, Integer.toString(vh.getLayoutPosition()), Toast.LENGTH_LONG).show();
                    articulos.remove(vh.getLayoutPosition());
                    notifyDataSetChanged();
                }
        });
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        viewHolder.postId.setText(articulos.get(i).getIdProducto());
        viewHolder.postName.setText(articulos.get(i).getNombre());
        viewHolder.postCount.setText(String.format(Float.toString(articulos.get(i).getExistencia())));
    }
    // Return the size of your dataset (invoked by the layout manager)

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView postId, postName, postCount;
        ImageButton btnUpdate, btnDelete;
        public MyViewHolder(final View itemView){
            super(itemView);
            postId = itemView.findViewById(R.id.postId);
            postName = itemView.findViewById(R.id.postName);
            postCount = itemView.findViewById(R.id.postCount);
            btnUpdate = itemView.findViewById(R.id.updateProduct);
            btnDelete = itemView.findViewById(R.id.deleteProduct);
        }
    }
}
