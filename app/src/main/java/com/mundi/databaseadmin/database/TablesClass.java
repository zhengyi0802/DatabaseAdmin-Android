package com.mundi.databaseadmin.database;

public class TablesClass {

    private String tablename;
    private String title;

    public TablesClass(String table, String title) {
        this.tablename = table;
        this.title = title;
    }

    public String getTablename() {
        return tablename;
    }

    public String getTitle() {
        return title;
    }
}
