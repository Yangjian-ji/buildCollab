package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

public class ProjectProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_profile);
        ImageButton goback = findViewById(R.id.goback);
        Button askforInvite = findViewById(R.id.askForInvite);
        goback.setOnClickListener(v -> finish());


        onclick.buttonEffect(goback);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        int projectId = b.getInt("id");
        DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
        Project project = database_helper.getProject(String.valueOf(projectId));
        TextView title = findViewById(R.id.projectName);
        title.setText(project.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText(project.getDescription());

        onclick.buttonEffect(askforInvite);
        askforInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.addUserToProject(HomeActivity.getUserId(), String.valueOf(projectId));
                Intent intent = new Intent(ProjectProfileActivity.this, ProjectActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", projectId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}