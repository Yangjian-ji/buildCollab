package com.example.buildcollab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.utils.onclick;

public class MainActivity extends AppCompatActivity {
    Button gotosignup, gotologin;
    private MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        getItembyId();

        onclick.buttonEffect(gotologin);
        onclick.buttonEffect(gotosignup);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, LoginActivity.class);
                startActivity(intent);
            }
        });

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getItembyId(){
        gotosignup = findViewById(R.id.Signupbutton);
        gotologin = findViewById(R.id.Loginbutton);
    }


}