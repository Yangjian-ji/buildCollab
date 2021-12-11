package com.example.buildcollab.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelperGroups;

public class NewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);


        EditText title = findViewById(R.id.groupName);
        EditText description = findViewById(R.id.groupDescription);

        Button button = findViewById(R.id.create);
        button.setOnClickListener(v -> {
            String sTitle = title.getText().toString();
            String sDescription = description.getText().toString();
            if (sTitle.length() < 2 || sDescription.length() < 2) {
                Toast.makeText(this, "Title/Description is to short", Toast.LENGTH_LONG).show();
                return;
            }
            DatabaseHelperGroups database_helper = new DatabaseHelperGroups(getApplicationContext());
            database_helper.addGroups(sTitle, sDescription);
            Toast.makeText(this, "Group Created", Toast.LENGTH_LONG).show();
            finish();
        });

    }
}