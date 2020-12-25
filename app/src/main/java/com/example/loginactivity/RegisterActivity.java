package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginactivity.databinding.ActivityRegisterBinding;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.model.RegisterModel;
import com.example.loginactivity.viewmodel.AppViewModel;

public class RegisterActivity extends AppCompatActivity {
    private AppViewModel appViewModel;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void onClickGroup(){
        binding.btDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
    }

    private void doRegister() {
        String fullname = binding.etNama.getText().toString();
        String accountnumber = binding.etNorek.getText().toString();
        String address = binding.etAlamat.getText().toString();
        String phonenumber = binding.etNohp.getText().toString();
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (fullname.equals("")&&accountnumber.equals("")&&address.equals("")&&phonenumber.equals("")&&username.equals("")&&password.equals("")){
            Toast.makeText(getApplicationContext(),"Mohon Lengkapi Data Registrasi", Toast.LENGTH_SHORT).show();
        } else {
            RegisterModel registerModel = new RegisterModel(fullname, accountnumber, address, phonenumber, username, password);
            appViewModel.registerNasabah(registerModel).observe(this, nasabahsResponse -> {
                System.out.println("register response : " + nasabahsResponse.getMessage());
                APIResponse response = nasabahsResponse;
                if (response.getResponse() == 200) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}