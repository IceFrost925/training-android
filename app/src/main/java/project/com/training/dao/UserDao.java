package project.com.training.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import project.com.training.model.User;
import project.com.training.utils.DBHelper;


// 功能：完成Student表的记录的CRUD
public class UserDao {
    private DBHelper helper;

    // 构造方法，初始化DBHelper对象
    public UserDao(Context context) {
        helper = new DBHelper(context, 1);
    }
    /*
    // 1. 查询所有的记录，给CursorAdapter使用
    public Cursor getCursor() {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        Cursor cursor = db.query("city", null, null, null,
                null, null, null);
//        Cursor cursor = db.rawQuery("select * from student", null);
        return  cursor;
    }*/
/*
    // 1. 查询所有的记录，给CursorAdapter使用
    public Cursor getCursor() {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        Cursor cursor = db.query("student", null, null, null,
                null, null, null);
//        Cursor cursor = db.rawQuery("select * from student", null);
        return  cursor;
    }*/

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
    // 返回User对象的登录
    public User Login(String username,String password) {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        String sql="select * from s_user where username=? and passwd=?";
        User user= (User) db.rawQuery(sql, new String[]{username,password});
//        Cursor cursor = db.rawQuery("select * from student", null);
        return  user;
    }
    //登录
    public boolean login(String username,String password){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from s_user where username=? and passwd=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //判断用户是否存在
    public boolean ifUserexist(String username) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from s_user where username=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }










}
