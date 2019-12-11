package com.mundi.databaseadmin.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class QueryData {
    private static final String TAG = "QueryData";

    private BackendConnection backendConnection;
    public QueryData(String uri, String databasename, String tablename) {
        Log.d(TAG, "QueryData()");
        String cmd = "querydata&dbname=" + databasename + "&tablename=" + tablename;
        backendConnection = new BackendConnection(uri, cmd);
    }

    public JSONArray getData() {
        Log.d(TAG, "getData()");
        return backendConnection.getJSONArray();
    }

}
