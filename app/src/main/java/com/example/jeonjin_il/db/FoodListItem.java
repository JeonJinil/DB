package com.example.jeonjin_il.db;

/**
 * Created by jeonjin-il on 2016. 11. 17..
 */

public class FoodListItem {
    private int iconDrawable;
    private String food_name;
    private String how;


    FoodListItem(int iconDrawable, String food_name, String how){
        this.iconDrawable = iconDrawable;
        this.food_name = food_name;
        this.how = how;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
}
