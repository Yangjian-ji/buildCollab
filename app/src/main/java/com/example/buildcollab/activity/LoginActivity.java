package com.example.buildcollab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildcollab.R;
import com.example.buildcollab.utils.DatabaseHelper;
import com.example.buildcollab.utils.Groups;
import com.example.buildcollab.utils.Project;
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
                login();
            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    login();
                    return true;
                }
                return false;
            }
        });

    }

    private void login() {
        DatabaseHelper DatabaseHelper = new DatabaseHelper(getApplicationContext());
        if (email.getText().toString().length() == 0 || email.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
            return;
        }
        Users user = DatabaseHelper.getUserByEmail(email.getText().toString());
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Email not found", Toast.LENGTH_LONG).show();
            return;
        }
        if (!user.getPassword().equals(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
            return;
        }

        //A eliminar no futuro-------------------------------------------------------------------


        if (DatabaseHelper.getUserByName("John Johnson") == null)
            DatabaseHelper.addUser("John Johnson", "john.com", "john");

        boolean exist = false;
        for (Groups group : DatabaseHelper.getGroups()) {
            if (group.getTitle().equals("Fashion World")) {
                exist = true;
                break;
            }
        }

        if (!exist)
            DatabaseHelper.addGroups("Fashion World", "This is Fashion description", "0");


        exist = false;

        for (Project project : DatabaseHelper.getProjects()) {
            if (project.getTitle().equals("Game Developer")) {
                exist = true;
                break;
            }
        }
        if (!exist)
            DatabaseHelper.addProject("Game Developer", "We want create a game", "0");
        //Fim --------------------------------------------------------------------
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle b = new Bundle();
        b.putInt("userId", Integer.parseInt(user.getUserId()));
        intent.putExtras(b);
        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }

    private void getItemById() {
        goback = findViewById(R.id.goback_login);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        login = findViewById(R.id.login);
    }
}