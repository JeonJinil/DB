package com.example.jeonjin_il.db;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    LinearLayout tagCloudPlane;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_hash);

        hash_button = (Button) findViewById(R.id.hash_button);
        hash_text = (EditText) findViewById(R.id.hash_text);
        textview = (TextView) findViewById(R.id.hash_text2);
        tagCloudPlane = (LinearLayout) findViewById(R.id.frame_tagCloud);
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

        tagCloudPlane.measure(0,0);

        makeTagCloud();

        hash_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashSearch();
            }
        });
        hash_text.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    hashSearch();
                    return true;
                }
                return false;
            }
        });

    }

    private void hashSearch(){
        String str = hash_text.getText().toString();
        if(!str.equals("")){
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
    }

    private void makeTagCloud(){
        ArrayList<TextView> tagCloud = dbHelper.getTagCloud(this);
        LinearLayout now = getNewPlane(this);
        int count = 0;

        for(TextView tv : tagCloud){
            if(count > 4){
                tagCloudPlane.addView(now);
                now = getNewPlane(tagCloudPlane.getContext());
                count = 0;
            }
            now.addView(tv);
            count++;
        }
        tagCloudPlane.addView(now);
    }

    private LinearLayout getNewPlane(Context c){
        LinearLayout ret = new LinearLayout(c);
        ret.setPadding(10,10,10,10);
        return ret;
    }
}

