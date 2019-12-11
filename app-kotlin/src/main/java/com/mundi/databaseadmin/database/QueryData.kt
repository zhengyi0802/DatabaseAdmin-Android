package com.mundi.databaseadmin.database

import android.util.Log
import org.json.JSONArray

class QueryData(uri: String?, databasename: String, tablename: String) {
    private val backendConnection: BackendConnection
    val data: JSONArray
        get() {
            Log.d(TAG, "getData()")
            return backendConnection.jsonArray
        }

    companion object {
        private const val TAG = "QueryData"
    }

    init {
        Log.d(TAG, "QueryData()")
        val cmd = "querydata&dbname=$databasename&tablename=$tablename"
        backendConnection = BackendConnection(uri, cmd)
    }
}