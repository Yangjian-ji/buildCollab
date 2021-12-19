package com.example.buildcollab.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.onclick;

public class NewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);


        ImageButton goback = findViewById(R.id.goback);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        onclick.buttonEffect(goback);

        EditText title = findViewById(R.id.groupName);
        EditText description = findViewById(R.id.groupDescription);

        Button button = findViewById(R.id.create);

        onclick.buttonEffect(button);

        button.setOnClickListener(v -> {
            String sTitle = title.getText().toString();
            String sDescription = description.getText().toString();
            if (sTitle.length() < 2 || sDescription.length() < 2) {
                Toast.makeText(this, "Title/Description is to short", Toast.LENGTH_LONG).show();
                return;
            }
            DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
            String groupId = database_helper.addGroups(sTitle, sDescription, HomeActivity.getUserId());
            database_helper.addUserToGroup(HomeActivity.getUserId(), groupId);
            Toast.makeText(this, "Group Created", Toast.LENGTH_LONG).show();
            finish();
        });

    }
}