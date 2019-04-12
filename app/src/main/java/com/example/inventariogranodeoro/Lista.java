package com.example.inventariogranodeoro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Lista extends RecyclerView.Adapter<Lista.MyViewHolder> {
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> producto = new ArrayList<String>();
    private ArrayList<String> cantidad = new ArrayList<String>();

    public Lista(){ }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i){
        viewHolder.postId.setText(id.get(i));
        viewHolder.postName.setText(producto.get(i));
        viewHolder.postCount.setText(cantidad.get(i));
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return id.size();
    }
    public void addProducto(String i, String p, String c){
        id.add(i);
        producto.add(p);
        cantidad.add(c);
        notifyDataSetChanged();
    }
    public void removeProducto(String i){
        int x = 0;
        while(id.get(x) != i){
            x++;
        }
        id.remove(x);
        producto.remove(x);
        cantidad.remove(x);
    }
    public void modify(String i, String c){
        int x = 0;
        while(id.get(x) != i){
            x++;
        }
        cantidad.set(x, c);
    }
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
