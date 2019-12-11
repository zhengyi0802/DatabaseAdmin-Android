package com.mundi.databaseadmin.viewmodel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mundi.databaseadmin.database.CreateDatabase

class CreateDatabaseViewModel : ViewModel() {
    private val mStr: MutableLiveData<String>
    private val mHandler: Handler
    private var uri: String? = null
    private var mdbname: String? = null
    val response: LiveData<String>
        get() {
            Log.d(TAG, "getResponse()")
            return mStr
        }

    fun setDbName(dbname: String?) {
        Log.d(TAG, "setDbName()")
        mdbname = dbname
        mHandler.post(getData)
        return
    }

    fun setUri(mUri: String?) {
        Log.d(TAG, "setUri()")
        uri = mUri
        return
    }

    private val getData = Runnable {
        Log.d(TAG, "getData()")
        val createDatabase = CreateDatabase(uri, mdbname!!)
        val ret = createDatabase.response
        val str = createDatabase.responseString
        mStr.value = str
        return@Runnable
    }

    companion object {
        private const val TAG = "ListDatabasesViewModel"
    }

    init {
        Log.d(TAG, "CreateDatabaseViewModel()")
        mStr = MutableLiveData()
        mHandler = Handler()
        return
    }
}