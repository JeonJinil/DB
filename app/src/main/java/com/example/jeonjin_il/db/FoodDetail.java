package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jeonjin-il on 2016. 11. 18..
 */

public class FoodDetail extends AppCompatActivity {
    TextView text_foodname , text_foodhow ;
    ScrollView sc;
    Button buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetail);

        Intent intent = getIntent();
        int food_icon = intent.getExtras().getInt("Food_icon");
        final String food_name = intent.getExtras().getString("Food_name");
        String food_how = intent.getExtras().getString("Food_how");

        sc = (ScrollView) findViewById(R.id.fooddetail_layout);
        text_foodname = (TextView) findViewById(R.id.fooddetail_name);
        text_foodhow = (TextView) findViewById(R.id.fooddetail_how);
        buy = (Button) findViewById(R.id.fooddetail_buy);

        sc.setBackgroundResource(food_icon);
        text_foodname.setText(food_name);
        text_foodhow.setText(food_how);
        buy.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
                Toast.makeText(getApplicationContext(),"아직 구현안했지롱 ",Toast.LENGTH_LONG).show();

                dbHelper.fooddetail_Buy("user01",food_name);
            }
        });
        Button timer = (Button)findViewById(R.id.timer_button);
        timer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),TimerView.class);
                startActivity(intent);
            }
        });
    }
}