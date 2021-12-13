package com.example.buildcollab.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Users;
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

        if (name.getText().length() < 3) {
            Toast.makeText(getApplicationContext(), "Name is too small", Toast.LENGTH_LONG).show();
            return;
        }
        if (email.getText().length() < 3) {
            Toast.makeText(getApplicationContext(), "Email is too small", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.getText().length() < 3) {
            Toast.makeText(getApplicationContext(), "Password is too small", Toast.LENGTH_LONG).show();
            return;
        }
        if (!cpassword.getText().toString().equals(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseHelper DatabaseHelper = new DatabaseHelper(getApplicationContext());
        Users user = DatabaseHelper.getUserByEmail(email.getText().toString());
        if (user != null) {
            Toast.makeText(getApplicationContext(), "An user already exists with this email", Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseHelper.addUser(name.getText().toString(), email.getText().toString(), password.getText().toString());
        Toast.makeText(signUpActivity, "Sign up completed", Toast.LENGTH_LONG).show();
        finish();
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