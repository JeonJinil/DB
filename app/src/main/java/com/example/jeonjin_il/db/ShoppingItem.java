package com.example.jeonjin_il.db;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 16..
 */

public class ShoppingItem {
    String name=null;
    int num = 0;
    boolean selected=false;
    ArrayList<String> itemNum=null;
    int buynum;

    public ShoppingItem(String name,int num,boolean selected)
    {
        this.name=name;
        this.num = num;
        this.selected=selected;
        this.itemNum=new ArrayList<String>();
        for(int i=0; i<num; i++)
        {
            itemNum.add(i,i+1+"개");
        }
        this.buynum=0;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public ArrayList<String> getItemNum(){return itemNum;}

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
    public void setBuynum(int num){this.buynum=num;}
    public int getBuynum(){return this.buynum;}
}
