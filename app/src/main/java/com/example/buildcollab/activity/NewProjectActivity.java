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

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        ImageButton goback = findViewById(R.id.goback);

        EditText title = findViewById(R.id.projectTitle);
        EditText description = findViewById(R.id.projectDescription);


        Button button = findViewById(R.id.create);

        button.setOnClickListener(v -> {
            String sTitle = title.getText().toString();
            String sDescription = description.getText().toString();
            if (sTitle.length() < 2 || sDescription.length() < 2) {
                Toast.makeText(this, "Title/Description is to short", Toast.LENGTH_LONG).show();
                return;
            }
            DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
            String projectId = database_helper.addProject(sTitle, sDescription, HomeActivity.getUserId());
            database_helper.addUserToProject(HomeActivity.getUserId(), projectId);
            Toast.makeText(this, "Project Created", Toast.LENGTH_LONG).show();
            finish();
        });


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        onclick.buttonEffect(goback);
        onclick.buttonEffect(button);
    }

}