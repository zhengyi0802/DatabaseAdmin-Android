package com.mundi.databaseadmin.database;

import android.util.Log;

import java.util.ArrayList;

public class ListTables {

    private static final String TAG = "ListDatabases";

    private BackendConnection backendConnection;
    public ListTables(String uri, String databasename) {
        Log.d(TAG, "ListTables()");
        String cmd = "listtables&dbname=" + databasename;
        backendConnection = new BackendConnection(uri, cmd);
    }

    public ArrayList<String> getTables() {
        Log.d(TAG, "getTables()");
        return backendConnection.getArrayListString();
    }

}
