package com.example.buildcollab.activity;

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
import com.example.buildcollab.utils.Users;
import com.example.buildcollab.utils.onclick;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        ImageButton goback = findViewById(R.id.goback);
        Button create = findViewById(R.id.create);
        TextView title = findViewById(R.id.title);
        EditText postTitle = findViewById(R.id.postTitle);
        EditText postDescription = findViewById(R.id.postDescription);

        onclick.buttonEffect(goback);
        onclick.buttonEffect(create);

        Bundle b = getIntent().getExtras();
        if (b == null)
            finish();
        String groupId = String.valueOf(b.getInt("id"));

        DatabaseHelper DatabaseHelper = new DatabaseHelper(getApplicationContext());

        title.setText(DatabaseHelper.getGroup(groupId).getTitle());

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postTitle.getText().length() < 3) {
                    Toast.makeText(getApplicationContext(), "Title is too small!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (postDescription.getText().length() < 3) {
                    Toast.makeText(getApplicationContext(), "Description is too small!", Toast.LENGTH_LONG).show();
                    return;
                }
                DatabaseHelper.addPostGroup(HomeActivity.getUserId(), groupId, postTitle.getText().toString(), postDescription.getText().toString());
                Toast.makeText(NewPostActivity.this, "Post created successfully!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}