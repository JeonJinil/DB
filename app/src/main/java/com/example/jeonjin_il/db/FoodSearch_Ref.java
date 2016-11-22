package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_search :
                return true;
            case R.id.action_fridge:
                Intent intent = new Intent(getApplication(),FoodSearch_Ref.class);
                startActivity(intent);
                return true;
            case R.id.action_login :
                Intent intent_login = new Intent(this,MainLogin.class);
                startActivity(intent_login);
                return true;
            case R.id.action_cart:
                Intent intent_cart=  new Intent(this,ShoppingBasket.class);
                startActivity(intent_cart);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch_ref);
        inti();

        //테스트용 데이터 넣기용
        plus_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.food_insert("김밥","한식");
                dbHelper.recipe_insert(0,0); dbHelper.recipe_insert(0,1); dbHelper.recipe_insert(0,2);
                dbHelper.hash_insert(0,"한식");  dbHelper.hash_insert(0,"비오는날");

                dbHelper.food_insert("돈부리","일식");
                dbHelper.recipe_insert(1,3); dbHelper.recipe_insert(1,1);  dbHelper.recipe_insert(1,4);
                dbHelper.hash_insert(1,"일식");  dbHelper.hash_insert(1,"비오는날");

                dbHelper.food_insert("돈가스","일식");
                dbHelper.recipe_insert(2,3); dbHelper.recipe_insert(2,4);  dbHelper.recipe_insert(2,5);
                dbHelper.hash_insert(2,"일식");  dbHelper.hash_insert(2,"비오는날");

                dbHelper.material_insert("김","??",10,1000,2016);
                dbHelper.material_insert("밥","??",10,1000,2016);
                dbHelper.material_insert("햄","??",10,1000,2016);
                dbHelper.material_insert("돼지고기","??",10,1000,2016);
                dbHelper.material_insert("튀김","??",10,1000,2016);
                dbHelper.material_insert("채소","??",10,1000,2016);

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
                Toast.makeText(getApplicationContext(),data[num] +"으로 만들수 있는 요리 번호는 :"+food_id.toString() ,Toast.LENGTH_LONG).show();
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