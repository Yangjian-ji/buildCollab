package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.onclick;

public class GroupActivity extends AppCompatActivity {

    private ImageButton goback;
    private ImageView groupProfile;
    private Button newPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);


        goback = findViewById(R.id.goback);
        groupProfile = findViewById(R.id.groupPhoto);
        newPost = findViewById(R.id.newPost);

        onclick.buttonEffect(goback);
        onclick.buttonEffect(newPost);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        groupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, GroupProfileActivity.class);
                startActivity(intent);
            }
        });

        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
    }
}