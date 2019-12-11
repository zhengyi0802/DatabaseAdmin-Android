package com.mundi.databaseadmin.database;

import android.icu.util.IslamicCalendar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BackendConnection {

    private static final String TAG = "BackendConnection";

    private URL mUrl;
    private String mUrlString;
    private HttpURLConnection mConnect;
    private String responseString;
    private int ResponseCode;

    public BackendConnection(String url, String cmdstr) {
        Log.d(TAG, "BackendConnection()");
        Log.d(TAG, "uri = " + url);
        Log.d(TAG, "cmdstr = " + cmdstr);
        mUrlString = url + "?action=" + cmdstr;
        sendRequest();
    }

    public BackendConnection(String url, String cmdstr, JSONArray array) {
        Log.d(TAG, "BackendConnection()");
        mUrlString = url + "?action=" + cmdstr;
        sendRequest();
    }

    public  BackendConnection(String url, String cmdstr, JSONObject object) {
        Log.d(TAG, "BackendConnection()");
        mUrlString = url + "?action=" + cmdstr;
        sendRequest();
    }

    private void sendRequest() {
        Log.d(TAG, "sendRequest() uri = " + mUrlString);
        try {
            mUrl = new URL(mUrlString);
            mConnect = (HttpURLConnection) mUrl.openConnection();
            ResponseCode = mConnect.getResponseCode();
            if (ResponseCode == 200) {
                InputStream in = new BufferedInputStream(mConnect.getInputStream());
                getResponse(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mConnect.disconnect();
        }
        return;
    }

    private void getResponse(InputStream is) throws IOException {
        Log.d(TAG, "getResponse()");
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        responseString = sb.toString();
        return;
    }

    public String getResponseString() {
        return responseString;
    }

    public JSONArray getJSONArray() {
        Log.d(TAG, "getJSONArray()");
        try {
            JSONArray array = new JSONArray(responseString);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TableClass> getTableClass() {
        Log.d(TAG, "getTableClass()");
        try {
            JSONArray array = new JSONArray(responseString);
            ArrayList mList = new ArrayList<TableClass>();
            for (int i=0; i < array.length(); i++) {
                JSONObject jsonobj = array.getJSONObject(i);
                String field = jsonobj.getString("Field");
                String type = jsonobj.getString("Type");
                String nullstr = jsonobj.getString("Null");
                String key = jsonobj.getString("Key");
                String defaultstr = jsonobj.getString("Default");
                String extrastr = jsonobj.getString("Extra");
                TableClass mtable = new TableClass(field, type, nullstr, key, defaultstr, extrastr);
                mList.add(mtable);
            }
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getArrayListString() {
        Log.d(TAG, "getArrayListString()");
        try {
            JSONArray array = new JSONArray(responseString);
            ArrayList mList = new ArrayList<String>();
            for (int i = 0; i < array.length(); i++) {
                mList.add(array.getString(i));
                Log.d(TAG, "list name = " + array.getString(i));
            }
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJSONObject() {
        Log.d(TAG, "getJSONObject()");
        try {
            JSONObject object = new JSONObject(mUrlString);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getResposeCode() {
        Log.d(TAG, "getResposeCode()");
        return ResponseCode;
    }

}
