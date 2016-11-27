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
            Intent intent_hash = new Intent(getApplication(),FoodSearch_Hash.class);
            startActivity(intent_hash);
            return true;
        case R.id.action_fridge:
            Intent intent_ref = new Intent(getApplication(),FoodSearch_Ref.class);
            startActivity(intent_ref);
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
        bottomNavigation.setDefaultItem((byte)2);

        setDefault();
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
                    case R.id.tab_main:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new mainFragment());
                        break;
                    case R.id.tab_products:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new westernFragment());
                        break;
                    case R.id.tab_more:
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_fragment_containers,new SnackFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

    public void setDefault(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment_containers,new mainFragment());
        transaction.commit();
    }

    }

