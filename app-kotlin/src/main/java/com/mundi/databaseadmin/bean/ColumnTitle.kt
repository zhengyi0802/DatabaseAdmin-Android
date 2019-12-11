package com.mundi.databaseadmin.bean

class ColumnTitle(val index: Int, var title: String, var field: String) {
    var typeString: String? = null
    var nullString: String? = null
    var keyString: String? = null
    var defaultString: String? = null
    var extraString: String? = null
    var showFlag = true
    var enabledFlag = true

}