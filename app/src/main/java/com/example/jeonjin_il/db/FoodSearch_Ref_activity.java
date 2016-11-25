package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 26..
 */

public class FoodSearch_Ref_activity extends AppCompatActivity {
    ListView listview ;
    ArrayList<FoodListItem> datas = new ArrayList<FoodListItem>();
    int random_icon,random_name,random_how;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_ref_activity);

        Intent intent = getIntent();
        ArrayList<Integer> food_id = intent.getIntegerArrayListExtra("food_id");

        for(int i=0;i<food_id.size();i++){
            random_icon = getResources().getIdentifier("@drawable/food_" + food_id.get(i), "drawable", "com.example.jeonjin_il.db");
            random_how = getResources().getIdentifier("@raw/howfood_"+food_id.get(i), "raw", "com.example.jeonjin_il.db");
            random_name =getResources().getIdentifier("@raw/namefood_"+food_id.get(i), "raw", "com.example.jeonjin_il.db");

            datas.add(new FoodListItem(random_icon,readTxt(random_name),readTxt(random_how)));
        }

        listview = (ListView)findViewById(R.id.ref_listv);
        Custom_Adapter list_adapter = new Custom_Adapter(getLayoutInflater(),datas, getApplicationContext());
        listview.setAdapter(list_adapter);

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