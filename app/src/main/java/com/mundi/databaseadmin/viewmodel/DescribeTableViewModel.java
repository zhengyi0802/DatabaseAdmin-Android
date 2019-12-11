package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mundi.databaseadmin.database.TableClass;

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

public class DescribeTableViewModel extends ViewModel {

    private final static String TAG = "DescribeTableViewModel";

    private MutableLiveData<ArrayList> mArrayList;
    private Handler mHandler;
    private URL mURL;
    private HttpURLConnection mConnect;
    private String mDbname;
    private String mTablename;

    public DescribeTableViewModel() {
        mArrayList = new MutableLiveData<ArrayList>();
        mHandler = new Handler();
    }

    public LiveData<ArrayList> getResponse() {
        return mArrayList;
    }

    public void setTablename(String dbname, String tablename) {
        mDbname = dbname;
        mTablename = tablename;
        mHandler.post(getData);
    }

    private Runnable getData = new Runnable() {

        @Override
        public void run() {
            try {
                String urlstr = "http://db.mundi-tv.tk/mobile.php?action=describetable&dbname=" +
                        mDbname + "&tablename=" + mTablename;
                Log.d(TAG, "url = " + urlstr);
                mURL = new URL(urlstr);
                mConnect = (HttpURLConnection) mURL.openConnection();
                InputStream in = new BufferedInputStream(mConnect.getInputStream());
                readStream(in);
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                mConnect.disconnect();
            }
        }

        private void readStream(InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
            for (String line = r.readLine(); line != null; line =r.readLine()){
                sb.append(line);
            }
            is.close();
            try {
                JSONArray jsonarray = new JSONArray(sb.toString());
                ArrayList mList = new ArrayList<TableClass>();
                for (int i=0; i < jsonarray.length(); i++) {
                    JSONObject jsonobj = jsonarray.getJSONObject(i);
                    String field = jsonobj.getString("Field");
                    String type = jsonobj.getString("Type");
                    String nullstr = jsonobj.getString("Null");
                    String key = jsonobj.getString("Key");
                    String defaultstr = jsonobj.getString("Default");
                    String extrastr = jsonobj.getString("Extra");
                    TableClass mtable = new TableClass(field, type, nullstr, key, defaultstr, extrastr);
                    mList.add(mtable);
                }
                mArrayList.setValue(mList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //mStr.setValue(sb.toString());
            Log.d(TAG, sb.toString());
        }

    };

}
