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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_search :
                return true;
            case R.id.action_fridge:
                Intent intent = new Intent(getApplication(),FoodSearch_Ref.class);
                startActivity(intent);
                return true;
            case R.id.action_login :
                Intent intent_login = new Intent(this,MainLogin.class);
                startActivity(intent_login);
                return true;
            case R.id.action_cart:
                Intent intent_cart=  new Intent(this,ShoppingBasket.class);
                startActivity(intent_cart);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_random);

        dbHelper = new DBHelper(getApplicationContext(),"FOOD.db",null,1);
        random_button = (Button) findViewById(R.id.random_button);
        random_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                int random_value = dbHelper.food_random();
                random_textview = (TextView)findViewById(R.id.random_text);
                random_textview.setText(random_value + " 번 요리");
            }
        });

    }
}

