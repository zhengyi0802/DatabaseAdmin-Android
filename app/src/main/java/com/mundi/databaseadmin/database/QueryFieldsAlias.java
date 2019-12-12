package com.mundi.databaseadmin.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QueryFieldsAlias {
    private static final String TAG = "QueryFieldsAlias";

    private String dbname;
    private String tablename;

    private BackendConnection backendConnection;
    public QueryFieldsAlias(String uri, String databasename, String tablename) {
        Log.d(TAG, "QueryFieldsAlias()");
        this.dbname = databasename;
        this.tablename = tablename;
        String cmd = "queryfieldsalias&dbname=" + databasename
                + "&tablename=" + tablename;
        backendConnection = new BackendConnection(uri, cmd);
    }

    public JSONArray getData() {
        Log.d(TAG, "getData()");
        return backendConnection.getJSONArray();
    }

    public List<FieldsClass> getListData() {
        Log.d(TAG, "getListData()");
        List<FieldsClass> mList = new ArrayList<FieldsClass>();
        JSONArray array = backendConnection.getJSONArray();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String field = object.getString("field");
                String description = object.getString("description");
                FieldsClass fieldsClass = new FieldsClass(tablename, field, description);
                mList.add(fieldsClass);
                Log.d(TAG, "tablename = " + tablename + " field = " + field
                        + "description = " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mList;
    }

}
