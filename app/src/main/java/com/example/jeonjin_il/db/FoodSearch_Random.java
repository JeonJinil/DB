package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class FoodSearch_Random extends AppCompatActivity {

    DBHelper dbHelper;
    Button random_button ;
    TextView random_textview ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_random);

        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        random_button = (Button) findViewById(R.id.random_button);
        random_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                int random_value = dbHelper.food_random(); //FOOD 테이블의 food_id 를 랜덤으로 가저옴
                random_textview = (TextView)findViewById(R.id.random_text);
                random_textview.setText(random_value + " 번 요리");
            }
        });

    }
}

