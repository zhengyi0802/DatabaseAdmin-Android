package com.mundi.databaseadmin.bean

class CellData {
    var data: String? = null
        private set
    var row: Int
        private set
    var column: Int
        private set

    constructor(row: Int, column: Int, datastr: String?) {
        this.row = row
        this.column = column
        data = datastr
    }

    constructor(row: Int, column: Int, data: Short) {
        this.row = row
        this.column = column
        this.data = java.lang.Short.toString(data)
    }

    constructor(row: Int, column: Int, data: Int) {
        this.row = row
        this.column = column
        this.data = Integer.toString(data)
    }

    constructor(row: Int, column: Int, data: Long) {
        this.row = row
        this.column = column
        this.data = java.lang.Long.toString(data)
    }

    constructor(row: Int, column: Int, data: Float) {
        this.row = row
        this.column = column
        this.data = java.lang.Float.toString(data)
    }

    constructor(row: Int, column: Int, data: Double) {
        this.row = row
        this.column = column
        this.data = java.lang.Double.toString(data)
    }

    constructor(row: Int, column: Int, data: Boolean) {
        this.row = row
        this.column = column
        if (data) this.data = "true" else this.data = "false"
    }

    val dataShort: Short
        get() = data!!.toShort()

    val dataInteger: Int
        get() = data!!.toInt()

    val dataLong: Long
        get() = data!!.toLong()

    val dataFloat: Float
        get() = data!!.toFloat()

    val dataDouble: Double
        get() = data!!.toDouble()

    val dataBoolean: Boolean
        get() = if (data.equals("true", ignoreCase = true)) {
            true
        } else {
            false
        }
}