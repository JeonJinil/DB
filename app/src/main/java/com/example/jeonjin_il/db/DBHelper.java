package com.example.jeonjin_il.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by jeonjin-il on 2016. 11. 10..
 */
public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        //USER (table 이름)
        //key(pk,int) , id (text) , pw (text)  (어트리뷰트)

            db.execSQL("CREATE TABLE FOOD (_key INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT, type INTEGER, material TEXT, tag TEXT);");


   }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void food_insert(String name,int type,String material, String tag) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO FOOD VALUES(null, '" + name + "', '" + type + "', '" + material + "', '" + tag + "');");
        db.close();
    }
    public ArrayList<String> food_search_by_ref(String select_input){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name,material FROM FOOD ", null);

        while(cursor.moveToNext()){
            StringTokenizer st = new StringTokenizer(cursor.getString(1),"@");
            while(st.hasMoreTokens()){
                if(st.nextToken().equals(select_input))
                    ret.add(cursor.getString(0));
            }
        }
        return ret;
    }
    public void test(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM FOOD", null);

        while(cursor.moveToNext()){
            Log.d("TAG",cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getInt(2)+" "+cursor.getString(3)+" "+cursor.getString(4));
        }
    }
//
    public void update(String item, int price) {
        SQLiteDatabase db = getWritableDatabase();
        //미구
//        db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        //미구현
//        db.execSQL("DELETE FROM MONEYBOOK WHERE item='" + item + "';");
        db.close();
    }
}



