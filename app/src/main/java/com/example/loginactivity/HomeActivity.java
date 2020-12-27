package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.loginactivity.adapter.ViewPagerAdapter;
import com.example.loginactivity.databinding.ActivityHomeBinding;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.model.MutasiModel;
import com.example.loginactivity.model.NasabahModel;
import com.example.loginactivity.viewmodel.AppViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    private ActivityHomeBinding binding;
    private AppViewModel appViewModel;
    ArrayList<NasabahModel> nasabahArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
        viewPager = (ViewPager) findViewById(R.id.image_switcher);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
    }

    void init(){
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.loginactivity.login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("com.example.loginactivity.login", "");
        System.out.println("Username : " + username);
        appViewModel.getNasabah(username).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                System.out.println(apiResponse.getMessage());
                NasabahModel nasabahs;
                Type listType = new TypeToken<NasabahModel>(){}.getType();
                nasabahs = new Gson().fromJson(apiResponse.getMessage(), listType);
                binding.tvUser.setText(nasabahs.getFullname());
                binding.tvRekening.setText(nasabahs.getAccountnumber());
                binding.tvSaldo.setText(nasabahs.getBalance().toString());
                SharedPreferences sharedPreferencesNorek = getSharedPreferences("sharedNorek", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorNorek = sharedPreferencesNorek.edit();
                editorNorek.putString("sharedNorek", binding.tvRekening.getText().toString());
                editorNorek.apply();
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