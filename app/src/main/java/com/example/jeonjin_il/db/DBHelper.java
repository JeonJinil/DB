package com.example.jeonjin_il.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                " material_id INTEGER , num INTEGER );");

        db.execSQL("CREATE TABLE MATERIAL (material_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " material_name TEXT, material_type TEXT, remain_num INTEGER, price INTEGER, DATE TEXT);");

        db.execSQL("CREATE TABLE RECIPE (food_id INTEGER,"+
                " material_id INTEGER );");

        db.execSQL("CREATE TABLE HASH (food_id INTEGER,"+
                " tag TEXT);");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isinti(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HASH ", null);
        while(cursor.moveToNext()){
            return true;
        }
        return false;
    }


    public ArrayList<Integer> food_hash(String str){
        ArrayList<Integer> ret = new ArrayList<Integer>();

        SQLiteDatabase db = getReadableDatabase();

        StringTokenizer st = new StringTokenizer(str,"#");
        while(st.hasMoreTokens()){
            String data = st.nextToken();
            Log.d("TAG","data : "+data);
            Cursor cursor = db.rawQuery("SELECT * FROM HASH WHERE tag = '"+data+"' ", null);
            while(cursor.moveToNext()){
                Log.d("TAG","cursor in");
                boolean ok = true;
                for(int i=0;i<ret.size();i++){
                    if(ret.get(i) == cursor.getInt(0)){
                        ok = false;
                        break;
                    }
                }
                if(ok)
                    ret.add(cursor.getInt(0));
            }
        }

        return ret;
    }

    public int food_random(){
        SQLiteDatabase db = getReadableDatabase();

        int last = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM FOOD ", null);
        while(cursor.moveToNext()){
            last++;
        }
        Log.d("TAG", String.valueOf(last));
        double random_double = Math.random();
        int random_int = (int)(random_double * last);
        return random_int ;
    }

    public void food_insert(String name,String type ) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO FOOD VALUES(null, '" + name + "', '" + type + "');");
        db.close();
    }

    public void material_insert(String name , String type , int num , int price , String date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MATERIAL VALUES(null, '" + name + "', '" + type + "'," + num +","+price +",'"+date+"');");
        db.close();
    }

    public void recipe_insert(int food_id , int material_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RECIPE VALUES(" + food_id + ", " + material_id + ");");
        db.close();
    }

    public void hash_insert(int food_id, String tag){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO HASH VALUES(" + food_id + ", '" + tag + "');");
        db.close();
    }

    public ArrayList<Integer> food_search_by_ref(String select_input){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT R.food_id FROM MATERIAL AS M, RECIPE AS R " +
                "WHERE R.material_id = M.material_id AND M.material_name = '"+select_input+"'", null);
        while(cursor.moveToNext()){
            ret.add(cursor.getInt(0));
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


    public void fooddetail_Buy(String user_id,String food_name) {
        SQLiteDatabase db = getWritableDatabase();
        int food_id;
        int material_id;
        int flag=0;
        Cursor cursor = db.rawQuery("SELECT food_id FROM FOOD WHERE food_name='" + food_name + "'", null);
        Log.v("음식",food_name);
        cursor.moveToNext();
        food_id = cursor.getInt(0);
        Log.v("음식id",Integer.toString(food_id));
        cursor = db.rawQuery("SELECT material_id FROM RECIPE WHERE food_id='" + food_id + "'", null);

        Cursor cursor1 = db.rawQuery("SELECT material_id FROM BASKET WHERE user_id='" + user_id + "'", null);
        while (cursor.moveToNext()) {
            while (cursor1.moveToNext()) {
                Log.v("재료",Integer.toString(cursor.getInt(0)));
                if (cursor.getInt(0) == cursor1.getInt(0)) {
                    Cursor temp = db.rawQuery("SELECT num " +
                            "FROM BASKET " +
                            "WHERE user_id='" + user_id + "' and material_id='" + cursor.getInt(0) + "'", null);
                    temp.moveToNext();
                    int mate_num = temp.getInt(0);
                    mate_num++;
                    db.execSQL("UPDATE BASKET SET num='" + mate_num + "' " +
                            "WHERE user_id='" + user_id + "' and material_id='" + cursor.getInt(0) + "'");
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                db.execSQL("INSERT INTO BASKET VALUES('"+user_id+"','"+cursor.getInt(0)+"',1)");
            }
            cursor1.moveToFirst();
            flag=0;

        }
        db.close();
    }

    public void basket_insert(String basket_id,int material_id,int num){
        SQLiteDatabase db=getWritableDatabase();

        db.execSQL("INSERT INTO BASKET VALUES('"+ basket_id +"','"+ material_id +"','"+ num + "')");
        db.close();
    }
    public void basket_delete()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from BASKET");
        db.execSQL("delete from MATERIAL");
        db.execSQL("delete from FOOD");
        db.execSQL("delete from RECIPE");
        db.close();
    }

    public ArrayList<ShoppingItem> material_list(String id){
        ArrayList<ShoppingItem> ret = new ArrayList<ShoppingItem>();
        SQLiteDatabase db=getReadableDatabase();
        ShoppingItem item;
        Cursor cursor=db.rawQuery("SELECT material_name,num,remain_num,DATE FROM BASKET,MATERIAL WHERE user_id='"+ id +"' and  MATERIAL.material_id=BASKET.material_id",null);

        while(cursor.moveToNext()) {
            item=new ShoppingItem(cursor.getString(0),cursor.getInt(1),false,cursor.getInt(2), cursor.getString(3));
            ret.add(item);

        }
        db.close();
        return ret;
    }

    public int updateBasket(String user_id,String material_name,int num,int Buynum)
    { // num은 한 재료의 장바구니 안에 있는 개수 Buynum은 유저가 한 재료를 구매한 개수
        int material_id;
        int remain;
        int update_remain;
        int update_num;
        int price;
        String date;
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery( //material_id 가져오기
                "SELECT material_id,price FROM MATERIAL WHERE MATERIAL.material_name='"+ material_name +"'",null);
        cursor.moveToNext();
        material_id=cursor.getInt(0); // material_id를 가져왔다
        price=cursor.getInt(1); //material의 price를 가져왔다



        if((num-Buynum)==0) //장바구니에 담겨있는 한 재료를 모두 구매할 때 BASKET에서 그 재료 지우기
        {
            db.execSQL("DELETE FROM BASKET WHERE user_id='"+ user_id +"' and material_id='"+ material_id +"'");
        }
        else //재료를 샀으나 그 재료가 일부 남아있을 때
        {
            update_num = num - Buynum;
            db.execSQL("UPDATE BASKET SET num='" + update_num + "' WHERE user_id='" + user_id + "' and material_id='" + material_id + "'");
        }
        cursor=db.rawQuery("SELECT remain_num FROM MATERIAL WHERE material_id='"+ material_id +"'",null); //재료의 재고 가져오기
        cursor.moveToNext();
        remain=cursor.getInt(0); //재고 가져왔음
        update_remain=remain-Buynum;
        db.execSQL("UPDATE MATERIAL SET remain_num='"+ update_remain +"' WHERE material_id='"+ material_id +"'");
        //db.execSQL("INSERT INTO BASKET VALUES(null,'"+ basket_id +"','"+ material +"')");

        db.close();
        return price;
    }
    /////// 해람
    public ArrayList<Integer> getMaterialListByName(String name, int sortType, boolean reverse){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        String sortString, reverseString;
        switch(sortType){
            case 1: sortString = "remain_num"; if(reverse) reverseString = "DESC"; else reverseString = "ASC"; break;
            case 2: sortString = "DATE"; if(reverse) reverseString = "ASC"; else reverseString = "DESC"; break;
            default: sortString = "material_name"; if(reverse) reverseString = "DESC"; else reverseString = "ASC";
        }

        if(!name.equals("")){
            cursor = db.rawQuery(String.format("SELECT material_id FROM MATERIAL WHERE material_name like '%%%s%%'" +
                    "ORDER BY %s %s", name, sortString, reverseString), null);
        }
        else cursor = db.rawQuery(String.format("SELECT material_id FROM MATERIAL " +
                "ORDER BY %s %s", sortString, reverseString), null);
        while(cursor.moveToNext()){
            ret.add(cursor.getInt(0));
        }

        return ret;
    }

    public MaterialListItem materialSearchById(int i) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT material_name, remain_num, DATE FROM MATERIAL WHERE material_id = %d", i), null);

        MaterialListItem item = new MaterialListItem();
        if(cursor.moveToNext()){
            item.setName(cursor.getString(0));
            item.setRemain(cursor.getInt(1));
            item.setDate(cursor.getString(2));
        }
        else{ item.setName("null"); item.setRemain(0); }

        db.close();
        return item;
    }

    public void updateMaterial(ArrayList<MaterialListItem> list){
        SQLiteDatabase db = getWritableDatabase();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        for(MaterialListItem item : list){
            db.execSQL(String.format("UPDATE MATERIAL SET remain_num = %d, DATE = '%s' WHERE material_name = '%s'", item.getRemain(), date, item.getName()));
//            db.execSQL("UPDATE BASKET SET material='"+ material1 +"' WHERE basket_id="+"'user01';");
        }
        db.close();
    }

    public ArrayList<TextView> getTagCloud(Context c){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TextView> ret = new ArrayList<TextView>();
        TextView tv;
        int sum;
        float size;

        Cursor cursor = db.rawQuery(String.format("SELECT count(*) FROM HASH"), null);
        if(cursor.moveToNext()){
            sum = cursor.getInt(0);

            Log.d("TAG",String.format("sum = %d", cursor.getInt(0)));
        }
        else return ret;

        cursor = db.rawQuery(String.format("SELECT tag, count(*) FROM HASH GROUP BY tag"), null);
        while(cursor.moveToNext()){
            tv = new TextView(c);
            tv.setPadding(3,3,3,3);
            tv.setText("#" + cursor.getString(0));
            size = Math.max(12, ((float) (cursor.getInt(1) ^ (3/4)) / sum) * 150);
            tv.setTextSize(size);
            tv.measure(0, 0);
            ret.add(tv);

            Log.d("TAG",String.format("tag = %s, count = %d, size = %.2f", cursor.getString(0), cursor.getInt(1), size));
        }

        return ret;
    }
}



