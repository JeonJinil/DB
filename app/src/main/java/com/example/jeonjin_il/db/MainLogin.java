package com.example.jeonjin_il.db;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainLogin extends AppCompatActivity {

    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inti();
        ButterKnife.inject(this);


    }
    private void inti() {
        DBHelper db = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

        if(db.isinti() == true)
            return ;

        Log.d("TAG","fuck");
        //재료
        db.material_insert("감자","야채",5,2000,"2016-12-05");      //0
        db.material_insert("베이컨","고기",5,5500,"2016-12-05");
        db.material_insert("체다지츠","치즈",5,4000,"2016-12-05");
        db.material_insert("모짜렐라 치즈","치즈",5,4000,"2016-12-05");
        db.material_insert("식용류","기름",5,2000,"2016-12-05");
        db.material_insert("라면사리","랴면",5,1000,"2016-12-05");    //5
        db.material_insert("가래떡","떡",5,3000,"2016-12-05");
        db.material_insert("양파","야채",5,2000,"2016-12-05");
        db.material_insert("고추장","장",5,6000,"2016-12-05");
        db.material_insert("케첩","장",5,4000,"2016-12-05");
        db.material_insert("방울토마토","야채",5,2000,"2016-12-05");     //10
        db.material_insert("햄","고기",5,10000,"2016-12-05");
        db.material_insert("생크림","크림",5,2000,"2016-12-05");
        db.material_insert("브로콜리","야채",5,2000,"2016-12-05");
        db.material_insert("달걀","고기",5,500,"2016-12-05");
        db.material_insert("김치","야채",5,2000,"2016-12-05");        //15
        db.material_insert("당근","야채",5,2000,"2016-12-05");
        db.material_insert("가지","야채",5,2000,"2016-12-05");
        db.material_insert("소금","조미료",5,1000,"2016-12-05");
        db.material_insert("참치","고기",5,7000,"2016-12-05");
        db.material_insert("올리고당","기름",5,5000,"2016-12-05");     //20
        db.material_insert("돼지고기","고기",5,10000,"2016-12-05");
        db.material_insert("간장","조미료",5,2000,"2016-12-05");
        db.material_insert("메추리알","고기",5,2000,"2016-12-05");
        db.material_insert("마요네즈","조미료",5,2000,"2016-12-05");
        db.material_insert("고추","야채",5,2000,"2016-12-05");       //25

//음식
        db.food_insert("라볶이","분식");
        db.food_insert("치즈 베이컨","분식");
        db.food_insert("베이컨크림파스타","면식");
        db.food_insert("봉골레파스타","면식");
        db.food_insert("오믈렛","한식");
        db.food_insert("김치볶음밥","한식");
        db.food_insert("제육볶음","양식");
        db.food_insert("베이컨감자찜","양식");

//레시피당 들가는 재료
        db.recipe_insert(0,0);
        db.recipe_insert(0,1);
        db.recipe_insert(0,2);
        db.recipe_insert(0,3);
        db.recipe_insert(0,4);

        db.recipe_insert(1,5);
        db.recipe_insert(1,6);
        db.recipe_insert(1,7);
        db.recipe_insert(1,3);
        db.recipe_insert(1,8);
        db.recipe_insert(1,9);

        db.recipe_insert(2,7);
        db.recipe_insert(2,10);
        db.recipe_insert(2,11);
        db.recipe_insert(2,12);
        db.recipe_insert(2,18);

        db.recipe_insert(3,1);
        db.recipe_insert(3,13);
        db.recipe_insert(3,12);
        db.recipe_insert(3,2);

        db.recipe_insert(4,1);
        db.recipe_insert(4,14);
        db.recipe_insert(4,15);
        db.recipe_insert(4,7);
        db.recipe_insert(4,16);
        db.recipe_insert(4,17);
        db.recipe_insert(4,18);

        db.recipe_insert(5,15);
        db.recipe_insert(5,14);
        db.recipe_insert(5,19);
        db.recipe_insert(5,18);

        db.recipe_insert(6,21);
        db.recipe_insert(6,7);
        db.recipe_insert(6,25);
        db.recipe_insert(6,22);
        db.recipe_insert(6,18);

        db.recipe_insert(7,0);
        db.recipe_insert(7,1);
        db.recipe_insert(7,23);
        db.recipe_insert(7,24);
        db.recipe_insert(7,18);

//해시
        db.hash_insert(0,"분식");
        db.hash_insert(1,"분식");
        db.hash_insert(2,"면식");
        db.hash_insert(3,"면식");
        db.hash_insert(4,"한식");
        db.hash_insert(5,"한식");
        db.hash_insert(6,"양식");
        db.hash_insert(7,"양식");
        db.hash_insert(0,"매콤");
        db.hash_insert(1,"느끼");
        db.hash_insert(2,"느끼");
        db.hash_insert(3,"느끼");
        db.hash_insert(4,"담백");
        db.hash_insert(5,"매콤");
        db.hash_insert(6,"매콤");
        db.hash_insert(7,"담백");
    }


    void click_plus(View view){
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
            startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    void click_Go(View view){
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        String id = etUsername.getText().toString();
        String pw = etPassword.getText().toString();


        int result = dbHelper.Login(id,pw);
        if(result == -1){
            Toast.makeText(getApplicationContext(),"응 아니야~",Toast.LENGTH_LONG).show();
        }else if(Objects.equals(id, "admin")) {
            Toast.makeText(getApplicationContext(), "응 관리자~",Toast.LENGTH_LONG).show();
            Intent adminIntent = new Intent(this,MainAdmin.class);
            startActivity(adminIntent);

        }
        else{
            Toast.makeText(getApplicationContext(),"응 맞아~",Toast.LENGTH_LONG).show();
            Intent guestIntent = new Intent(this, MainActivity.class);
            startActivity(guestIntent);

        }



    }
}

