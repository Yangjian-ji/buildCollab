package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
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
        //Efeitos do clique
        onclick.buttonEffect(gotologin);
        onclick.buttonEffect(gotosignup);
        /**
         * Depois do clique salta para o login
         */
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, LoginActivity.class);
                startActivity(intent);
            }
        });
        /**
         * Depois do clique salta para o signup
         */
        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, SignUpActivity.class);
                startActivity(intent);

            }

        });
    }

    /**
     * Pega as coisas pelo id
     */
    public void getItembyId() {
        gotosignup = findViewById(R.id.Signupbutton);
        gotologin = findViewById(R.id.Loginbutton);
    }


}