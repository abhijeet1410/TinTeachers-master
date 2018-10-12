package com.abhijeet14.tinteachers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = findViewById(R.id.login_username);
        passwordText = findViewById(R.id.login_password);
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
    }
}
