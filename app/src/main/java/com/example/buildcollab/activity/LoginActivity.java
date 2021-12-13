package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelperGroups;
import com.example.buildcollab.utils.DatabaseHelperUser;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.Users;
import com.example.buildcollab.utils.checkInput;
import com.example.buildcollab.utils.onclick;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login;
    private ImageButton goback;
    private checkInput checkemail, checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getItemById();

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
                DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(getApplicationContext());
                if (email.getText().toString().length() == 0 || email.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
                    return;
                }
                Users user = databaseHelperUser.getUserByEmail(email.getText().toString());
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Email not found", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!user.getPassword().equals(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
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