package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jeonjin-il on 2016. 11. 18..
 */

public class FoodDetail extends AppCompatActivity {
    TextView textview ;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetail);

        Intent intent = getIntent();
        int food_icon = intent.getExtras().getInt("Food_icon");
        String food_name = intent.getExtras().getString("Food_name");

        textview = (TextView) findViewById(R.id.fooddetail_text);
        imageView = (ImageView) findViewById(R.id.fooddetail_image);


        imageView.setImageResource(food_icon);
        textview.setText(food_name);


    }
}