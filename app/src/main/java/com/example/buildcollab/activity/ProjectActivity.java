package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

public class ProjectActivity extends AppCompatActivity {

    private ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        goback = findViewById(R.id.goback);
        goback.setOnClickListener(v -> finish());

        Button leaveProject = findViewById(R.id.leaveProject);

        Button deleteProject = findViewById(R.id.deleteProject);


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


        String projectIdS = String.valueOf(b.getInt("id"));
        String projectOwner = database_helper.getProject(projectIdS).getOwnerId();
        if (projectOwner.equals(HomeActivity.getUserId())) {
            leaveProject.setVisibility(View.GONE);
            deleteProject.setVisibility(View.VISIBLE);
        } else {
            leaveProject.setVisibility(View.VISIBLE);
            deleteProject.setVisibility(View.GONE);
        }

        onclick.buttonEffect(leaveProject);
        leaveProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.removeUserProject(HomeActivity.getUserId(), projectIdS);
                Toast.makeText(getApplicationContext(), "You left the project", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        onclick.buttonEffect(deleteProject);
        deleteProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.deleteProject(projectIdS);
                Toast.makeText(getApplicationContext(), "You removed the project", Toast.LENGTH_LONG).show();
                finish();
            }
        });

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
