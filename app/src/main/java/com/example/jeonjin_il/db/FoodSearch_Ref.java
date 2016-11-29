package com.example.jeonjin_il.db;

import android.content.Intent;
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

    private Button[] mbutton = new Button[9];
    private String[] data = new String[9];
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_ref);
        inti();
    }


    @Override
    public void onClick(View v) {
        Button newbutton = (Button) v;
        int num = 0;
        for(Button tempbutton : mbutton){
            if(tempbutton == newbutton){
                ArrayList<Integer> food_id = dbHelper.food_search_by_ref(data[num]); //food_id 를 가저온다.
                if(food_id.size() ==0)
                    Toast.makeText(getApplicationContext(),data[num] +"으로 만들수 있는 요리가 없습니다 " ,Toast.LENGTH_LONG).show();
                else {
                    Intent intent = new Intent(getApplication(),FoodSearch_Ref_activity.class);
                    intent.putExtra("food_id",food_id);
                    startActivity(intent);
                }
            }
            num++;
        }
    }



    private void inti() {

        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        mbutton[0] = (Button) findViewById(R.id.ref_button11);
        mbutton[1] = (Button) findViewById(R.id.ref_button12);
        mbutton[2] = (Button) findViewById(R.id.ref_button13);
        mbutton[3] = (Button) findViewById(R.id.ref_button21);
        mbutton[4] = (Button) findViewById(R.id.ref_button22);
        mbutton[5] = (Button) findViewById(R.id.ref_button23);
        mbutton[6] = (Button) findViewById(R.id.ref_button31);
        mbutton[7] = (Button) findViewById(R.id.ref_button32);
        mbutton[8] = (Button) findViewById(R.id.ref_button33);


        data[0] = "돼지고기";data[1]="소고기"; data[2] = "닭고기";
        data[3] = "생선/갑각류"; data[4] = "유제품";data[5] = "달걀";
        data[6] = "채소"; data[7] = "과일"; data[8] = "가공식품";

        for(int i=0;i<9;i++){
            String resName = "@drawable/ref_" + (i+1) ;
            String packName = this.getPackageName();

            int resID = getResources().getIdentifier(resName, "drawable", packName);
            mbutton[i].setBackgroundResource(resID);
            mbutton[i].setOnClickListener(this);
        }
    }
}