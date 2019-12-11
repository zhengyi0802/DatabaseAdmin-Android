package com.mundi.databaseadmin.database

import android.util.Log

class CreateDatabase(uri: String?, databasename: String) {
    private val backendConnection: BackendConnection
    val response: Boolean
        get() {
            Log.d(TAG, "getResponse()")
            return if (backendConnection.resposeCode == 200) {
                true
            } else false
        }

    val responseString: String
        get() {
            Log.d(TAG, "getResponseString()")
            return backendConnection.responseString
        }

    companion object {
        private const val TAG = "CreateDatabase"
    }

    init {
        Log.d(TAG, "ListTables()")
        val cmd = "createdatabase&dbname=$databasename"
        backendConnection = BackendConnection(uri, cmd)
        return
    }
}