package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    String id;
    Bundle bundle;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    switch(item.getItemId()){
        case R.id.action_random :
            Intent intent_random = new Intent(getApplication(),FoodSearch_Random.class);
            startActivity(intent_random);
            return true;
        case R.id.action_hash :
            Intent intent_hash = new Intent(getApplication(),FoodSearch_Hash.class);
            startActivity(intent_hash);
            return true;
        case R.id.action_fridge:
            Intent intent_ref = new Intent(getApplication(),FoodSearch_Ref.class);
            intent_ref.putExtra("ID",id);
            startActivity(intent_ref);
            return true;
        case R.id.action_cart:
            Intent intent_cart=  new Intent(this,ShoppingBasket.class);
            intent_cart.putExtra("ID",id);
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
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        Toast.makeText(this,id+" 님 어서오세요",Toast.LENGTH_LONG).show();


        BottomNavigation bottomNavigation=(BottomNavigation)findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultItem((byte)2);

        setDefault();
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {

                bundle = new Bundle();
                bundle.putString("ID",id);

                switch (itemId){
                    case R.id.tab_home:
                        transaction=getSupportFragmentManager().beginTransaction();
                        chinaFragment china = new chinaFragment();
                        china.setArguments(bundle);
                        transaction.replace(R.id.frame_fragment_containers,china);

                        break;
                    case R.id.tab_images:
                        transaction=getSupportFragmentManager().beginTransaction();
                        koreaFragment korea = new koreaFragment();
                        korea.setArguments(bundle);
                        transaction.replace(R.id.frame_fragment_containers,korea);
                        break;
                    case R.id.tab_main:
                        transaction=getSupportFragmentManager().beginTransaction();
                        mainFragment main = new mainFragment();
                        main.setArguments(bundle);
                        transaction.replace(R.id.frame_fragment_containers,main);
                        break;
                    case R.id.tab_products:
                        transaction=getSupportFragmentManager().beginTransaction();
                        westernFragment western = new westernFragment();
                        western.setArguments(bundle);
                        transaction.replace(R.id.frame_fragment_containers,western);
                        break;
                    case R.id.tab_more:
                        transaction=getSupportFragmentManager().beginTransaction();
                        SnackFragment snack = new SnackFragment();
                        snack.setArguments(bundle);
                        transaction.replace(R.id.frame_fragment_containers, snack);
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

