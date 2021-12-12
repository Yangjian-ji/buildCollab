package com.example.buildcollab.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelperUser;
import com.example.buildcollab.utils.checkInput;
import com.example.buildcollab.utils.onclick;

public class SignUpActivity extends AppCompatActivity {
    private SignUpActivity signUpActivity;
    private Button signup;
    private ImageButton goback;
    private EditText name, email, password, cpassword;
    private checkInput checkname, checkemail, checkpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpActivity = this;
        getItemById();

        checkpassword = new checkInput();
        checkname = new checkInput();
        checkemail = new checkInput();
        //Verificar os inputs
        checkname.normalInputVerification(name);
        checkemail.emailInputVerification(email);
        checkpassword.cpasswordVerification(password, cpassword);

        //Volta a pagina anterior
        goback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Efeitos do clique
        onclick.buttonEffect(goback);
        onclick.buttonEffect(signup);

        //Da o registo
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });


    }

    private void sign_up() {
        //  if (checkname.isValid() && checkemail.isValid() && checkpassword.isValid()) {

        //Verificar se ja existe este email registado e armazenar os dados
        //TODO
        if (true) {
            //Pop up de mensagem e fecha a pagina

            if (name.getText().length() > 0) {
                DatabaseHelperUser database_helper = new DatabaseHelperUser(getApplicationContext());
                database_helper.addUser(name.toString(), " ");
            }
            Toast.makeText(signUpActivity, "Sign up completed", Toast.LENGTH_LONG).show();
            finish();

        } else {

            //Pop up de mensagem
            Toast.makeText(signUpActivity, "Email already registed", Toast.LENGTH_LONG).show();
        }

        //  }

    }

    private void getItemById() {
        goback = findViewById(R.id.goback_signup);
        signup = findViewById(R.id.signup);
        name = findViewById(R.id.name_input);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        cpassword = findViewById(R.id.confirm_password_input);
    }
}