package com.mundi.databaseadmin.database;

import android.util.Log;

import java.util.ArrayList;

public class CreateDatabase {

    private static final String TAG = "CreateDatabase";

    private BackendConnection backendConnection;
    public CreateDatabase(String uri, String databasename) {
        Log.d(TAG, "ListTables()");
        String cmd = "createdatabase&dbname=" + databasename;
        backendConnection = new BackendConnection(uri, cmd);
        return;
    }

    public boolean getResponse() {
        Log.d(TAG, "getResponse()");
        if (backendConnection.getResposeCode() == 200) {
            return true;
        }
        return false;
    }

    public String getResponseString() {
        Log.d(TAG, "getResponseString()");
        return backendConnection.getResponseString();
    }

}
