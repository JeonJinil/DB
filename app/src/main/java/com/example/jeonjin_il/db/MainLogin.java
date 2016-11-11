package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    void click_Go(View view){
        Intent intent = new Intent(this, FoodSearch_Ref.class);
        startActivity(intent);

    }
}

