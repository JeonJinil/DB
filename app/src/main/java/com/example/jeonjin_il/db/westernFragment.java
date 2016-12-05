package com.example.jeonjin_il.db;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class westernFragment extends Fragment  {
    ListView listview ;
    ArrayList<FoodListItem> datas = new ArrayList<FoodListItem>();

    public westernFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_western, container, false);
        // Inflate the layout for this fragment
        String str = getArguments().getString("ID");
        Log.i("TAG",str);
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.fragment_western, container, false);


        datas.add(new FoodListItem(R.drawable.food_6,readTxt(R.raw.namefood_6),readTxt(R.raw.howfood_6)));
        datas.add(new FoodListItem(R.drawable.food_7,readTxt(R.raw.namefood_7),readTxt(R.raw.howfood_7)));

        listview = (ListView)ll.findViewById(R.id.listvie);
        Custom_Adapter list_adapter = new Custom_Adapter(getActivity().getLayoutInflater(),datas,getContext(),str);

        listview.setAdapter(list_adapter);

        return ll;

    }

    private String readTxt(int text){

        InputStream inputStream = getResources().openRawResource(text);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}


