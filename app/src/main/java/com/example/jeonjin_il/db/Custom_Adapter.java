package com.example.jeonjin_il.db;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by jeonjin-il on 2016. 11. 26..
 */

public class Custom_Adapter extends BaseAdapter {

    ArrayList<FoodListItem> datas;
    LayoutInflater inflater;
    Context context;
    String str;
    public Custom_Adapter(LayoutInflater inflater, ArrayList<FoodListItem> datas, Context context, String id) {
        // TODO Auto-generated constructor stub

        this.datas = datas;
        this.inflater = inflater;
        this.context =context;
        this.str = id;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size(); //datas의 개수를 리턴
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub2
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


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_fooditem, null);
        }
        ImageButton img_flag = (ImageButton) convertView.findViewById(R.id.fooditme_button);
        img_flag.setImageResource(datas.get(position).getIconDrawable());
        img_flag.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetail.class);
                intent.putExtra("Food_name", datas.get(position).getFood_name());
                intent.putExtra("Food_icon", datas.get(position).getIconDrawable());
                intent.putExtra("Food_how", datas.get(position).getHow());
                intent.putExtra("ID",str);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}