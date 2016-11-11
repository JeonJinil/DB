package com.example.jeonjin_il.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class FoodSearch_Ref extends AppCompatActivity implements View.OnClickListener {

    private Button[] mbutton = new Button[8];
    private String[] data = new String[8];
    Button plus_button ;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_ref);
        inti();

        //테스트용 데이터 넣기용
        plus_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.food_insert("김밥",1,"@김@밥@햄@단무지","#한식#간단");
                dbHelper.food_insert("소고기국밥",1,"@소고기@밥","#한식");
                dbHelper.food_insert("돈가스",2,"@돼지고기@단무지","#양식#인기");
                dbHelper.food_insert("돈부리",2,"@돼지고기@밥","#양식");
                dbHelper.test();
            }
        });


    }


    @Override
    public void onClick(View v) {
        Button newbutton = (Button) v;
        int num = 0;
        for(Button tempbutton : mbutton){
            if(tempbutton == newbutton){
                ArrayList<String> food_name = dbHelper.food_search_by_ref(data[num]);
                Toast.makeText(getApplicationContext(),data[num] +"으로 만들수 있는 요리는 :"+food_name ,Toast.LENGTH_LONG).show();
            }
            num++;
        }
    }


    private void inti() {

        dbHelper = new DBHelper(getApplicationContext(),"FOOD.db",null,1);
        mbutton[0] = (Button) findViewById(R.id.ref_button11);
        mbutton[1] = (Button) findViewById(R.id.ref_button12);
        mbutton[2] = (Button) findViewById(R.id.ref_button13);
        mbutton[3] = (Button) findViewById(R.id.ref_button14);
        mbutton[4] = (Button) findViewById(R.id.ref_button21);
        mbutton[5] = (Button) findViewById(R.id.ref_button22);
        mbutton[6] = (Button) findViewById(R.id.ref_button23);
        mbutton[7] = (Button) findViewById(R.id.ref_button24);
        data[0] = "김";data[1]="돼지고기";data[2] = "건어물";data[3] = "단무지";
        data[4] = "우유";data[5] = "맥주";data[6] = "밥";data[7] = "햄";
        plus_button = (Button) findViewById(R.id.ref_button_plus);
        for(int i=0;i<8;i++){
            mbutton[i].setOnClickListener(this);
        }
    }
}

