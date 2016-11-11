package com.example.jeonjin_il.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent_admin = new Intent(MainActivity.this,FoodSearch_Ref.class);
        startActivity(intent_admin);
    }
}
