package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mundi.databaseadmin.database.ListTables;

import java.util.ArrayList;

public class ListTablesViewModel extends ViewModel {

    private final static String TAG = "ListTablesViewModel";

    private MutableLiveData<ArrayList<String>> mList;
    private Handler mHandler;
    private String uri;
    private String mDbname;

    public ListTablesViewModel() {
        Log.d(TAG, "ListTablesViewModel()");
        mList = new MutableLiveData<ArrayList<String>>();
        return;
    }

    public LiveData<ArrayList<String>> getListTables() {
        Log.d(TAG, "getListTables()");
        return mList;
    }

    public void setDbname(String dbname) {
        Log.d(TAG, "setDbname()");
        mDbname = dbname;
        mHandler = new Handler();
        return;
    }

    public void setUri(String mUri) {
        Log.d(TAG, "setUri()");
        uri = mUri;
        return;
    }

    public void refresh() {
        Log.d(TAG, "refresh()");
        mHandler.post(getData);
        return;
    }

    private Runnable getData = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "refresh()");
            ListTables conn = new ListTables(uri, mDbname);
            mList.setValue(conn.getTables());
            return;
        }

    };
}
