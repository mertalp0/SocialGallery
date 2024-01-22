package com.example.socialgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialgallery.service.AuthCallback;
import com.example.socialgallery.service.AuthService;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
private AuthService auth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        EditText email =findViewById(R.id.txtSignupEmail);
        EditText password =findViewById(R.id.txtSignupPassword);
        EditText firstName =findViewById(R.id.txtFirstName);
        EditText lastName =findViewById(R.id.txtLastName);
        Button btnLogin = findViewById(R.id.signupbtnLogin);
        Button btnSignup = findViewById(R.id.signupbtnSignup);
        auth = new AuthService(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: GİRİŞ İŞLEMLERİ
                Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: GİRİŞ İŞLEMLERİ
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String firstNameText = firstName.getText().toString();
                String lastNameText = lastName.getText().toString();

                if(emailText.isEmpty()){
                    showToast("email boş olamaz");
                    return;
                }
                if(passwordText.isEmpty()){
                    showToast("şifre boş olamaz");
                    return;
                }
                if(firstNameText.isEmpty()){
                    showToast("isim boş olamaz");
                    return;
                }
                if(lastNameText.isEmpty()){
                    showToast("soy isim boş olamaz");
                    return;
                }
                if(passwordText.length() < 6 ){
                    showToast("şifre en az altı karakterli olmalı");
                    return;
                }

                auth.signup(emailText,passwordText,firstNameText,lastNameText ,new AuthCallback(){
                    @Override
                    public void onSuccess(FirebaseUser user) {
                        showToast("Kayıt Başarılı. Giriş Yapabilirsiniz. ");
                    }
                    @Override
                    public void onFailure(String errorMessage) {
                        showToast("Kayıt Başarısız. Hata: "+  errorMessage);
                    }
                });
            }
        });
    }
    private void showToast (String message){
        Toast.makeText(this,message , Toast.LENGTH_SHORT).show();
    }
}