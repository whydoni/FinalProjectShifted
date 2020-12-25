package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.loginactivity.databinding.ActivityHomeBinding;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.viewmodel.AppViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void init(){
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.loginactivity.login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("com.example.loginactivity.login", "");
        System.out.println("Username : " + username);
        binding.tvUser.setText(username);
        appViewModel.getSaldo(username).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                DecimalFormat kurs = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("IDR ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');
                kurs.setDecimalFormatSymbols(formatRp);
                Double saldo = Double.parseDouble(apiResponse.getMessage());
                binding.tvSaldo.setText(kurs.format(saldo));
            }
        });
    }

    void onClickGroup() {
        binding.ibtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogout();
            }
        });
        binding.ibtCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreditActivity.class);
                startActivity(intent);
            }
        });
        binding.ibtMutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MutasiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void doLogout() {
        appViewModel.logoutNasabah().observe(this, nasabahsResponse -> {
            System.out.println("login response : " + nasabahsResponse.getMessage());
            APIResponse response = nasabahsResponse;
            if (response.getResponse() == 200) {
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.loginactivity.login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("com.example.loginactivity.login", "");
                editor.apply();
                SharedPreferences sharedPreferencesNorek = getSharedPreferences("sharedNorek", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorNorek = sharedPreferencesNorek.edit();
                editorNorek.putString("sharedNorek", "");
                editorNorek.apply();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}