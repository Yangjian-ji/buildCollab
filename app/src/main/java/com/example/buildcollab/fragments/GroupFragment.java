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
import com.example.buildcollab.activity.GroupActivity;
import com.example.buildcollab.activity.GroupProfileActivity;
import com.example.buildcollab.activity.HomeActivity;
import com.example.buildcollab.activity.NewGroupActivity;
import com.example.buildcollab.activity.ProfileActivity;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.MyGroupAdapter;
import com.example.buildcollab.utils.onclick;

import java.util.List;

public class GroupFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView emptyView;
    private RecyclerView.Adapter mAdapter;
    private ImageView profile;
    private DatabaseHelper database_helper;
    private List<Groups> groups;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_group, container, false);
        Button button = (Button) InputFragmentView.findViewById(R.id.newgroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewGroupActivity.class);
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

        displayGroups();

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

    private void displayGroups() {
        groups = database_helper.getGroupsBelong(HomeActivity.getUserId());
        if (groups.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
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
        mAdapter = new MyGroupAdapter(getContext(), getActivity(), groups, listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerView != null) {
            displayGroups();
            mRecyclerView.invalidate();
        }
    }
}
