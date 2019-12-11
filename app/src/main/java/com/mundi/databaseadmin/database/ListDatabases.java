package com.mundi.databaseadmin.database;

import android.util.Log;

import java.util.ArrayList;

public class ListDatabases {

    private static final String TAG = "ListDatabases";
    private BackendConnection backendConnection;
    public ListDatabases(String uri) {
        Log.d(TAG,"ListDatabases()");
        String cmd = "listdatabases";
        backendConnection = new BackendConnection(uri, cmd);
    }

    public ArrayList<String> getDatabases() {
        Log.d(TAG, "getDatabases()");
        return backendConnection.getArrayListString();
    }

    public boolean getResponse() {
        if (backendConnection.getResposeCode() == 200) {
            return true;
        }
        return false;
    }

}
