package com.example.socialgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button btnLogin = findViewById(R.id.splashLoginBtn);
        Button btnSignup = findViewById(R.id.splashSignupBtn);
        ImageView logo = findViewById(R.id.imageView3);


        String imageName = "logo";

        int imgId = getResources().getIdentifier(imageName,"drawable", getPackageName());
        logo.setImageResource(imgId);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this , SignupActivity.class);
                startActivity(intent);
            }
        });






    }
}