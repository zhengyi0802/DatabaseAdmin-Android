package com.mundi.databaseadmin.bean

class RowTitle {
    var rowTitle: String
        private set
    var flag = false

    constructor(index: Int) {
        rowTitle = Integer.toString(index)
        flag = true
    }

    constructor(str: String) {
        rowTitle = str
        flag = true
    }

}