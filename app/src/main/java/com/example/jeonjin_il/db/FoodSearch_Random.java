package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class FoodSearch_Random extends AppCompatActivity {

    DBHelper dbHelper;
    Button random_button ;
    ImageButton imagebutton;
    int random_value;
    int random_icon;
    int random_how;
    int random_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_random);
        imagebutton = (ImageButton)findViewById(R.id.random_foodbutton);
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        random_button = (Button) findViewById(R.id.random_button);

        random_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                random_value = dbHelper.food_random(); //FOOD 테이블의 food_id 를 랜덤으로 가저옴

                random_icon = getResources().getIdentifier("@drawable/food_" + random_value, "drawable", "com.example.jeonjin_il.db");
                random_how = getResources().getIdentifier("@raw/howfood_"+random_value, "raw", "com.example.jeonjin_il.db");
                random_name =getResources().getIdentifier("@raw/namefood_"+random_value, "raw", "com.example.jeonjin_il.db");
                imagebutton.setImageResource(random_icon);

            }
        });

        imagebutton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodDetail.class);
                intent.putExtra("Food_icon", random_icon);
                intent.putExtra("Food_name", readTxt(random_name));
                intent.putExtra("Food_how", readTxt(random_how));
                startActivity(intent);
            }
        });

    }

    private String readTxt(int text){

        InputStream inputStream = getResources().openRawResource(text);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}

