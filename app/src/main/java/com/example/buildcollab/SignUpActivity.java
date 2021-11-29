package com.example.buildcollab;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.utils.checkInput;

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

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });
        checkname.normalInputVerification(name);
        checkemail.emailInputVerification(email);
        checkpassword.cpasswordVerification(password, cpassword);

    }

    private void sign_up() {
        if (checkname.isValid() && checkemail.isValid() && checkpassword.isValid()) {
            //save to rom

            //Verificar se ja existe este email registado
            if (true) {
                Toast.makeText(this, "Sign up completed", Toast.LENGTH_LONG).show();
                finish();
                Toast.makeText(this, "Sign up completed", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Email already registed", Toast.LENGTH_LONG).show();
            }

        }

    }

    private void getItemById() {
        goback = findViewById(R.id.goback);
        signup = findViewById(R.id.Signupbutton);
        name = findViewById(R.id.name_input);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        cpassword = findViewById(R.id.confirm_password_input);
    }
}