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
    Button plus_button ;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_ref);
        inti();

        //테스트용 데이터 넣기용
        plus_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //재료
                dbHelper.material_insert("감자","야채",5,2000,"2016-12-05");      //0
                dbHelper.material_insert("베이컨","고기",5,5500,"2016-12-05");
                dbHelper.material_insert("체다지츠","치즈",5,4000,"2016-12-05");
                dbHelper.material_insert("모짜렐라 치즈","치즈",5,4000,"2016-12-05");
                dbHelper.material_insert("식용류","기름",5,2000,"2016-12-05");
                dbHelper.material_insert("라면사리","랴면",5,1000,"2016-12-05");    //5
                dbHelper.material_insert("가래떡","떡",5,3000,"2016-12-05");
                dbHelper.material_insert("양파","야채",5,2000,"2016-12-05");
                dbHelper.material_insert("고추장","장",5,6000,"2016-12-05");
                dbHelper.material_insert("케첩","장",5,4000,"2016-12-05");
                dbHelper.material_insert("방울토마토","야채",5,2000,"2016-12-05");     //10
                dbHelper.material_insert("햄","고기",5,10000,"2016-12-05");
                dbHelper.material_insert("생크림","크림",5,2000,"2016-12-05");
                dbHelper.material_insert("브로콜리","야채",5,2000,"2016-12-05");
                dbHelper.material_insert("달걀","고기",5,500,"2016-12-05");
                dbHelper.material_insert("김치","야채",5,2000,"2016-12-05");        //15
                dbHelper.material_insert("당근","야채",5,2000,"2016-12-05");
                dbHelper.material_insert("가지","야채",5,2000,"2016-12-05");
                dbHelper.material_insert("소금","조미료",5,1000,"2016-12-05");
                dbHelper.material_insert("참치","고기",5,7000,"2016-12-05");
                dbHelper.material_insert("올리고당","기름",5,5000,"2016-12-05");     //20
                dbHelper.material_insert("돼지고기","고기",5,10000,"2016-12-05");
                dbHelper.material_insert("간장","조미료",5,2000,"2016-12-05");
                dbHelper.material_insert("메추리알","고기",5,2000,"2016-12-05");
                dbHelper.material_insert("마요네즈","조미료",5,2000,"2016-12-05");
                dbHelper.material_insert("고추","야채",5,2000,"2016-12-05");       //25

//음식
                dbHelper.food_insert("라볶이","분식");
                dbHelper.food_insert("치즈 베이컨","분식");
                dbHelper.food_insert("베이컨크림파스타","면식");
                dbHelper.food_insert("봉골레파스타","면식");
                dbHelper.food_insert("오믈렛","한식");
                dbHelper.food_insert("김치볶음밥","한식");
                dbHelper.food_insert("제육볶음","양식");
                dbHelper.food_insert("베이컨감자찜","양식");

//레시피당 들가는 재료
                dbHelper.recipe_insert(0,0);
                dbHelper.recipe_insert(0,1);
                dbHelper.recipe_insert(0,2);
                dbHelper.recipe_insert(0,3);
                dbHelper.recipe_insert(0,4);

                dbHelper.recipe_insert(1,5);
                dbHelper.recipe_insert(1,6);
                dbHelper.recipe_insert(1,7);
                dbHelper.recipe_insert(1,3);
                dbHelper.recipe_insert(1,8);
                dbHelper.recipe_insert(1,9);

                dbHelper.recipe_insert(2,7);
                dbHelper.recipe_insert(2,10);
                dbHelper.recipe_insert(2,11);
                dbHelper.recipe_insert(2,12);
                dbHelper.recipe_insert(2,18);

                dbHelper.recipe_insert(3,1);
                dbHelper.recipe_insert(3,13);
                dbHelper.recipe_insert(3,12);
                dbHelper.recipe_insert(3,2);

                dbHelper.recipe_insert(4,1);
                dbHelper.recipe_insert(4,14);
                dbHelper.recipe_insert(4,15);
                dbHelper.recipe_insert(4,7);
                dbHelper.recipe_insert(4,16);
                dbHelper.recipe_insert(4,17);
                dbHelper.recipe_insert(4,18);

                dbHelper.recipe_insert(5,15);
                dbHelper.recipe_insert(5,14);
                dbHelper.recipe_insert(5,19);
                dbHelper.recipe_insert(5,18);

                dbHelper.recipe_insert(6,21);
                dbHelper.recipe_insert(6,7);
                dbHelper.recipe_insert(6,25);
                dbHelper.recipe_insert(6,22);
                dbHelper.recipe_insert(6,18);

                dbHelper.recipe_insert(7,0);
                dbHelper.recipe_insert(7,1);
                dbHelper.recipe_insert(7,23);
                dbHelper.recipe_insert(7,24);
                dbHelper.recipe_insert(7,18);

//해시
                dbHelper.hash_insert(0,"분식");
                dbHelper.hash_insert(1,"분식");
                dbHelper.hash_insert(2,"면식");
                dbHelper.hash_insert(3,"면식");
                dbHelper.hash_insert(4,"한식");
                dbHelper.hash_insert(5,"한식");
                dbHelper.hash_insert(6,"양식");
                dbHelper.hash_insert(7,"양식");
                dbHelper.hash_insert(0,"매콤");
                dbHelper.hash_insert(1,"느끼");
                dbHelper.hash_insert(2,"느끼");
                dbHelper.hash_insert(3,"느끼");
                dbHelper.hash_insert(4,"담백");
                dbHelper.hash_insert(5,"매콤");
                dbHelper.hash_insert(6,"매콤");
                dbHelper.hash_insert(7,"담백");

                Toast.makeText(getApplicationContext(),"테스트용 자료 넣었음 ",Toast.LENGTH_LONG).show();
            }
        });

        Button random = (Button)findViewById(R.id.ref_button_random);
        random.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodSearch_Random.class);
                startActivity(intent);
            }
        });

        Button hash = (Button)findViewById(R.id.ref_button_hash);
        hash.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),FoodSearch_Hash.class);
                startActivity(intent);
            }
        });

        Button timer = (Button)findViewById(R.id.ref_button_time);
        timer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),TimerView.class);
                startActivity(intent);
            }
        });

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
        plus_button = (Button) findViewById(R.id.ref_button_plus);
        for(int i=0;i<9;i++){
            String resName = "@drawable/ref_" + (i+1) ;
            String packName = this.getPackageName();

            int resID = getResources().getIdentifier(resName, "drawable", packName);
            mbutton[i].setBackgroundResource(resID);
            mbutton[i].setOnClickListener(this);
        }
    }
}