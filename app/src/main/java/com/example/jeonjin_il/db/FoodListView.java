package com.example.jeonjin_il.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class FoodListView extends AppCompatActivity {
    ListView listview ;
    ArrayList<FoodListItem> datas = new ArrayList<FoodListItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlistview);


        datas.add(new FoodListItem(R.drawable.food_01,"볶음밥"));
        datas.add(new FoodListItem(R.drawable.food_02,"라면"));
        datas.add(new FoodListItem(R.drawable.food_03,"꼬치구이"));

        listview = (ListView)findViewById(R.id.listview1);

        ListViewAdapter list_adapter = new ListViewAdapter(getLayoutInflater(),datas);
        listview.setAdapter(list_adapter);
        Log.d("TAG","finish");
    }


    public class ListViewAdapter extends BaseAdapter {

        ArrayList<FoodListItem> datas;
        LayoutInflater inflater;

        public ListViewAdapter(LayoutInflater inflater, ArrayList<FoodListItem> datas) {
            // TODO Auto-generated constructor stub

            this.datas= datas;
            this.inflater= inflater;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return datas.size(); //datas의 개수를 리턴
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return datas.get(position);//datas의 특정 인덱스 위치 객체 리턴.
        }

        //특정 위치(position)의 data(MemberData)을 지칭하는 아이디를 리턴하는 메소드
        //특별한 경우가 아니라면 보통은 data의 위치를 아이디로 사용하므로
        //전달받은 position를 그대로 리턴함.
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub


            if( convertView==null){
                convertView= inflater.inflate(R.layout.activity_fooditem, null);
            }
            Log.d("TAG",datas.get(position).getFood_name());
            ImageButton img_flag = (ImageButton) convertView.findViewById(R.id.fooditme_button);
            img_flag.setImageResource( datas.get(position).getIconDrawable() );
            img_flag.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),datas.get(position).getFood_name() +"요리가 눌림",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(),FoodDetail.class);
                    intent.putExtra("Food_name",datas.get(position).getFood_name());
                    intent.putExtra("Food_icon",datas.get(position).getIconDrawable());
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }
}

