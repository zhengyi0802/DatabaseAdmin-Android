package com.mundi.databaseadmin.bean;

public class RowTitle {

    private String str;
    private boolean flag = false;

    public RowTitle(int index) {
        this.str = Integer.toString(index);
        this.flag = true;
    }

    public RowTitle(String str) {
        this.str = str;
        this.flag = true;
    }

    public String getRowTitle() {
        return str;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

}
