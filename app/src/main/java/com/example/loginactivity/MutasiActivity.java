package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.loginactivity.adapter.MutasiAdapter;
import com.example.loginactivity.databinding.ActivityMutasiBinding;
import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.model.MutasiModel;
import com.example.loginactivity.viewmodel.AppViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MutasiActivity extends AppCompatActivity {
    ActivityMutasiBinding binding;
    AppViewModel appViewModel;
    ArrayList<MutasiModel> mutasiArrayList = new ArrayList<>();
    MutasiAdapter mutasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMutasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    void init() {
        if (mutasiAdapter == null) {
            mutasiAdapter = new MutasiAdapter(getApplicationContext(), mutasiArrayList);
            binding.rvlistMutasi.setLayoutManager(new LinearLayoutManager(this));
            binding.rvlistMutasi.setAdapter(mutasiAdapter);
            binding.rvlistMutasi.setItemAnimator(new DefaultItemAnimator());
            binding.rvlistMutasi.setNestedScrollingEnabled(true);
        } else {
            mutasiAdapter.notifyDataSetChanged();
        }
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.init();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedNorek", Context.MODE_PRIVATE);
        String accountnumber = sharedPreferences.getString("sharedNorek", "");
        System.out.println("accountnumber : " + accountnumber);
        appViewModel.getMutasi(accountnumber).observe(this, new Observer<APIResponse>() {
            @Override
            public void onChanged(APIResponse apiResponse) {
                System.out.println(apiResponse.getMessage());
                List<MutasiModel> mutasis;
                Type listType = new TypeToken<List<MutasiModel>>(){}.getType();
                mutasis = new Gson().fromJson(apiResponse.getMessage(), listType);
                mutasiArrayList.clear();
                mutasiArrayList.addAll(mutasis);
                mutasiAdapter.notifyDataSetChanged();
            }
        });
    }



}