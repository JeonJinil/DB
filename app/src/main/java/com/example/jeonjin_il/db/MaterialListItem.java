package com.example.jeonjin_il.db;

/**
 * Created by jeonjin-il on 2016. 11. 17..
 */

public class MaterialListItem {
    private boolean checked = false;
    private String name;
    private int remain;
    private String date;

    public void toggle() { checked = !checked; }

    public boolean isChecked() { return checked; }

    public int getRemain() { return remain; }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
