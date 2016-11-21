package com.example.jeonjin_il.db;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
        ButterKnife.inject(this);

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
        }
        else{
            Toast.makeText(getApplicationContext(),"응 맞아~",Toast.LENGTH_LONG).show();
            finish();
//            Intent intent = new Intent(this, FoodSearch_Ref.class);
//            startActivity(intent);

//            Explode explode = new Explode();
//            explode.setDuration(500);
//            getWindow().setExitTransition(explode);
//            getWindow().setEnterTransition(explode);
//            ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//            Intent i2 = new Intent(this,FoodSearch_Ref.class);
//            startActivity(i2, oc2.toBundle());
        }



    }
}

