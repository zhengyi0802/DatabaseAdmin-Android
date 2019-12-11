package com.mundi.databaseadmin.database

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class BackendConnection {
    private var mUrl: URL? = null
    private var mUrlString: String
    private var mConnect: HttpURLConnection? = null
    var responseString: String? = null
        private set
    private var ResponseCode = 0

    constructor(url: String, cmdstr: String) {
        Log.d(TAG, "BackendConnection()")
        Log.d(TAG, "uri = $url")
        Log.d(TAG, "cmdstr = $cmdstr")
        mUrlString = "$url?action=$cmdstr"
        sendRequest()
    }

    constructor(url: String, cmdstr: String, array: JSONArray?) {
        Log.d(TAG, "BackendConnection()")
        mUrlString = "$url?action=$cmdstr"
        sendRequest()
    }

    constructor(url: String, cmdstr: String, `object`: JSONObject?) {
        Log.d(TAG, "BackendConnection()")
        mUrlString = "$url?action=$cmdstr"
        sendRequest()
    }

    private fun sendRequest() {
        Log.d(TAG, "sendRequest() uri = $mUrlString")
        try {
            mUrl = URL(mUrlString)
            mConnect = mUrl!!.openConnection() as HttpURLConnection
            ResponseCode = mConnect!!.responseCode
            if (ResponseCode == 200) {
                val `in`: InputStream = BufferedInputStream(mConnect!!.inputStream)
                getResponse(`in`)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mConnect!!.disconnect()
        }
        return
    }

    @Throws(IOException::class)
    private fun getResponse(`is`: InputStream) {
        Log.d(TAG, "getResponse()")
        val sb = StringBuilder()
        val r = BufferedReader(InputStreamReader(`is`), 1000)
        var line = r.readLine()
        while (line != null) {
            sb.append(line)
            line = r.readLine()
        }
        `is`.close()
        responseString = sb.toString()
        return
    }

    val jSONArray: JSONArray?
        get() {
            Log.d(TAG, "getJSONArray()")
            try {
                return JSONArray(responseString)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    val tableClass: ArrayList<TableClass>?
        get() {
            Log.d(TAG, "getTableClass()")
            try {
                val array = JSONArray(responseString)
                val mList: ArrayList<*> = ArrayList<TableClass>()
                for (i in 0 until array.length()) {
                    val jsonobj = array.getJSONObject(i)
                    val field = jsonobj.getString("Field")
                    val type = jsonobj.getString("Type")
                    val nullstr = jsonobj.getString("Null")
                    val key = jsonobj.getString("Key")
                    val defaultstr = jsonobj.getString("Default")
                    val extrastr = jsonobj.getString("Extra")
                    val mtable = TableClass(field, type, nullstr, key, defaultstr, extrastr)
                    mList.add(mtable)
                }
                return mList
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    val arrayListString: ArrayList<String>?
        get() {
            Log.d(TAG, "getArrayListString()")
            try {
                val array = JSONArray(responseString)
                val mList: ArrayList<*> = ArrayList<String>()
                for (i in 0 until array.length()) {
                    mList.add(array.getString(i))
                    Log.d(TAG, "list name = " + array.getString(i))
                }
                return mList
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    val jSONObject: JSONObject?
        get() {
            Log.d(TAG, "getJSONObject()")
            try {
                return JSONObject(mUrlString)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    val resposeCode: Int
        get() {
            Log.d(TAG, "getResposeCode()")
            return ResponseCode
        }

    companion object {
        private const val TAG = "BackendConnection"
    }
}