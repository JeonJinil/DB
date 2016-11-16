package com.example.jeonjin_il.db;

/**
 * Created by jeonjin-il on 2016. 11. 16..
 */

public class ShoppingItem {
    String name=null;
    boolean selected=false;

    public ShoppingItem(String name,boolean selected)
    {
        this.name=name;

        this.selected=selected;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
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
