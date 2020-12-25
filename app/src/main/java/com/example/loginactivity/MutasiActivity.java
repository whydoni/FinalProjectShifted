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

import java.util.ArrayList;

public class MutasiActivity extends AppCompatActivity {
    private ActivityMutasiBinding binding;
    private AppViewModel appViewModel;
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
//                if()
            }
        });
    }



}