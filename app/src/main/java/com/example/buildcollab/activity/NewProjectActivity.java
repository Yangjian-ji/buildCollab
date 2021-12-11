package com.example.buildcollab.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

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
            database_helper.addProject(sTitle, sDescription);
            Toast.makeText(this,"Project Created",Toast.LENGTH_LONG).show();
            finish();
        });

    }

}