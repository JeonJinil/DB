package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        hash_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = hash_text.getText().toString();
                ArrayList<Integer> datas = new ArrayList<Integer>();
                datas = dbHelper.food_hash(str);
                if(datas.size() ==0)
                    Toast.makeText(getApplicationContext(),str +"로 등록된 요리가 없습니다 " ,Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getApplication(),FoodSearch_Ref_activity.class);
                    intent.putExtra("food_id",datas);
                    startActivity(intent);
                }
            }
        });

    }
}

