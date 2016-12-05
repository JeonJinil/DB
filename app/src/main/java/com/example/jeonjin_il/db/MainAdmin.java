package com.example.jeonjin_il.db;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class MainAdmin extends FragmentActivity {
    private DBHelper dbHelper;
    private AdminItemListFragment listFragment;
    private FragmentTransaction transaction;

    private EditText etSearch;
    private Spinner spnSort;
    private CheckBox chbxReverse;
    private Button btnSubmit;

    private int sortType = 0; // 0 = 이름순, 1 = 재고순, 2 = 갱신날짜순
    private boolean reverse = false; // true = 역순
    private String lastSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        etSearch = (EditText) findViewById(R.id.et_adminSearch);
        Button btnSearch = (Button) findViewById(R.id.btn_adminSearch);
        btnSubmit = (Button) findViewById(R.id.btn_adminSubmit);
        spnSort = (Spinner) findViewById(R.id.spn_adminSort);
        chbxReverse = (CheckBox) findViewById(R.id.chbx_adminSortReverse);

        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        updateArgs();

        etSearch.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    updateArgs();
                    Toast.makeText(v.getContext(),String.format("%d개의 검색 결과가 있습니다.", listFragmentReplaceByName()),Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        btnSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArgs();
                Toast.makeText(v.getContext(),String.format("%d개의 검색 결과가 있습니다.", listFragmentReplaceByName()),Toast.LENGTH_SHORT).show();
            }});
        btnSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = listFragment.updateMaterial();
                if(size != 0){
                    listFragmentReplaceByName();
                    Toast.makeText(v.getContext(),String.format("%d개 재료 정보를 수정하였습니다.", size),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(v.getContext(),"변경할 재료를 선택해주세요.",Toast.LENGTH_SHORT).show();
                }
            }});

        Toast.makeText(this,String.format("현재 DB에 %d개의 재료가 있습니다.", listFragmentReplaceByName()),Toast.LENGTH_SHORT).show();
    }

    private int listFragmentReplaceByName() {
        ArrayList<Integer> results = dbHelper.getMaterialListByName(lastSearch, sortType, reverse);
        Bundle args = new Bundle();
        args.putIntegerArrayList("item", results);

        listFragment = new AdminItemListFragment();
        listFragment.setArguments(args);

        fragmentReplace();
        return results.size();
    }

    private void fragmentReplace(){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_adminMaterialList, listFragment);
        transaction.commit();

        hideKeyboard();
    }

    protected void updateArgs(){
        lastSearch = etSearch.getText().toString();
        sortType = spnSort.getSelectedItemPosition();
        reverse = chbxReverse.isChecked();
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
    }
}

