package com.example.jeonjin_il.db;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class ShoppingBasket extends AppCompatActivity {
    DBHelper dbHelper;
    MyAdapter dataAdapter=null;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingbasket);

        displayListView();
        checkButtonClick();

    }
    private void displayListView()
    {
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

        ArrayList<ShoppingItem> ShoppingList= dbHelper.material_list("user01");

        dataAdapter=new MyAdapter(this,R.layout.activity_shoppingitem,ShoppingList);
        ListView listView=(ListView)findViewById(R.id.shopping_list);
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ShoppingItem item = (ShoppingItem) parent.getItemAtPosition(position);
            }
        });
    }

    private class MyAdapter extends ArrayAdapter<ShoppingItem> {
        private ArrayList<ShoppingItem> ShoppingList;
        public MyAdapter(Context context, int Resourceid, ArrayList<ShoppingItem> ShoppingList) {
            super(context, Resourceid, ShoppingList);
            this.ShoppingList = new ArrayList<ShoppingItem>();
            this.ShoppingList.addAll(ShoppingList);
        }
        ArrayList<String> itemNum;

        private class ViewHolder {
            CheckBox name;
            TextView textView;
            Spinner spinner;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             ViewHolder holder = null;
            if (convertView == null) {
                LayoutInflater v = (LayoutInflater) getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                convertView = v.inflate(R.layout.activity_shoppingitem, null);
                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.textView = (TextView) convertView.findViewById(R.id.shopping_item_textview);
                holder.spinner = (Spinner) convertView.findViewById(R.id.spinner);

                convertView.setTag(holder);
                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        ShoppingItem item = (ShoppingItem) cb.getTag();
                        item.setSelected(cb.isChecked());

                    }
                });

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final ShoppingItem item = ShoppingList.get(position);
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent,View view, int position, long id) {
                    item.setBuynum(position + 1);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });
            holder.name.setText(item.getName());
            holder.name.setChecked(item.isSelected());
            holder.name.setTag(item);
            holder.textView.setText(item.getDate());
            itemNum=new ArrayList<String>();
            itemNum.addAll(item.getItemNum());


            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(
                    getApplicationContext(),R.layout.spinner,itemNum);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinner.setAdapter(adapter1);

            holder.spinner.setSelection(item.getNum()-1);
            return convertView;
        }
    }
    private void checkButtonClick() {
        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp = "";
                int price=0;
                int total_price=0;
                boolean buy_flag = true;
                DBHelper db = new DBHelper(getApplicationContext(), "FOOD1.db", null, 1);

                StringBuffer responseText = new StringBuffer();
                ArrayList<ShoppingItem> ShoppingList = dataAdapter.ShoppingList;
                for (int i = 0; i < ShoppingList.size(); i++) {
                    ShoppingItem item = ShoppingList.get(i);
                    if (item.isSelected()) {
                        if(item.getRemain() - item.getBuynum()  < 0 ){
                            responseText.append(item.getName() + "의 남은 개수는 " + item.getRemain()+ "개 입니다.\n");
                            buy_flag = false;
                    }
                    }
                }
                if(buy_flag) {
                    for (int i = 0; i < ShoppingList.size(); i++) {
                        ShoppingItem item = ShoppingList.get(i);
                        if (item.isSelected()) {
                            price = 0;
                            responseText.append("\n" + item.getName() + " " + item.getBuynum() + "개");
                                price = db.updateBasket("user01", item.getName(), item.getNum(), item.getBuynum());
                                item.setNum(item.getNum() - item.getBuynum());
                                price = price * item.getBuynum();
                                total_price = total_price + price;
                        }
                    }
                }
                if(buy_flag) {
                    responseText.append("\n" + "총 " + total_price + "원");
                    Toast.makeText(getApplicationContext(),
                            responseText, Toast.LENGTH_SHORT).show();
                }else{
                    responseText.append("개수를 조정해주세요! ^^");
                    Toast.makeText(getApplicationContext(),
                            responseText, Toast.LENGTH_SHORT).show();
                }
                displayListView();
            }
        });

    }
    }





