package com.example.jeonjin_il.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class FoodSearch_Hash extends AppCompatActivity {

    Button hash_button;
    EditText hash_text;
    TextView textview;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_hash);

        hash_button = (Button) findViewById(R.id.hash_button);
        hash_text = (EditText) findViewById(R.id.hash_text);
        textview = (TextView) findViewById(R.id.hash_text2);

        dbHelper = new DBHelper(getApplicationContext(),"FOOD.db",null,1);
        hash_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = hash_text.getText().toString();
                ArrayList<Integer> datas = new ArrayList<Integer>();
                datas = dbHelper.food_hash(str);


                textview.setText(datas.toString());
            }
        });

    }
}

