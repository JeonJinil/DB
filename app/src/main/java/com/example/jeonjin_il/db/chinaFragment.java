package com.example.jeonjin_il.db;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class chinaFragment extends Fragment  {
    ListView listview ;
    ArrayList<FoodListItem> datas = new ArrayList<FoodListItem>();

    public chinaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_china, container, false);
        // Inflate the layout for this fragment

        String str = getArguments().getString("ID");
        Log.i("TAG",str);
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.fragment_china, container, false);

        datas.add(new FoodListItem(R.drawable.food_2,readTxt(R.raw.namefood_2),readTxt(R.raw.howfood_2)));
        datas.add(new FoodListItem(R.drawable.food_3,readTxt(R.raw.namefood_3),readTxt(R.raw.howfood_3)));


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
