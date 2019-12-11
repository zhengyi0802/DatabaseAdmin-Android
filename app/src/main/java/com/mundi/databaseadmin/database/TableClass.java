package com.mundi.databaseadmin.database;

public class TableClass {
    private String field_val;
    private String type_val;
    private String null_val;
    private String key_val;
    private String default_val;
    private String extra_val;

    public TableClass(String field, String type, String null_str,
                      String key, String default_str, String extra_str) {
        this.field_val   = field;
        this.type_val    = type;
        this.null_val    = null_str;
        this.key_val     = key;
        this.default_val = default_str;
        this.extra_val   = extra_str;
    }

    public String getField() {
        return field_val;
    }

    public String getType() {
        return type_val;
    }

    public String getNull(){
        return null_val;
    }

    public String getKey() {
        return key_val;
    }

    public String getDefault() {
        return default_val;
    }

    public String getExtra() {
        return extra_val;
    }

    public void setField(String field) {
        this.field_val = field;
    }

    public void setType(String type) {
        this.type_val = type;
    }

    public void setNull(String nullstr) {
        this.null_val = nullstr;
    }

    public void setKey(String key) {
        this.key_val = key;
    }

    public void setDefault(String defaultstr) {
        this.default_val = defaultstr;
    }

    public void setExtra(String extrastr) {
        this.extra_val = extrastr;
    }

}
