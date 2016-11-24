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
        setContentView(R.layout.activity_shoppingbasket);

        displayListView();
        checkButtonClick();

    }
    private void displayListView()
    {
        dbHelper = new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

        ArrayList<ShoppingItem> ShoppingList=new ArrayList<ShoppingItem>();
        ShoppingItem item;

        ArrayList<String> list = dbHelper.material_list("user01");

        Integer temp[] = new Integer[list.size()];
        for(int i=0;i<list.size();i++){
            temp[i] = 1;
        }


        for(int i=0;i<list.size()-1;i++){
            if(temp[i] == -1)
                continue;
            for(int j=i+1;j<list.size();j++){
                if(list.get(i).equals(list.get(j))){
                    temp[i]++;
                    temp[j] = -1;
                }
            }
        }

        Log.d("NUM","finish");


        for(int num = 0;num < list.size();num++){
            if(temp[num] == -1)
                continue;
            item = new ShoppingItem(list.get(num),temp[num], false);
            ShoppingList.add(item);
        }
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

        private class ViewHolder {
            CheckBox name;
            TextView textView;
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

            ShoppingItem item = ShoppingList.get(position);
            holder.name.setText(item.getName());
            holder.name.setChecked(item.isSelected());
            holder.name.setTag(item);
            Log.d("NUM",Integer.toString(item.getNum()));
            holder.textView.setText(Integer.toString(item.getNum()));
            return convertView;
        }
    }
    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp="";
                DBHelper db=new DBHelper(getApplicationContext(),"FOOD1.db",null,1);

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<ShoppingItem> ShoppingList = dataAdapter.ShoppingList;
                for(int i=0;i<ShoppingList.size();i++){
                    ShoppingItem item = ShoppingList.get(i);
                    if(item.isSelected()){
                        responseText.append("\n" + item.getName());
                        temp=temp+"@"+item.getName();
                        Log.v("temp",temp);

                    }
                }
                db.updateBasket(temp);

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();


            }
        });

    }


}

