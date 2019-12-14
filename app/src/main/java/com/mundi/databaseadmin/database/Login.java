package com.mundi.databaseadmin.database;

import android.util.Log;

public class Login {
    private static final String TAG = "Login";

    private BackendConnection backendConnection;
    public Login(String uri, String databasename, String username, String password) {
        Log.d(TAG, "Login()");
        String cmd = "login&dbname=" + databasename +
                "&username=" + username + "&password=" + password;
        backendConnection = new BackendConnection(uri, cmd);
    }

    public String getResponseString() {
        Log.d(TAG, "getResponseString()");
        return backendConnection.getResponseString();
    }
}
