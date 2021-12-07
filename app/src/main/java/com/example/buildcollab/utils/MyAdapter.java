package com.example.buildcollab.utils;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<LauncherActivity.ListItem> listItem;
    private int type;

    public MyAdapter(Context context, List listItem, int type) {
        this.context = context;
        this.listItem = listItem;
        this.type = type; //0 if Groups 1 if Project 2 if Person

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        //Atualizar as cenas para aqui
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Leitura dos items
        }

        @Override
        public void onClick(View v) {
//Salta para outra pagina
        }
    }
}
