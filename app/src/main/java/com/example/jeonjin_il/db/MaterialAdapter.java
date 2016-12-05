package com.example.jeonjin_il.db;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bbusy on 2016-11-26.
 */

public class MaterialAdapter extends BaseAdapter {

    // 문자열을 보관 할 ArrayList
    private ArrayList<MaterialListItem> m_List;

    // 생성자
    public MaterialAdapter() {
        m_List = new ArrayList<MaterialListItem>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public MaterialListItem getItem(int position) {
        return m_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_admin_item_view, parent, false);
        }

        // TextView에 현재 position의 문자열 추가
        MaterialListItem item = m_List.get(position);
        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.chbx_item);
        checkBox.setText(item.getName());

        final EditText editText = (EditText) convertView.findViewById(R.id.et_adminRemain);
        editText.setText(String.valueOf(item.getRemain()));
        editText.setHint(String.valueOf(item.getRemain()));

        TextView tvDate = (TextView) convertView.findViewById(R.id.tv_adminDate);
        tvDate.setText(item.getDate());

        // 버튼을 터치 했을 때 이벤트 발생
        Button btnMinus = (Button) convertView.findViewById(R.id.btn_adminMinus);
        Button btnPlus = (Button) convertView.findViewById(R.id.btn_adminPlus);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(pos).toggle();
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if(!editText.getText().toString().equals("")){
                        MaterialListItem item = getItem(pos);
                        item.setRemain(Integer.parseInt(editText.getText().toString()));
                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        return true;
                    }
                }
                return false;
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(!hasFocus){
                    if(!editText.getText().toString().equals("")) {
                        MaterialListItem item = getItem(pos);
                        item.setRemain(Integer.parseInt(editText.getText().toString()));
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    else{
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialListItem item = getItem(pos);
                if(item.getRemain() > 0){
                    item.setRemain(item.getRemain()-1);
                    editText.setText(Integer.toString(item.getRemain()));
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialListItem item = getItem(pos);
                if(item.getRemain() < 9999){
                    item.setRemain(item.getRemain()+1);
                    editText.setText(Integer.toString(item.getRemain()));
                }
            }
        });

        return convertView;
    }

    public void addItem(MaterialListItem item){
        m_List.add(item);
    }

    public ArrayList<MaterialListItem> getUpdateList(){
        ArrayList<MaterialListItem> ret = new ArrayList<MaterialListItem>();
        for(MaterialListItem item : m_List){
            if(item.isChecked()){
                ret.add(item);
            }
        }
        return ret;
    }
}
