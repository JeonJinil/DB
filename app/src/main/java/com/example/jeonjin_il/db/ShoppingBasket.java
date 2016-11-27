package com.example.jeonjin_il.db;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_search :
                Intent intent_hash = new Intent(getApplication(),FoodSearch_Hash.class);
                startActivity(intent_hash);
                return true;
            case R.id.action_fridge:
                Intent intent_ref = new Intent(getApplication(),FoodSearch_Ref.class);
                startActivity(intent_ref);
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
        setContentView(R.layout.activity_shoppingbasket);

        displayListView();
        checkButtonClick();

    }
    private void displayListView()
    {
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);
        //dbHelper.basket_delete();
        //dbHelper.basket_insert("user01",1,4);
        //dbHelper.basket_insert("user01",2,3);
        //dbHelper.basket_insert("user01",3,2);
        //dbHelper.basket_insert("user01",4,1);
        ArrayList<ShoppingItem> ShoppingList= dbHelper.material_list("user01");



        Log.d("NUM","finish");



        dataAdapter=new MyAdapter(this,R.layout.activity_shoppingitem,ShoppingList);
        ListView listView=(ListView)findViewById(R.id.shopping_list);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShoppingItem item = (ShoppingItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Clicked on Row: " + item.getName(), Toast.LENGTH_LONG).show();

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
                        Toast.makeText(getApplicationContext(), "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplicationContext(), (item.getName()+" "+item.getBuynum()) + "개 선택", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });
            holder.name.setText(item.getName());
            holder.name.setChecked(item.isSelected());
            holder.name.setTag(item);
            Log.d("NUM",Integer.toString(item.getNum()));
            holder.textView.setText(Integer.toString(item.getNum()));
            itemNum=new ArrayList<String>();
            itemNum.addAll(item.getItemNum());
            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(
                    getApplicationContext(),android.R.layout.simple_spinner_item,itemNum);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spinner.setAdapter(adapter1);

            return convertView;
        }
    }
    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp = "";
                DBHelper db = new DBHelper(getApplicationContext(), "FOOD1.db", null, 1);

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<ShoppingItem> ShoppingList = dataAdapter.ShoppingList;
                for (int i = 0; i < ShoppingList.size(); i++) {
                    ShoppingItem item = ShoppingList.get(i);
                    if (item.isSelected()) {
                        responseText.append("\n" + item.getName() + " " + item.getBuynum() + "개");
                        db.updateBasket("user01", item.getName(), item.getNum(), item.getBuynum());
                        item.setNum(item.getNum() - item.getBuynum());

                    }

                    Toast.makeText(getApplicationContext(),
                            responseText, Toast.LENGTH_LONG).show();


                }
            }
        });

    }
    }





