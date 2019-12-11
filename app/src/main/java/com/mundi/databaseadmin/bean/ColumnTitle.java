package com.mundi.databaseadmin.bean;

public class ColumnTitle {

    private int index;
    private String title;
    private String field;
    private String typestr;
    private String nullstr;
    private String keystr;
    private String defaultstr;
    private String extrastr;
    private boolean show_flag;
    private boolean enabled_flag;

    public ColumnTitle(int index, String title, String field) {
        this.index = index;
        this.title = title;
        this.field = field;
        this.show_flag = true;
        this.enabled_flag = true;
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getField() {
        return field;
    }

    public boolean getShowFlag() {
        return show_flag;
    }

    public boolean getEnabledFlag() {
        return enabled_flag;
    }

    public String getTypeString() {
        return typestr;
    }

    public String getNullString() {
        return nullstr;
    }

    public String getKeyString() {
        return keystr;
    }

    public String getDefaultString() {
        return defaultstr;
    }

    public String getExtraString() {
        return extrastr;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setShowFlag(boolean flag) {
        this.show_flag = flag;
    }

    public void setEnabledFlag(boolean flag) {
        this.enabled_flag = flag;
    }

    public void setTypeString(String typestr) {
        this.typestr = typestr;
    }

    public void setNullString(String nullstr) {
        this.nullstr = nullstr;
    }

    public void setKeyString(String keystr) {
        this.keystr = keystr;
    }

    public void setDefaultString(String defaultstr) {
        this.defaultstr = defaultstr;
    }

    public void setExtraString(String extrastr) {
        this.extrastr = extrastr;
    }

}
