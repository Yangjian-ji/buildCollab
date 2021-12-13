package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        int groupId = b.getInt("id");
        DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
        Groups groups = database_helper.getGroup(String.valueOf(groupId));
        TextView title = findViewById(R.id.groupName);
        title.setText(groups.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText(groups.getDescription());


        Button askForInvite = findViewById(R.id.askForInvite);
        onclick.buttonEffect(askForInvite);

        askForInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.addUserToGroup(HomeActivity.getUserId(), String.valueOf(groupId));
                Intent intent = new Intent(GroupProfileActivity.this, GroupActivity.class);
                startActivity(intent);

            }
        });
    }

}