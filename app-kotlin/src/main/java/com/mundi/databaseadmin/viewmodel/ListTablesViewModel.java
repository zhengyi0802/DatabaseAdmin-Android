package com.mundi.databaseadmin.viewmodel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mundi.databaseadmin.database.ListTables
import java.util.*

class ListTablesViewModel : ViewModel() {
    private val mList: MutableLiveData<ArrayList<String>>
    private var mHandler: Handler? = null
    private var uri: String? = null
    private var mDbname: String? = null
    val listTables: LiveData<ArrayList<String>>
        get() {
            Log.d(TAG, "getListTables()")
            return mList
        }

    fun setDbname(dbname: String?) {
        Log.d(TAG, "setDbname()")
        mDbname = dbname
        mHandler = Handler()
        return
    }

    fun setUri(mUri: String?) {
        Log.d(TAG, "setUri()")
        uri = mUri
        return
    }

    fun refresh() {
        Log.d(TAG, "refresh()")
        mHandler!!.post(getData)
        return
    }

    private val getData = Runnable {
        Log.d(TAG, "refresh()")
        val conn = ListTables(uri, mDbname!!)
        mList.value = conn.tables
        return@Runnable
    }

    companion object {
        private const val TAG = "ListTablesViewModel"
    }

    init {
        Log.d(TAG, "ListTablesViewModel()")
        mList = MutableLiveData()
        return
    }
}