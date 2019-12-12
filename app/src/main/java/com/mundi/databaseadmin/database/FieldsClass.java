package com.mundi.databaseadmin.database;

public class FieldsClass {

    private String tablename;
    private String fieldname;
    private String titls;

    public FieldsClass(String tablename, String fieldname, String title) {
        this.tablename = tablename;
        this.fieldname = fieldname;
        this.titls = title;
        return;
    }

    public String getTablename() {
        return tablename;
    }

    public String getFieldname() {
        return fieldname;
    }

    public String getTitle() {
        return titls;
    }

}
