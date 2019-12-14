package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mundi.databaseadmin.database.Login;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private MutableLiveData<String> mStr;
    private Handler mHandler;
    private String uri;
    private String mdbname;
    private String mUsername;
    private String mPassword;

    public LoginViewModel() {
        mStr = new MutableLiveData<String>();
        mHandler = new Handler();
        return;
    }
    public LiveData<String> getResponse() {
        Log.d(TAG, "getResponse()");
        return mStr;
    }

    public void setLoginData(String uri, String dbname, String username, String password) {
        this.uri = uri;
        this.mdbname = dbname;
        this.mUsername = username;
        this.mPassword = password;
        mHandler.post(getData);
    }

    private Runnable getData = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "getData()");
            Login login = new Login(uri, mdbname, mUsername, mPassword);
            String str = login.getResponseString();
            Log.d(TAG, "response = " + str);
            mStr.setValue(str);
            return;
        }
    };

}
