package com.mundi.databaseadmin.database

import android.util.Log
import java.util.ArrayList

class ListTables(uri: String?, databasename: String) {
    private val backendConnection: BackendConnection
    val tables: ArrayList<String>
        get() {
            Log.d(TAG, "getTables()")
            return backendConnection.arrayListString
        }

    companion object {
        private const val TAG = "ListDatabases"
    }

    init {
        Log.d(TAG, "ListTables()")
        val cmd = "listtables&dbname=$databasename"
        backendConnection = BackendConnection(uri, cmd)
    }
}