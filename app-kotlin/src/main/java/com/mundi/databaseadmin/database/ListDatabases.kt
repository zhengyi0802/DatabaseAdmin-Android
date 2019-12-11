package com.mundi.databaseadmin.database

import android.util.Log
import java.util.ArrayList

class ListDatabases(uri: String?) {
    private val backendConnection: BackendConnection
    val databases: ArrayList<String>
        get() {
            Log.d(TAG, "getDatabases()")
            return backendConnection.arrayListString
        }

    val response: Boolean
        get() = if (backendConnection.resposeCode == 200) {
            true
        } else false

    companion object {
        private const val TAG = "ListDatabases"
    }

    init {
        Log.d(TAG, "ListDatabases()")
        val cmd = "listdatabases"
        backendConnection = BackendConnection(uri, cmd)
    }
}