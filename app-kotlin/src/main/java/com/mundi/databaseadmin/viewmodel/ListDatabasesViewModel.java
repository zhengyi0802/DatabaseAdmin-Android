package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mundi.databaseadmin.database.ListDatabases;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListDatabasesViewModel extends ViewModel {

    private final static String TAG = "ListDatabasesViewModel";

    private MutableLiveData<ArrayList<String>> mList;
    private Handler mHandler;
    private String uri;

    public ListDatabasesViewModel() {
        Log.d(TAG, "ListDatabasesViewModel()");
        mList = new MutableLiveData<ArrayList<String>>();
        mHandler = new Handler();
        return;
    }

    public void setUri(String mUri) {
        Log.d(TAG, "setUri()");
        uri = mUri;
        return;
    }

    public LiveData<ArrayList<String>> getListDatabases() {
        Log.d(TAG, "getListDatabases()");
        return mList;
    }

    public void refresh() {
        Log.d(TAG, "refresh()");
        mHandler.post(getData);
        return;
    }

    private Runnable getData = new Runnable() {

        @Override
        public void run() {
            Log.d(TAG, "getData()");
            ListDatabases conn = new ListDatabases(uri);
            if (conn.getResponse()) {
                mList.setValue(conn.getDatabases());
            }
            return;
        }
    };
}
