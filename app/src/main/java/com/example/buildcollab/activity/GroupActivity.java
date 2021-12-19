package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.GroupPost;
import com.example.buildcollab.utils.GroupPostsAdapter;
import com.example.buildcollab.utils.onclick;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {

    private ImageButton goback;
    private ImageView groupProfile;
    private Button newPost;
    private Button invite;
    private RecyclerView mRecyclerView;
    private DatabaseHelper database_helper;
    private TextView emptyView;
    private TextView groupTitle;
    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);


        goback = findViewById(R.id.goback);
        groupProfile = findViewById(R.id.groupPhoto);
        newPost = findViewById(R.id.newPost);
        mRecyclerView = findViewById(R.id.reclycleview);
        emptyView = findViewById(R.id.emptyMessage);
        groupTitle = findViewById(R.id.groupName);
        invite = findViewById(R.id.invite);

        database_helper = new DatabaseHelper(getApplicationContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        onclick.buttonEffect(goback);
        onclick.buttonEffect(newPost);
        onclick.buttonEffect(invite);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        groupId = String.valueOf(b.getInt("id"));
        groupTitle.setText(database_helper.getGroup(groupId).getTitle());

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        groupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, GroupProfileActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(groupId));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, NewPostActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(groupId));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void displayPosts() {
        ArrayList<GroupPost> posts = database_helper.getPostsGroup(groupId);
        if (posts.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        GroupPostsAdapter.OnItemClickListener listener = post -> {
            System.out.println(post); //TODO create activity to see full post

//            Intent intent;
//            if (database_helper.isUserInGroup(HomeActivity.getUserId(), groups.getGroupId()))
//                intent = new Intent(getActivity(), GroupActivity.class);
//            else
//                intent = new Intent(getActivity(), GroupProfileActivity.class);
//            Bundle b = new Bundle();
//            b.putInt("id", Integer.parseInt(groups.getGroupId()));
//            intent.putExtras(b);
//            startActivity(intent);
        };
        GroupPostsAdapter mAdapter = new GroupPostsAdapter(getApplicationContext(), this, posts, listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerView != null) {
            displayPosts();
            mRecyclerView.invalidate();
        }
    }
}