package com.example.jeonjin_il.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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

        db.execSQL("CREATE TABLE FOOD (_key INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, type INTEGER, material TEXT, tag TEXT);");

        db.execSQL("CREATE TABLE USER (_key INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT,pw TEXT, num INTEGER);");

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

    public void user_insert(String id, String pw) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER",null);
        int num = cursor.getCount()+1;
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO USER VALUES(null, '" + id + "', '" + pw + "', '" + num + "');");
        Log.i("TAG","okgg"+num);

        db.close();
    }
    public int Login(String id, String pw) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);

        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(id) && cursor.getString(2).equals(pw) ){
                Log.d("TAG", String.valueOf(cursor.getInt(0))+"DBHelper_user");
                return cursor.getInt(0);
            }
        }

        return -1;
    }
}



