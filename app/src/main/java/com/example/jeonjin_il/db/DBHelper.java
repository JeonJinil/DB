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

        db.execSQL("CREATE TABLE USER ( user_id TEXT PRIMARY KEY, pw TEXT, sex TEXT);");

        db.execSQL("CREATE TABLE FOOD ( food_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " food_name TEXT, food_type INTEGER );");

        db.execSQL("CREATE TABLE BASKET ( user_id TEXT ,"+
                " material_id INTEGER , num INTEGER , PRIMARY KEY(user_id,material_id);");

        db.execSQL("CREATE TABLE MATERIAL (material_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " material_name TEXT, material_type TEXT, remain_num INTEGER, price INTEGER, DATE INTEGER);");

        db.execSQL("CREATE TABLE RECIPE (food_id INTEGER,"+
                " material_id INTEGER ,PRIMARY KEY(food_id,material_id);");

        db.execSQL("CREATE TABLE HASH (food_id INTEGER,"+
                " tag TEXT, PRIMARY KEY(food_id,tag);");


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
        Cursor cursor = db.rawQuery("SELECT * FROM FOOD ", null);
        while(cursor.moveToNext()){
            last++;
        }
        double random_double = Math.random();
        int random_int = (int)(random_double * last);
        return random_int ;
    }

    public void food_insert(String name,String type ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO FOOD VALUES(null, '" + name + "', '" + type + "');");
        db.close();
    }

    public void recipe_insert(int food_id , String material_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RECIPE VALUES('" + food_id + "', '" + material_id + "');");
        db.close();
    }

    public void hash_insert(int food_id, String tag){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RECIPE VALUES('" + food_id + "', '" + tag + "');");
        db.close();
    }

    public ArrayList<Integer> food_search_by_ref(String select_input){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM MATERIAL ", null);
        int material_id = -1;
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(select_input)) {
                material_id = cursor.getInt(0);
                break;
            }
        }
        if(material_id == -1)
            return ret;

        Cursor cursor2 = db.rawQuery("SELECT * FROM RECIPE ", null);
        while(cursor2.moveToNext()){
            if(cursor2.getInt(1) == material_id ) {
                ret.add(cursor2.getInt(0));
            }
        }

        return ret;
    }


    public void user_insert(String id, String pw, String sex) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER",null);
        Log.i("TAG",sex);
        db.execSQL("INSERT INTO USER VALUES('" + id + "', '" + pw + "', '" + sex + "');");
        db.close();
    }
    public int Login(String id, String pw) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while(cursor.moveToNext()){
            if(cursor.getString(0).equals(id) && cursor.getString(1).equals(pw) ){
                Log.i("TAG",cursor.getString(2));
                return cursor.getInt(0);
            }
        }

        return -1;
    }

////////////////////////////////////////////////////////////////짱구


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



