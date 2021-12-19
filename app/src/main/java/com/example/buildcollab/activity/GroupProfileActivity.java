package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.onclick;

public class GroupProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);
        onclick.buttonEffect(findViewById(R.id.goback));
        findViewById(R.id.goback).setOnClickListener(v -> finish());

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        String groupId = String.valueOf(b.getInt("id"));
        DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
        Groups groups = database_helper.getGroup(String.valueOf(groupId));
        TextView title = findViewById(R.id.groupName);
        title.setText(groups.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText(groups.getDescription());

        Button askForInvite = findViewById(R.id.askForInvite);
        onclick.buttonEffect(askForInvite);

        Button messages = findViewById(R.id.messages);
        onclick.buttonEffect(messages);

        Button deleteGroup = findViewById(R.id.deleteGroup);
        onclick.buttonEffect(deleteGroup);

        Button leaveGroup = findViewById(R.id.leaveGroup);
        onclick.buttonEffect(leaveGroup);

        if (database_helper.isUserInGroup(HomeActivity.getUserId(), groupId)) {
            askForInvite.setVisibility(View.GONE);
            messages.setVisibility(View.VISIBLE);

            String groupOwner = database_helper.getGroup(groupId).getOwnerId();
            if (groupOwner.equals(HomeActivity.getUserId())) {
                leaveGroup.setVisibility(View.GONE);
                deleteGroup.setVisibility(View.VISIBLE);
            } else {
                leaveGroup.setVisibility(View.VISIBLE);
                deleteGroup.setVisibility(View.GONE);
            }
        } else {
            askForInvite.setVisibility(View.VISIBLE);
            messages.setVisibility(View.GONE);
            deleteGroup.setVisibility(View.GONE);
            leaveGroup.setVisibility(View.GONE);
        }

        onclick.buttonEffect(askForInvite);
        askForInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Requested to join group", Toast.LENGTH_LONG).show();
                database_helper.addUserToGroup(HomeActivity.getUserId(), String.valueOf(groupId));
                Intent intent = new Intent(GroupProfileActivity.this, GroupActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(groupId));
                intent.putExtras(b);
                startActivity(intent);

            }
        });


        onclick.buttonEffect(messages);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.addUserToGroup(HomeActivity.getUserId(), String.valueOf(groupId));
                Intent intent = new Intent(GroupProfileActivity.this, GroupActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(groupId));
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        onclick.buttonEffect(leaveGroup);
        leaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.removeUserGroup(HomeActivity.getUserId(), groupId);
                Toast.makeText(getApplicationContext(), "You left this group", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        onclick.buttonEffect(deleteGroup);
        deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.deleteGroup(groupId);
                Toast.makeText(getApplicationContext(),"You deleted this group", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}