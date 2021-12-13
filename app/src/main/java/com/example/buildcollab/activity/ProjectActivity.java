package com.example.buildcollab.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelperProjects;
import com.example.buildcollab.utils.Project;
import com.example.buildcollab.utils.onclick;

public class ProjectActivity extends AppCompatActivity {

    private ImageButton goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_profile);
        goback = findViewById(R.id.goback);
        goback.setOnClickListener(v -> finish());

        onclick.buttonEffect(goback);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        int projectId = b.getInt("id");
        DatabaseHelperProjects database_helper = new DatabaseHelperProjects(getApplicationContext());
        Project project = database_helper.getProject(String.valueOf(projectId));
        TextView title = findViewById(R.id.projectName);
        title.setText(project.getTitle());
        TextView description = findViewById(R.id.description);
        description.setText(project.getDescription());
    }
}
