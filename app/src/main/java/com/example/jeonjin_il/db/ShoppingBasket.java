package com.example.jeonjin_il.db;

import android.content.Context;
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
        dbHelper = new DBHelper(getApplicationContext(),"FOOD.db",null,1);
        Log.d("TAG","1");
//        dbHelper.makeBasket();
//        dbHelper.basket_insert("user01","@김@밥@햄@단무지");
        Log.d("TAG","2");
        //테스트데이터 basket table에 넣으려면 dbHelper.basket_insert(id,material)
        //자꾸 displayListView()가 호출되서 같은데이터가 계속넣어지게되어 데이터몇개넣고
        //insert하는 구문 지운거임




        ArrayList<ShoppingItem> ShoppingList=new ArrayList<ShoppingItem>();
        ShoppingItem item;

        ArrayList<String> list = dbHelper.material_list("user01");
        Log.d("TAG",list.toString());

        while(!list.isEmpty()) {

            item = new ShoppingItem(list.get(0).toString(), false);
            list.remove(0);
            ShoppingList.add(item);
            Log.d("TAG",item.getName());
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
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            if (convertView == null) {
                LayoutInflater v = (LayoutInflater) getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                convertView = v.inflate(R.layout.activity_shoppingitem, null);
                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);

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
            return convertView;
        }
    }
    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String temp="";
                DBHelper db=new DBHelper(getApplicationContext(),"FOOD.db",null,1);

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

