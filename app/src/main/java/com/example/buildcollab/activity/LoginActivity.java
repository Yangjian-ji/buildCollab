package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.checkInput;
import com.example.buildcollab.utils.onclick;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity loginActivity;
    private EditText email, password;
    private Button login;
    private ImageButton goback;
    private checkInput checkemail, checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getItemById();
        loginActivity = this;

        checkemail = new checkInput();
        checkpassword = new checkInput();
        //Inicia as verificacoes
        checkemail.normalInputVerification(email);
        checkpassword.normalInputVerification(password);

        //Efeito do clique
        onclick.buttonEffect(goback);
        onclick.buttonEffect(login);


        //Volta a pagina anterior
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Da o login e vai a homepage
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                //  if (checkemail.isValid() && checkpassword.isValid()) {
                //Verificar se existe a conta e a pass ser igual

                //TODO


                intent = new Intent(loginActivity, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                //}

            }
        });

    }

    private void getItemById() {
        goback = findViewById(R.id.goback_login);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);

        login = findViewById(R.id.login);
    }
}