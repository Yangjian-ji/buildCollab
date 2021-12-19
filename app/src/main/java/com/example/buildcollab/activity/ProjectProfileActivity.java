package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

public class ProjectProfileActivity extends AppCompatActivity {

    private ImageButton editproject, editDescription, editRequiredKnowledge;
    private EditText description, areaOfWork, requiredKnowledge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_profile);
        ImageButton goback = findViewById(R.id.goback);
        Button askforInvite = findViewById(R.id.askForInvite);
        goback.setOnClickListener(v -> finish());


        Button leaveProject = findViewById(R.id.leaveProject);
        Button deleteProject = findViewById(R.id.deleteProject);


        editproject = findViewById(R.id.editproject);
        editDescription = findViewById(R.id.editDescription);
        editRequiredKnowledge = findViewById(R.id.editRequiredKnowledge);
        description = findViewById(R.id.description);
        areaOfWork = findViewById(R.id.areaOfWork);
        requiredKnowledge = findViewById(R.id.requiredKnowledge);


        onclick.buttonEffect(goback);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        int projectId = b.getInt("id");
        DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
        Project project = database_helper.getProject(String.valueOf(projectId));
        TextView title = findViewById(R.id.projectName);
        title.setText(project.getTitle());


        String projectIdS = String.valueOf(b.getInt("id"));
        String projectOwner = database_helper.getProject(projectIdS).getOwnerId();
        if (projectOwner.equals(HomeActivity.getUserId())) {
            leaveProject.setVisibility(View.GONE);
            deleteProject.setVisibility(View.VISIBLE);
            askforInvite.setVisibility(View.GONE);
        } else {
            if (database_helper.isUserInProject(HomeActivity.getUserId(), projectIdS)) {

                leaveProject.setVisibility(View.VISIBLE);
                deleteProject.setVisibility(View.GONE);
                askforInvite.setVisibility(View.GONE);
            } else {

                leaveProject.setVisibility(View.GONE);
                deleteProject.setVisibility(View.GONE);
            }

            description.setText(project.getDescription());
            editproject.setVisibility(View.GONE);
            editDescription.setVisibility(View.GONE);
            editRequiredKnowledge.setVisibility(View.GONE);
            description.setFocusable(false);
            areaOfWork.setFocusable(false);
            requiredKnowledge.setFocusable(false);
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
        onclick.buttonEffect(askforInvite);
        askforInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.addUserToProject(HomeActivity.getUserId(), String.valueOf(projectId));
                Intent intent = new Intent(ProjectProfileActivity.this, ProjectActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", projectId);
                Toast.makeText(ProjectProfileActivity.this, "Invite Requested", Toast.LENGTH_LONG).show();
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}