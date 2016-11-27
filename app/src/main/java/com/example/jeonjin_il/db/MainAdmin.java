package com.example.jeonjin_il.db;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class MainAdmin extends FragmentActivity {
    AdminItemListFragment list;
    DBHelper dbHelper;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        etSearch = (EditText) findViewById(R.id.et_adminSearch);
        Button btnSearch = (Button) findViewById(R.id.btn_adminSearch);
        Button btnSubmit = (Button) findViewById(R.id.btn_adminSubmit);
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

        etSearch.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    fragmentReplace(etSearch.getText().toString());
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReplace(etSearch.getText().toString());
            }});
        btnSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.updateMaterial();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }});

        fragmentReplace("");
    }

    private void fragmentReplace(String item) {
        ArrayList<Integer> itemId = dbHelper.getMaterialListByName(item);
        for(Integer i : itemId){
            Log.d("TAG",i.toString());
        }
        Bundle args = new Bundle();
        args.putIntegerArrayList("item", itemId);

        list = new AdminItemListFragment();
        list.setArguments(args);

        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_adminMaterialList, list);

        // Commit the transaction
        transaction.commit();
    }
}

