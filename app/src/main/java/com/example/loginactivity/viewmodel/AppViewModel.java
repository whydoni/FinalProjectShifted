package com.example.loginactivity.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginactivity.model.APIResponse;
import com.example.loginactivity.model.LoginModel;
import com.example.loginactivity.model.RegisterModel;
import com.example.loginactivity.networking.AppRepository;

public class AppViewModel extends ViewModel {
    private MutableLiveData<APIResponse> mutableLiveData;
    private AppRepository appRepository;

    public void init(){
        if(mutableLiveData!=null){
            return;
        }
        appRepository = appRepository.getInstance();
    }

    public LiveData<APIResponse> loginNasabah(LoginModel loginModel){
        if (mutableLiveData==null){
            appRepository = AppRepository.getInstance();
        }
        mutableLiveData = appRepository.loginNasabah(loginModel);
        return mutableLiveData;
    }

    public LiveData<APIResponse> logoutNasabah(){
        if (mutableLiveData==null){
            appRepository = AppRepository.getInstance();
        }
        mutableLiveData = appRepository.logoutNasabah();
        return mutableLiveData;
    }

    public LiveData<APIResponse> registerNasabah(RegisterModel registerModel){
        if (mutableLiveData==null){
            appRepository = AppRepository.getInstance();
        }
        mutableLiveData = appRepository.registerNasabah(registerModel);
        return mutableLiveData;
    }

    public LiveData<APIResponse> getSaldo(String string){
        if (mutableLiveData == null){
            appRepository = AppRepository.getInstance();
        }
        mutableLiveData = appRepository.getSaldo(string);
        return mutableLiveData;
    }

    public LiveData<APIResponse> getMutasi(String accountnumber){
        if (mutableLiveData == null){
            appRepository = AppRepository.getInstance();
        }
        mutableLiveData = appRepository.getMutasi(accountnumber);
        return mutableLiveData;
    }
}
