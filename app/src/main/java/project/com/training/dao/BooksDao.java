package project.com.training.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import project.com.training.model.Book;
import project.com.training.model.User;
import project.com.training.utils.DBHelper;


// 功能：完成Student表的记录的CRUD
public class BooksDao {
    private DBHelper helper;

    // 构造方法，初始化DBHelper对象
    public BooksDao(Context context) {
        helper = new DBHelper(context, 1);
    }


    //根据书的种类查询
    public List<Book> findbooksByType(String jhi_type) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from books where jhi_type=?";
        List<Book> list= (List<Book>) db.rawQuery(sql, new String[]{jhi_type});
        db.close();
        return list;
    }
    //根据书的种类查询
    public List<Book> findAllbooks() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from books";
        //Book book= (Book) db.rawQuery(sql, null);
        List<Book> list= (List<Book>) db.rawQuery(sql, null);
        db.close();
        return list;
    }

    /*public void deleteBySQL(int id) {
        String sql = "delete from student where _id=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[] {
                String.valueOf(id)
        });
        db.close();
    }*/
    //根据书的名字查询
    public Book findbooksByName(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from books where name=?";
        Book book= (Book) db.rawQuery(sql, new String[]{name});
        db.close();
        return book;
    }

}


// 1. 查询所有的书记录，给CursorAdapter使用
    /*public Cursor getCursor() {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        Cursor cursor = db.query("books", null, null, null,
                null, null, null);
//        Cursor cursor = db.rawQuery("select * from student", null);
        return  cursor;
    }
    // 1. 根据种类查询所有的书记录，给CursorAdapter使用
    public Cursor getCursorByType(String jhi_type) {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        Cursor cursor = db.query("books", null, jhi_type, null,
                null, null, null);
//        Cursor cursor = db.rawQuery("select * from student", null);
        return  cursor;
    }

    public void insertUser(User user) {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getWritableDatabase();
        // 2. 组装需要插入的数据ContentValues对象
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("username", user.getUsername());
        values.put("passwd", user.getPasswd());
        // 3. 执行db.insert()方法插入记录
        long id = db.insert("s_user", null, values); // 返回插入数据的_id的值
        // 4. 关闭数据库
        db.close();
    }


    public void update(Student student) {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getWritableDatabase();
        // 2. 组装需要插入的数据ContentValues对象
        ContentValues values = new ContentValues();
        values.put("_id", student.get_id());
        values.put("name", student.getName());
        values.put("classmate", student.getClassmate());
        values.put("age", student.getAge());
        // 3. 执行db.update()方法更新记录
        long id = db.update("student", values, "_id=?",
                new String[]{String.valueOf(student.get_id())});
        // 4. 关闭数据库
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student", "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // 另一种方法：直接执行SQL语句，使用万能的execSQL()方法
    public void insertBySQL(Student student) {
        String sql = "insert into student(name, classmate, age) values(?,?,?)";

        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[] {
                student.getName(),
                student.getClassmate(),
                String.valueOf(student.get_id())
        });
        db.close();
    }*/


