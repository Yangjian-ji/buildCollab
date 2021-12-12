package com.example.buildcollab.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.DatabaseHelperGroups;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.Project;

public class GroupProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);
        findViewById(R.id.goback).setOnClickListener(v -> finish());

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        int groupId = b.getInt("id");
        DatabaseHelperGroups database_helper = new DatabaseHelperGroups(getApplicationContext());
        Groups groups = database_helper.getGroup(String.valueOf(groupId));
        TextView title = findViewById(R.id.groupName);
        title.setText(groups.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText(groups.getDescription());
    }
}