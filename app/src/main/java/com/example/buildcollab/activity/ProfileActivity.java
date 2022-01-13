package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.temp.ChatWithJohnActivity;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Users;
import com.example.buildcollab.utils.onclick;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton goback, editName, editDescription, editPortefolio, editWhatsAppContact;
    private ImageView qrcode;
    private Button gochat;
    private EditText description, interest, portefolio;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b = getIntent().getExtras();

        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDescription);
        editPortefolio = findViewById(R.id.editPortefolio);
        editWhatsAppContact = findViewById(R.id.editWhatsAppContact);
        description = findViewById(R.id.description);
        interest = findViewById(R.id.interest);
        portefolio = findViewById(R.id.portefolio);

        gochat = findViewById(R.id.chat);
        if (b == null) {
            gochat.setVisibility(View.GONE);
        } else {
            userId = b.getInt("id");
            DatabaseHelper database_helper = new DatabaseHelper(getApplicationContext());
            Users users = database_helper.getUser(String.valueOf(userId));
            TextView name = findViewById(R.id.userName);
            name.setText(users.getName());
            TextView description = findViewById(R.id.description);
            description.setText(users.getDescription());
            if (!String.valueOf(userId).equals(HomeActivity.getUserId())) {
                editDescription.setVisibility(View.GONE);
                gochat.setVisibility(View.VISIBLE);
                editName.setVisibility(View.GONE);
                editPortefolio.setVisibility(View.GONE);
                editWhatsAppContact.setVisibility(View.GONE);
                description.setFocusable(false);
                interest.setFocusable(false);
                portefolio.setFocusable(false);
            } else {
                gochat.setVisibility(View.GONE);
            }
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

        onclick.buttonEffect(goback);
        onclick.buttonEffect(gochat);
        onclick.buttonEffect(qrcode);
    }
}