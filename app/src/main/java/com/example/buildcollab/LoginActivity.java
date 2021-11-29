package com.example.buildcollab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.utils.checkInput;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity loginActivity;
    private EditText name, password;
    private Button login;
    private checkInput checkname, checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getItemById();
        loginActivity = this;
        checkname.normalInputVerification(name);
        checkpassword.normalInputVerification(password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                if (checkname.isValid() && checkpassword.isValid()) {
                    //Verificar se existe a conta e a pass ser igual

                    //TODO


                    //intent = new Intent(loginActivity , XXX.class)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    private void getItemById() {
        name = findViewById(R.id.name_input);
        password = findViewById(R.id.password_input);
        checkname = new checkInput();
        checkpassword = new checkInput();
        login = findViewById(R.id.login);
    }
}