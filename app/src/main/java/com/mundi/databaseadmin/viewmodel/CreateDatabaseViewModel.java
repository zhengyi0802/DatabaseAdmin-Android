package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mundi.databaseadmin.database.CreateDatabase;

public class CreateDatabaseViewModel extends ViewModel {
    private final static String TAG = "ListDatabasesViewModel";

    private MutableLiveData<String> mStr;
    private Handler mHandler;
    private String uri;
    private String mdbname;

    public CreateDatabaseViewModel() {
        Log.d(TAG, "CreateDatabaseViewModel()");
        mStr = new MutableLiveData<String>();
        mHandler = new Handler();
        return;
    }

    public LiveData<String> getResponse() {
        Log.d(TAG, "getResponse()");
        return mStr;
    }

    public void setDbName(String dbname) {
        Log.d(TAG, "setDbName()");
        mdbname = dbname;
        mHandler.post(getData);
        return;
    }

    public void setUri(String mUri) {
        Log.d(TAG, "setUri()");
        uri = mUri;
        return;
    }

    private Runnable getData = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "getData()");
            CreateDatabase createDatabase = new CreateDatabase(uri, mdbname);
            boolean ret = createDatabase.getResponse();
            String str = createDatabase.getResponseString();
            mStr.setValue(str);
            return;
        }
    };
}
