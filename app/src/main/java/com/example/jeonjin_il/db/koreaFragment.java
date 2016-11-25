package com.example.jeonjin_il.db;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class koreaFragment extends Fragment  {
    ListView listview ;
    ArrayList<FoodListItem> datas = new ArrayList<FoodListItem>();

    public koreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_korea, container, false);
        // Inflate the layout for this fragment
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.fragment_korea, container, false);

        datas.add(new FoodListItem(R.drawable.food_4,readTxt(R.raw.namefood_4),readTxt(R.raw.howfood_4)));
        datas.add(new FoodListItem(R.drawable.food_5,readTxt(R.raw.namefood_5),readTxt(R.raw.howfood_5)));


        listview = (ListView)ll.findViewById(R.id.listvie);
        Custom_Adapter list_adapter = new Custom_Adapter(getActivity().getLayoutInflater(),datas,getContext());
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
