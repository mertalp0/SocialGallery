package com.example.socialgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.socialgallery.service.AuthCallback;
import com.example.socialgallery.service.AuthService;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.loginBtnLogin);
        Button btnSignup = findViewById(R.id.loginBtnSignup);
        EditText textEmail = findViewById(R.id.loginEmailTxt);
        EditText textPassword = findViewById(R.id.loginPasswordTxt);

        authService = new AuthService(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "E-mail Boş Olamaz", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Şifre Boş Olamaz", Toast.LENGTH_SHORT).show();
                    return;
                }

                authService.login(email, password, new AuthCallback() {
                    @Override
                    public void onSuccess(FirebaseUser user) {
                        showToast("Giriş başarılı. Hoş geldiniz." );
                        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        showToast("Giriş başarısız. Hata: " + errorMessage);
                    }
                });
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}