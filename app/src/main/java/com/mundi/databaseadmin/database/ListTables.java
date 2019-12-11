package com.mundi.databaseadmin.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTables {

    private static final String TAG = "ListDatabases";

    private BackendConnection backendConnection;
    private String dbname;
    private String uri;
    public ListTables(String uri, String databasename) {
        Log.d(TAG, "ListTables()");
        this.dbname = databasename;
        this.uri = uri;
        String cmd = "listtables&dbname=" + databasename;
        backendConnection = new BackendConnection(uri, cmd);
        getTableAlias();
    }

    public ArrayList<String> getTables() {
        Log.d(TAG, "getTables()");
        return backendConnection.getArrayListString();
    }

    public void  getTableAlias() {
        String cmd = "querydata&dbname=" + dbname + "&tablename=tables_alias";
        backendConnection = new BackendConnection(uri, cmd);
    }

    public JSONArray getJSONArray() {
        return backendConnection.getJSONArray();
    }

    public List<TablesClass> getTablesClass() {
        List<TablesClass> mTablesClass = new ArrayList<TablesClass>();
        try {
            JSONArray array = backendConnection.getJSONArray();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Iterator<String> sIterator = object.keys();
                String tablename = null;
                String title = null;
                while(sIterator.hasNext()) {
                    String key = sIterator.next();
                    String value = object.getString(key);
                    if (key.equals("tablename")) {
                        tablename = value;
                    } else if (key.equals("description")) {
                        title = value;
                    }
                }
                TablesClass tablesClass = new TablesClass(tablename, title);
                mTablesClass.add(tablesClass);
            }
            return mTablesClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
