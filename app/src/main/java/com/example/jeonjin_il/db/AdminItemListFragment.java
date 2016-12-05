package com.example.jeonjin_il.db;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminItemListFragment extends Fragment {
    private MaterialAdapter adapter;
    private DBHelper db;

    public AdminItemListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_item_list, container, false);
        db = new DBHelper(v.getContext(),"FOOD1.db",null,1);
        Bundle args = getArguments();
        ArrayList<Integer> itemList = args.getIntegerArrayList("item");

        adapter = new MaterialAdapter();
        ListView lv = (ListView) v.findViewById(R.id.lv_adminItemList);
        lv.setAdapter(adapter);
        MaterialListItem item;
        for(int m=0; m<itemList.size(); m++){
            Log.d("TAG", itemList.get(m).toString());
        }
        for(Integer i : itemList) {
            item = db.materialSearchById(i.intValue());
            if(item.getName() != "null") adapter.addItem(item);
        }

        return v;
    }

    public int updateMaterial(){
        ArrayList<MaterialListItem> list = adapter.getUpdateList();
        int size = list.size();
        if(size != 0){
            db.updateMaterial(list);
        }
        return size;
    }
}
