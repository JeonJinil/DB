package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {

    Button go_ref , go_ran , go_hash , go_basket ,go_list;
    private FragmentTransaction transaction;

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
        setContentView(R.layout.activity_main);

        BottomNavigation bottomNavigation=(BottomNavigation)findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {
                switch (itemId){
                    case R.id.tab_home:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new chinaFragment());
                        break;
                    case R.id.tab_images:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new koreaFragment());
                        break;
                    case R.id.tab_camera:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new mainFragment());
                        break;
                    case R.id.tab_products:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new westernFragment());
                        break;
                    case R.id.tab_more:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new japanFragment());
                        break;
                }
                transaction.commit();
            }
        });
//
//        go_ref = (Button) findViewById(R.id.main_button_ref);
//        go_ran = (Button)findViewById(R.id.main_button_random);
//        go_hash = (Button) findViewById(R.id.main_button_hash);
//        go_basket = (Button) findViewById(R.id.main_button_basket);
//        go_list = (Button) findViewById(R.id.main_button_list);
//
//        go_ref.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(),FoodSearch_Ref.class);
//                startActivity(intent);
//            }
//        });
//
//        go_ran.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(),FoodSearch_Random.class);
//                startActivity(intent);
//            }
//        });
//        go_hash.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(),FoodSearch_Hash.class);
//                startActivity(intent);
//            }
//        });
//        go_basket.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(),ShoppingBasket.class);
//                startActivity(intent);
//            }
//        });
//        go_list.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent  = new Intent(getApplication(),FoodListView.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//        Button temp  = (Button) findViewById(R.id.main_button_insert);
//        temp.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DBHelper dbHelper;
//                dbHelper = new DBHelper(getApplicationContext(),"FOOD.db",null,1);
//                dbHelper.basket_insert("user01","@김@밥@김@햄@단무지");
//            }
//        });




    }
}
