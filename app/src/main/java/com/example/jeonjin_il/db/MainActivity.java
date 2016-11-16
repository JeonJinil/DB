package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button go_ref , go_ran , go_hash , go_basket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_ref = (Button) findViewById(R.id.main_button_ref);
        go_ran = (Button)findViewById(R.id.main_button_random);
        go_hash = (Button) findViewById(R.id.main_button_hash);
        go_basket = (Button) findViewById(R.id.main_button_basket);

        go_ref.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodSearch_Ref.class);
                startActivity(intent);
            }
        });

        go_ran.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodSearch_Random.class);
                startActivity(intent);
            }
        });
        go_hash.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodSearch_Hash.class);
                startActivity(intent);
            }
        });
        go_basket.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ShoppingBasket.class);
                startActivity(intent);
            }
        });

        Button temp  = (Button) findViewById(R.id.main_button_insert);
        temp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper;
                dbHelper = new DBHelper(getApplicationContext(),"BASKET.db",null,1);
                dbHelper.basket_insert("user01","@김@밥@김@햄@단무지");
            }
        });

//        Intent intent_admin = new Intent(this,MainLogin.class);
//        startActivity(intent_admin);
    }
}
