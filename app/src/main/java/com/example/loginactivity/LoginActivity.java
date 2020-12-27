package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.loginactivity.databinding.ActivityLoginBinding;
import com.example.loginactivity.model.LoginModel;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.viewmodel.AppViewModel;

public class LoginActivity extends AppCompatActivity {
    private AppViewModel appViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    @SuppressLint("ClickableViewAccessibility")
    void onClickGroup(){
        binding.btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister();
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        binding.btReveal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    case MotionEvent.ACTION_UP:
                        binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                }
                return false;
            }
        });
    }

    private void init(){
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
    }

    private void doLogin() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (password.equals("")&&username.equals("")){
            Toast.makeText(getApplicationContext(),"Mohon Lengkapi username dan password", Toast.LENGTH_SHORT).show();
        } else {
            LoginModel loginModel = new LoginModel(username, password);
            appViewModel.loginNasabah(loginModel).observe(this, nasabahsResponse -> {
                System.out.println("login response : " + nasabahsResponse.getMessage());
                APIResponse response = nasabahsResponse;
                if (response.getResponse() == 200) {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.loginactivity.login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("com.example.loginactivity.login", binding.etUsername.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void gotoRegister() {
        Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}