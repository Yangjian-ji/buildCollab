package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.temp.ChatWithJohnActivity;
import com.example.buildcollab.utils.DatabaseHelperUser;
import com.example.buildcollab.utils.Users;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton goback;
    private ImageView qrcode;
    private Button gochat;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b = getIntent().getExtras();

        gochat = findViewById(R.id.chat);
        if (b == null) {
            gochat.setVisibility(View.GONE);
        } else {
            userId = b.getInt("id");
            DatabaseHelperUser database_helper = new DatabaseHelperUser(getApplicationContext());
            Users users = database_helper.getUser(String.valueOf(userId));
            TextView name = findViewById(R.id.userName);
            name.setText(users.getName());
            TextView description = findViewById(R.id.description);
            description.setText(users.getDescription());

        }

        goback = findViewById(R.id.goback);
        qrcode = findViewById(R.id.qrcodeimage);


        gochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatWithJohnActivity.class);
                startActivity(intent);
            }
        });


        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, QrCodeActivity.class);

                Bundle b = new Bundle();
                b.putInt("id", userId);
                b.putString("type", "user");
                intent.putExtras(b);

                startActivity(intent);
            }
        });


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}