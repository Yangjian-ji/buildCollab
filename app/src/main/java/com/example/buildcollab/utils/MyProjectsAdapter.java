package com.example.buildcollab.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;

import java.util.List;

public class MyProjectsAdapter extends RecyclerView.Adapter<MyProjectsAdapter.viewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Project project);
    }

    private Context context;
    private Activity activity;
    private List<Project> projects;
    private DatabaseHelper database_helper;
    private OnItemClickListener listener;

    public MyProjectsAdapter(Context context, Activity activity, List<Project> projects, OnItemClickListener listener) {
        this.context = context;
        this.activity = activity;
        this.projects = projects;
        this.listener = listener;
    }

    @Override
    public MyProjectsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyProjectsAdapter.viewHolder holder, int position) {
        int mLastPosition = holder.getAdapterPosition();
        holder.title.setText(projects.get(mLastPosition).getTitle());
        holder.description.setText(projects.get(mLastPosition).getDescription());
        database_helper = new DatabaseHelper(context);
        holder.bind(projects.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView delete, edit;

        public viewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }

        public void bind(Project item, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
