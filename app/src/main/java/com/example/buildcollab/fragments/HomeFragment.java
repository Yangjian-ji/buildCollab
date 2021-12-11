package com.example.buildcollab.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;
import com.example.buildcollab.activity.GroupProfileActivity;
import com.example.buildcollab.activity.ProfileActivity;
import com.example.buildcollab.activity.ProjectProfileActivity;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.DatabaseHelperGroups;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.MyGroupAdapter;
import com.example.buildcollab.utils.MyProjectsAdapter;
import com.example.buildcollab.utils.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView profile;
    private DatabaseHelper database_helper;
    private List<Project> projects;
    private List<Groups> groups;
    private ConcatAdapter concatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = InputFragmentView.findViewById(R.id.reclycleview);
        mRecyclerView.setHasFixedSize(true);

        database_helper = new DatabaseHelper(getContext());

        mLayoutManager = new LinearLayoutManager(InputFragmentView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        displayAll();

        profile = InputFragmentView.findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        return InputFragmentView;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerView != null) {
            displayAll();
            mRecyclerView.invalidate();
        }
    }


    private void displayAll() {
        MyProjectsAdapter myProjectsAdapter = displayProjects();
        MyGroupAdapter myGroupAdapter = displayGroups();
        double t = Math.random();
        if (t >= 0.5) {

            concatAdapter = new ConcatAdapter(myProjectsAdapter, myGroupAdapter);
        } else {

            concatAdapter = new ConcatAdapter(myGroupAdapter, myProjectsAdapter);
        }
        mRecyclerView.setAdapter(concatAdapter);
    }


    private MyProjectsAdapter displayProjects() {
        projects = new ArrayList<>(database_helper.getProjects());
        Collections.shuffle(projects);
        MyProjectsAdapter.OnItemClickListener listener = project -> {
            Intent intent = new Intent(getActivity(), ProjectProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(project.getProjectId()));
            intent.putExtras(b);
            startActivity(intent);
        };
        MyProjectsAdapter mAdapter = new MyProjectsAdapter(getContext(), getActivity(), projects, listener);
        return mAdapter;
    }

    private MyGroupAdapter displayGroups() {
        DatabaseHelperGroups database_helper = new DatabaseHelperGroups(getContext());
        groups = new ArrayList<>(database_helper.getGroups());
        Collections.shuffle(groups);
        MyGroupAdapter.OnItemClickListener listener = groups -> {
            Intent intent = new Intent(getActivity(), GroupProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(groups.getGroupId()));
            intent.putExtras(b);
            startActivity(intent);
        };


        MyGroupAdapter mAdapter = new MyGroupAdapter(getContext(), getActivity(), groups, listener);
        return mAdapter;
    }


}
