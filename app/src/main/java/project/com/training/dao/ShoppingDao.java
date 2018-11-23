package project.com.training.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.com.training.model.Collect;
import project.com.training.model.Shopping;
import project.com.training.utils.DBHelper;

public class ShoppingDao {

    private Long id;
    private DBHelper helper;
    public ShoppingDao(){

    }
    public ShoppingDao(Cursor cursor){


    }
    // 构造方法，初始化DBHelper对象
    public ShoppingDao(Context context) {
        helper = new DBHelper(context, 1);
    }
    public boolean insert(Shopping shopping) {
        String sql = "insert into shopping(number,suser_id,book_id_id) values(?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{
               // String.valueOf(shopping.getId()),
                shopping.getNumber(),
                String.valueOf(shopping.getSuser_id()),
                String.valueOf(shopping.getBook_id_id()),


        });
        //db.close();
        return true;
    }
    public Cursor findMyGouWuChe() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select books.id as _id,books.name,books.price,books.picture,books.jhi_number,books.count,books.jhi_describe,shopping.number " +
                "from shopping inner join books on " +
                "shopping.book_id_id=books.id";
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql, null

                );

        // db.close();
        return cursor;
    }
    public List findMyGouWuCheList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select books.id as _id,books.name,books.price,books.picture,books.jhi_number,books.jhi_describe,shopping.number " +
                "from shopping inner join books on " +
                "shopping.book_id_id=books.id";
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql, null);
        List list=new ArrayList();
        while(cursor.moveToNext()){
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("bookid",cursor.getString(0));
            map.put("name",cursor.getString(1));
            map.put("price",cursor.getString(2));
            map.put("picture",cursor.getString(3));
            map.put("jhi_number",cursor.getString(4));
            map.put("jhi_describe",cursor.getString(5));
            map.put("number",cursor.getString(6));
           list.add(map);
        }

        // db.close();
        return list;
    }
    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("shopping", "book_id_id=?", new String[]{String.valueOf(id)});
       //db.close();
    }
    public void updateMyShopping(Shopping shopping) {
        String sql = "update shopping set number=? where book_id_id=?";

        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[] {
                shopping.getNumber(),
                String.valueOf(shopping.getBook_id_id())
        });
        //db.close();
    }
    //判断用户是否存在
    public boolean ifShoppingExist(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from shopping where book_id_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }
}
