package project.com.training.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.com.training.model.Collect;
import project.com.training.model.User;
import project.com.training.utils.DBHelper;

public class CollectDao {
    private DBHelper helper;

    // 构造方法，初始化DBHelper对象
    public CollectDao(){};
    public CollectDao(Context context) {
        helper = new DBHelper(context, 1);
    }
    public Cursor findAllCollect() {
        //Book book=null;
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select id as _id,picture,name,jhi_type,price,jhi_describe from books";
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql, null);
        //if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
        //book.setId(cursor.getLong(cursor.getColumnIndex("id")));
        // }
        // db.close();
        return cursor;
    }
    public Cursor findShouCang() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select books.id as _id,books.name,books.price,books.picture," +
                "books.jhi_number,books.count,books.jhi_describe " +
                "from collect inner join books on "+
                "collect.book_id=books.id";
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql,null
                );

        // db.close();
        return cursor;
    }
    public List findShouCangList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select books.id as _id,books.name,books.price,books.picture," +
                "books.jhi_number,books.count,books.jhi_describe " +
                "from collect inner join books on "+
                "collect.book_id=books.id";
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql,null
        );
        List list=new ArrayList();
        while(cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("bookid", cursor.getString(0));
            map.put("name", cursor.getString(1));
            map.put("price", cursor.getString(2));
            map.put("picture", cursor.getString(3));
            map.put("jhi_number", cursor.getString(4));
            map.put("count", cursor.getString(5));
            map.put("jhi_describe", cursor.getString(6));

            list.add(map);
        }
        // db.close();
        return list;
    }
    public Cursor findMyShouCang(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        //String sql = "select * from books";
        String sql="select books.id as _id,books.name,books.price,books.picture,books.jhi_number,books.count,books.jhi_describe" +
                "from collect inner join books on " +
                "collect.book_id=books.id where collect.suser_id="+id;
        //Book book= (Book) db.rawQuery(sql, null);
        Cursor cursor= db.rawQuery(sql, new String[
                id
                ]);

        // db.close();
        return cursor;
    }
    public boolean insert(Collect collect) {
        String sql = "insert into collect(suser_id,book_id) values(?,?)";
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL(sql, new String[]{
                //String.valueOf(collect.getId()),
                String.valueOf(collect.getSuser_id()),
                String.valueOf(collect.getBook_id())

        });
        //db.close();
        return true;
    }
    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("collect", "book_id=?", new String[]{String.valueOf(id)});
        //db.close();
    }
    //判断用户是否存在
    public boolean ifCollectExist(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from collect where book_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

}
