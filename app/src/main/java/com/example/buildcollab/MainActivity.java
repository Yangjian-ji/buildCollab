package com.example.buildcollab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button gotosignup, gotologin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotosignup = findViewById(R.id.Signupbutton);
        gotologin = findViewById(R.id.Loginbutton);
        setListener();
    }

    private void setListener() {
        Onclick onclick = new Onclick();
        gotosignup.setOnClickListener(onclick);
        gotologin.setOnClickListener(onclick);

    }

    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch (v.getId()) {
                case R.id.Signupbutton:
                    //       intent = new Intent(this, MainActivity.class);
                    break;
                case R.id.Loginbutton:
                    //      intent = new Intent(this, MainActivity.class);
                    break;

            }
            startActivity(intent);
            finish();
        }
    }
}