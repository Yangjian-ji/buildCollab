package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

public class ProjectActivity extends AppCompatActivity {

    private ImageButton goback, editproject, editDescription, editRequiredKnowledge;
    private EditText description, areaOfWork, requiredKnowledge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        goback = findViewById(R.id.goback);
        goback.setOnClickListener(v -> finish());


        ImageView profile = findViewById(R.id.projectPhoto);


        onclick.buttonEffect(goback);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        int projectId = b.getInt("id");
        DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
        Project project = database_helper.getProject(String.valueOf(projectId));

        TextView projectName = findViewById(R.id.projectName);
        projectName.setText(project.getTitle());


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, ProjectProfileActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
