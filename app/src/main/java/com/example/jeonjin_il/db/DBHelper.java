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

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE FOOD (_key INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, type INTEGER, material TEXT, tag TEXT);");

        db.execSQL("CREATE TABLE USER (_key INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT,pw TEXT, num INTEGER);");

//        db.execSQL("CREATE TABLE BASKET(_key INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                " basket_id TEXT, material TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Integer> food_hash(String str){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ArrayList<String> temp = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT tag FROM FOOD ", null);

        StringTokenizer st = new StringTokenizer(str,"#");
        while(st.hasMoreTokens())
                temp.add(st.nextToken());

        for(int i=0;i<temp.size();i++){
            Log.d("TAG","temp "+temp.get(i));
        }

        int num = 0;
        while(cursor.moveToNext()){
            StringTokenizer st2 = new StringTokenizer(cursor.getString(0),"#");
            while(st2.hasMoreTokens()){
                boolean flag= false;
                String now_str = st2.nextToken();
                for(int i=0;i<temp.size();i++){
                    if(now_str.equals(temp.get(i))) {
                        ret.add(num);
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
            num++;
        }
        return ret;

    }

    public int food_random(){
        SQLiteDatabase db = getReadableDatabase();

        int last = -1;
        Cursor cursor = db.rawQuery("SELECT _key FROM FOOD ", null);
        while(cursor.moveToNext()){
            last++;
        }
        double random_double = Math.random();
        int random_int = (int)(random_double * last);
        return random_int ;
    }

    public void food_insert(String name,int type,String material, String tag) {
        SQLiteDatabase db = getWritableDatabase();
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


    public void user_insert(String id, String pw) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER",null);
        int num = cursor.getCount()+1;
        db.execSQL("INSERT INTO USER VALUES(null, '" + id + "', '" + pw + "', '" + num + "');");
        db.close();
    }
    public int Login(String id, String pw) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(id) && cursor.getString(2).equals(pw) ){
                Log.d("TAG", String.valueOf(cursor.getInt(0))+"DBHelper_user");
                return cursor.getInt(0);
            }
        }

        return -1;
    }
////////////////////////////////////////////////////////////////짱구

    public void makeBasket(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("CREATE TABLE BASKET(_key INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " basket_id TEXT, material TEXT);");
        db.close();
    }

    public void basket_insert(String basket_id,String material){
        SQLiteDatabase db=getWritableDatabase();

        db.execSQL("INSERT INTO BASKET VALUES(null,'"+ basket_id +"','"+ material +"')");
        db.close();
    }

    public ArrayList<String> material_list(String id){
        ArrayList<String> ret = new ArrayList<String>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT basket_id,material FROM BASKET ",null);

        while(cursor.moveToNext()) {
            if (cursor.getString(0).equals(id)) {
                StringTokenizer st = new StringTokenizer(cursor.getString(1), "@");
                while (st.hasMoreTokens())
                    ret.add(st.nextToken());
                break;
            }
        }
        return ret;
    }

    public void updateBasket(String material1)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE BASKET SET material='"+ material1 +"' WHERE basket_id="+"'user01';");
        //db.execSQL("INSERT INTO BASKET VALUES(null,'"+ basket_id +"','"+ material +"')");
        db.close();
    }




}



