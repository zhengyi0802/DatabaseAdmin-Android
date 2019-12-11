package com.mundi.databaseadmin.bean;

public class CellData {
    private String datastr;
    private int row;
    private int column;

    public CellData(int row, int column, String datastr) {
        this.row = row;
        this.column = column;
        this.datastr = datastr;
    }

    public CellData(int row, int column, short data) {
        this.row = row;
        this.column = column;
        this.datastr = Short.toString(data);
    }

    public CellData(int row, int column, int data) {
        this.row = row;
        this.column = column;
        this.datastr = Integer.toString(data);
    }

    public CellData(int row, int column, long data) {
        this.row = row;
        this.column = column;
        this.datastr = Long.toString(data);
    }

    public CellData(int row, int column, float data) {
        this.row = row;
        this.column = column;
        this.datastr = Float.toString(data);
    }

    public CellData(int row, int column, double data) {
        this.row = row;
        this.column = column;
        this.datastr = Double.toString(data);
    }

    public CellData(int row, int column, boolean data) {
        this.row = row;
        this.column = column;
        if (data) this.datastr = "true";
        else this.datastr = "false";
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getData() {
        return datastr;
    }

    public short getDataShort() {
        return Short.parseShort(datastr);
    }

    public int getDataInteger() {
        return Integer.parseInt(datastr);
    }

    public long getDataLong() {
        return Long.parseLong(datastr);
    }

    public float getDataFloat() {
        return Float.parseFloat(datastr);
    }

    public double getDataDouble() {
        return Double.parseDouble(datastr);
    }

    public boolean getDataBoolean() {
        if (datastr.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }
}
