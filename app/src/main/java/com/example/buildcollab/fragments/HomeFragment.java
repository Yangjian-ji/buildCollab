package com.example.buildcollab.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.buildcollab.R;
import com.example.buildcollab.activity.GroupActivity;
import com.example.buildcollab.activity.GroupProfileActivity;
import com.example.buildcollab.activity.HomeActivity;
import com.example.buildcollab.activity.ProfileActivity;
import com.example.buildcollab.activity.ProjectActivity;
import com.example.buildcollab.activity.ProjectProfileActivity;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.MyGroupAdapter;
import com.example.buildcollab.utils.MyProjectsAdapter;
import com.example.buildcollab.utils.MyUserAdapter;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView profile;
    private EditText search;
    private DatabaseHelper database_helper;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Project> projects;
    private List<Groups> groups;
    private List<Users> users;
    private ConcatAdapter concatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        search = InputFragmentView.findViewById(R.id.search);
        swipeRefreshLayout = InputFragmentView.findViewById(R.id.refresh);
        mRecyclerView = InputFragmentView.findViewById(R.id.reclycleview);
        mRecyclerView.setHasFixedSize(true);

        database_helper = new DatabaseHelper(getContext());

        mLayoutManager = new LinearLayoutManager(InputFragmentView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        displayAll(false, null);

        profile = InputFragmentView.findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(HomeActivity.getUserId()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                displayAll(true, null);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0)
                    displayAll(true, search.getText().toString());
            }
        });

        return InputFragmentView;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerView != null) {
            displayAll(false, null);
            mRecyclerView.invalidate();
        }
    }


    private void displayAll(boolean refresh, String filter) {
        if (filter != null) {
            projects = new ArrayList<>();
            groups = new ArrayList<>();
            users = new ArrayList<>();
            List<Project> tmpproject = new ArrayList<>(database_helper.getProjects());
            List<Groups> tmpgroups = new ArrayList<>(database_helper.getGroups());
            List<Users> tmpusers = new ArrayList<>(database_helper.getUsers());


            for (Project project : tmpproject) {
                if (project.getTitle().toUpperCase().contains(filter.toUpperCase())) {
                    projects.add(project);
                }
            }
            for (Groups group : tmpgroups) {
                if (group.getTitle().toUpperCase().contains(filter.toUpperCase())) {
                    groups.add(group);
                }
            }
            for (Users user : tmpusers) {
                if (user.getName().toUpperCase().contains(filter.toUpperCase())|| filter.toUpperCase().contains("ANI")) {
                    users.add(user);
                }
            }
        } else {
            projects = new ArrayList<>(database_helper.getProjects());
            groups = new ArrayList<>(database_helper.getGroups());
            users = new ArrayList<>(database_helper.getUsers());
        }


        MyProjectsAdapter myProjectsAdapter = displayProjects();
        MyGroupAdapter myGroupAdapter = displayGroups();
        MyUserAdapter myUserAdapter = displayUser();

        if (refresh) {
            Collections.shuffle(projects);
            Collections.shuffle(groups);

        }
        double t = Math.random();

        if (t >= 0.5) {

            concatAdapter = new ConcatAdapter(myProjectsAdapter, myGroupAdapter);
            concatAdapter = new ConcatAdapter(concatAdapter, myUserAdapter);
        } else {

            concatAdapter = new ConcatAdapter(myGroupAdapter, myProjectsAdapter);

            concatAdapter = new ConcatAdapter(concatAdapter, myUserAdapter);
        }
        mRecyclerView.setAdapter(concatAdapter);
    }

    private MyUserAdapter displayUser() {
        MyUserAdapter.OnItemClickListener listener = users -> {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(users.getUserId()));
            intent.putExtras(b);
            startActivity(intent);
        };


        MyUserAdapter mAdapter = new MyUserAdapter(getContext(), getActivity(), users, listener);
        return mAdapter;
    }


    private MyProjectsAdapter displayProjects() {

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
        MyProjectsAdapter mAdapter = new MyProjectsAdapter(getContext(), getActivity(), projects, listener);
        return mAdapter;
    }

    private MyGroupAdapter displayGroups() {

        MyGroupAdapter.OnItemClickListener listener = groups -> {
            Intent intent;
            if (database_helper.isUserInGroup(HomeActivity.getUserId(), groups.getGroupId()))
                intent = new Intent(getActivity(), GroupActivity.class);
            else
                intent = new Intent(getActivity(), GroupProfileActivity.class);
            Bundle b = new Bundle();
            b.putInt("id", Integer.parseInt(groups.getGroupId()));
            intent.putExtras(b);
            startActivity(intent);
        };

        MyGroupAdapter mAdapter = new MyGroupAdapter(getContext(), getActivity(), groups, listener);
        return mAdapter;
    }


}
