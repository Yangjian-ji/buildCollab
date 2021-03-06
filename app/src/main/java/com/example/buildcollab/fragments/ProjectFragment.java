package com.example.buildcollab.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;
import com.example.buildcollab.activity.HomeActivity;
import com.example.buildcollab.activity.NewProjectActivity;
import com.example.buildcollab.activity.ProfileActivity;
import com.example.buildcollab.activity.ProjectActivity;
import com.example.buildcollab.activity.ProjectProfileActivity;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.MyProjectsAdapter;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private TextView emptyView;
    private ImageView profile;
    private DatabaseHelper database_helper;
    private List<Project> projects;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_project, container, false);

        Button button = (Button) InputFragmentView.findViewById(R.id.newproject);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProjectActivity.class);
                startActivity(intent);
            }
        });

        onclick.buttonEffect(button);
        mRecyclerView = InputFragmentView.findViewById(R.id.reclycleview);
        emptyView = InputFragmentView.findViewById(R.id.emptyMessage);
        mRecyclerView.setHasFixedSize(true);

        database_helper = new DatabaseHelper(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(InputFragmentView.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        displayProjects();

        profile = InputFragmentView.findViewById(R.id.profile);

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(HomeActivity.getUserId()));
            intent.putExtras(b);
            startActivity(intent);
        });
        return InputFragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerView != null) {
            displayProjects();
            mRecyclerView.invalidate();
        }
    }

    private void displayProjects() {
        projects = new ArrayList<>(database_helper.getProjects(HomeActivity.getUserId()));
        if (projects.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        MyProjectsAdapter.OnItemClickListener listener = project -> {
            Intent intent;
            if (database_helper.isUserInProject(HomeActivity.getUserId(), project.getProjectId()))
                intent = new Intent(getActivity(), ProjectActivity.class);
            else
                intent = new Intent(getActivity(), ProjectProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(project.getProjectId()));
            intent.putExtras(b);
            startActivity(intent);
        };
        mAdapter = new MyProjectsAdapter(getContext(), getActivity(), projects, listener);
        mRecyclerView.setAdapter(mAdapter);
    }
}
