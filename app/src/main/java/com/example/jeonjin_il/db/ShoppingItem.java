package com.example.jeonjin_il.db;

/**
 * Created by jeonjin-il on 2016. 11. 16..
 */

public class ShoppingItem {
    String name=null;
    int num = 0;
    boolean selected=false;

    public ShoppingItem(String name,int num,boolean selected)
    {
        this.name=name;
        this.num = num;
        this.selected=selected;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSelected()
    {
        return selected;
    }
    public void setSelected(boolean selected)
    {
        this.selected=selected;
    }
}
