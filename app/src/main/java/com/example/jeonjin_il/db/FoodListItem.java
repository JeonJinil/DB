package com.example.jeonjin_il.db;

/**
 * Created by jeonjin-il on 2016. 11. 17..
 */

public class FoodListItem {
    private int iconDrawable;
    private String food_name;

    FoodListItem(int iconDrawable,String food_name){
        this.iconDrawable = iconDrawable;
        this.food_name = food_name;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public String getFood_name() {
        return food_name;
    }


}
