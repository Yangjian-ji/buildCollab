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
import com.example.buildcollab.activity.HomeActivity;

import java.util.List;

public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.viewHolder> {

    private Context context;
    private Activity activity;
    private List<Groups> groups;
    private DatabaseHelper database_helper;
    private OnItemClickListener listener;
    private boolean belong;

    public interface OnItemClickListener {
        void onItemClick(Groups groups);
    }

    public MyGroupAdapter(Context context, Activity activity, List<Groups> groups, OnItemClickListener listener) {
        this.context = context;
        this.activity = activity;
        this.groups = groups;
        this.listener = listener;
        this.belong = belong;
    }

    @Override
    public int getItemViewType(int position) {
        database_helper = new DatabaseHelper(context);
        return database_helper.isUserInGroup(HomeActivity.getUserId(), groups.get(position).getGroupId()) ? 1 : 0;
    }

    @NonNull
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if (i == 0)
            view = LayoutInflater.from(context).inflate(R.layout.group_item, viewGroup, false);
        else
            view = LayoutInflater.from(context).inflate(R.layout.group_item_belong, viewGroup, false);
        return new viewHolder(view);
    }


    public void onBindViewHolder(final MyGroupAdapter.viewHolder holder, int position) {
        int mLastPosition = holder.getAdapterPosition();
        holder.title.setText(groups.get(mLastPosition).getTitle());
        holder.description.setText(groups.get(mLastPosition).getDescription());
        database_helper = new DatabaseHelper(context);
        holder.bind(groups.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return groups.size();
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

        public void bind(Groups item, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
