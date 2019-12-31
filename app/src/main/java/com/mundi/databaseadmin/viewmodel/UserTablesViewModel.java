package com.mundi.databaseadmin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.mundi.databaseadmin.bean.CellData;
import com.mundi.databaseadmin.bean.ColumnTitle;
import com.mundi.databaseadmin.bean.RowTitle;
import com.mundi.databaseadmin.database.FieldsClass;
import com.mundi.databaseadmin.database.QueryData;
import com.mundi.databaseadmin.database.QueryFieldsAlias;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserTablesViewModel extends ViewModel {

    private final static String TAG = "UserTablesViewModel";

    private final static int INIT_ROWS = 100;
    private final static int INIT_COLUMNS = 27;

    private MutableLiveData<List<List<CellData>>> mDataList;
    private MutableLiveData<ArrayList<RowTitle>> mRowList;
    private MutableLiveData<ArrayList<ColumnTitle>> mColumnList;
    private List<FieldsClass> mFieldsClass;
    private Handler mHandler;
    private boolean remotedata = false;
    private String uri, dbname, tablename;

    public UserTablesViewModel() {

        Log.d(TAG, "constructor!");
        mDataList = new MutableLiveData<List<List<CellData>>>();
        mRowList  = new MutableLiveData<ArrayList<RowTitle>>();
        mColumnList = new MutableLiveData<ArrayList<ColumnTitle>>();
        mHandler = new Handler();
        if (remotedata) {
            mHandler.post(getData);
        } else {
            initRowTitle();
            initColumnTitle();
            initData();
        }
    }

    public void getfromRemote(String uri, String dbname, String tablename) {
        this.uri = uri;
        this.dbname = dbname;
        this.tablename = tablename;
        remotedata = true;
        mHandler.postDelayed(getData, 1000);
    }

    public LiveData<List<List<CellData>>> getCellData() {
        return mDataList;
    }

    public LiveData<ArrayList<RowTitle>> getRowData() {
        return mRowList;
    }

    public LiveData<ArrayList<ColumnTitle>> getColumnData() {
        return mColumnList;
    }

    private Runnable getData = new Runnable() {
        @Override
        public void run() {
            QueryFieldsAlias queryFieldsAlias =
                    new QueryFieldsAlias(uri, dbname, tablename);
            mFieldsClass = queryFieldsAlias.getListData();
            initColumnTitle();
            QueryData queryData = new QueryData(uri, dbname, tablename);
            JSONArray array = queryData.getData();
            parseData(array);
            mHandler.removeCallbacks(getData);
        }
    };

    private void initRowTitle() {
        Log.d(TAG, "initRowTitle!");
        ArrayList mRowTitleList = new ArrayList<RowTitle>();
        for (int i = 1; i < INIT_ROWS; i++) {
            RowTitle mRowTitle = new RowTitle(i);
            mRowTitleList.add(mRowTitle);
        }
        mRowList.setValue(mRowTitleList);
        return;
    }

    private void initColumnTitle() {
        Log.d(TAG, "initColumnTitle!");
        ArrayList mColumnTitleList = new ArrayList<ColumnTitle>();
        if(remotedata) {
            for (int i=0; i < mFieldsClass.size(); i++) {
                String title = mFieldsClass.get(i).getTitle();
                String field = mFieldsClass.get(i).getFieldname();
                ColumnTitle mColumnTitle = new ColumnTitle(i, title, field);
                Log.d(TAG, "field = " + field + "title = " + title);
                mColumnTitleList.add(mColumnTitle);
            }
        } else {
            for (int i = 1; i < INIT_COLUMNS; i++) {
                int j = Integer.valueOf('A') + i - 1;
                String str = Character.toString((char) j);
                ColumnTitle mColumnTitle = new ColumnTitle(i, str, str);
                mColumnTitleList.add(mColumnTitle);
            }
        }
        mColumnList.setValue(mColumnTitleList);
        return;
    }

    private void initData() {
        Log.d(TAG, "initData!");
        List<List<CellData>> lldata = new ArrayList<List<CellData>>();
        for(int i=0; i< INIT_ROWS; i++) {
            List<CellData> lCellData = new ArrayList<CellData>();
            for(int k=0; k < INIT_COLUMNS; k++) {
                CellData mCellData = new CellData(i, k, null);
                lCellData.add(k, mCellData);
            }
            lldata.add(lCellData);
        }
        mDataList.setValue(lldata);
        return;
    }

    private void parseData(JSONArray array) {
        try {
            int index = 0;
            int pos = 0;
            //ArrayList mColumnTitleList = new ArrayList<ColumnTitle>();
            ArrayList mRowTitleList = new ArrayList<RowTitle>();
            List<List<CellData>> lldata = new ArrayList<List<CellData>>();
            // get column title from first record key
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                List<CellData> lCellData =
                        new ArrayList<CellData>();
                for (int j = 0; j < mFieldsClass.size(); j++) {
                    CellData mCellData = new CellData(i, j, null);
                    lCellData.add(mCellData);
                }
                Iterator<String> sIterator = object.keys();
                index = 1;
                while(sIterator.hasNext()) {
                    String key = sIterator.next();
                    String value = object.getString(key);
                    if (key.equals("id")) {
                        index = Integer.parseInt(value);
                        RowTitle mRowTitle = new RowTitle(index);
                        mRowTitleList.add(mRowTitle);
                    } else if (key.equals("active_id")) {
                        if (value.equals("ACTIVATIVE")) {

                        }
                        continue;
                    } else {
                        for(pos = 0; pos < mFieldsClass.size(); pos++) {
                            FieldsClass fieldsClass = mFieldsClass.get(pos);
                            if(fieldsClass.getFieldname().equals(key)) {
                                break;
                            }
                        }
                        CellData mCellData = new CellData(i, pos, value);
                        lCellData.set(pos, mCellData);
                    }
                    Log.d(TAG, "index = " + index + " pos = " + pos);
                    Log.d(TAG, "key = " + key + " value = " + value);
                }
                lldata.add(lCellData);
            }
            //mColumnList.setValue(mColumnTitleList);
            mRowList.setValue(mRowTitleList);
            mDataList.setValue(lldata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
