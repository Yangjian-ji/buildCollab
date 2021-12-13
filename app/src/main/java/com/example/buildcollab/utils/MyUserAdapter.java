package com.example.buildcollab.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;

import java.util.List;

public class MyUserAdapter extends RecyclerView.Adapter<MyUserAdapter.viewHolder> {

    private Context context;
    private Activity activity;
    private List<Users> users;
    private DatabaseHelper database_helper;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Users users);
    }

    public MyUserAdapter(Context context, Activity activity, List<Users> users, OnItemClickListener listener) {
        this.context = context;
        this.activity = activity;
        this.users = users;
        this.listener = listener;

    }

    @NonNull
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_item, viewGroup, false);
        return new viewHolder(view);
    }


    public void onBindViewHolder(final MyUserAdapter.viewHolder holder, int position) {
        int mLastPosition = holder.getAdapterPosition();
        holder.name.setText(users.get(mLastPosition).getName());
        holder.description.setText(users.get(mLastPosition).getDescription());
        database_helper = new DatabaseHelper(context);
        holder.bind(users.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView name, description;

        public viewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(Users item, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
