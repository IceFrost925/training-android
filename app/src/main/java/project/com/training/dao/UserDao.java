package project.com.training.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
   /* public Cursor LoginUser(String email,String passwd) {
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        String sql="select * from s_user where email=? and passwd=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email,passwd});

        return cursor;
    }*/
   /* public void insertUser(User user) {
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
    }*/
    //注册
    public boolean insert(User user) {
        String sql = "insert into s_user(id,email,username,passwd) values(?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{
                String.valueOf(user.getId()),
                user.getEmail(),
                user.getUsername(),
                user.getPasswd()
        });
        //db.close();
        return true;
    }


    // 返回User对象的登录
   public User LoginUser(String username,String password) {

       User user=new User();
        // 1. 获取SQLiteDatabse对象db
        SQLiteDatabase db = helper.getReadableDatabase();
        // 2. 执行db.query()方法获取Cursor对象，
        // 相当于sql：select * from student
        String sql="select * from s_user where email=? and passwd=?";
        Cursor cursor= db.rawQuery(sql, new String[]{username,password});
//        Cursor cursor = db.rawQuery("select * from student", null);
       if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
           Log.d("iii", String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
           user.setId(cursor.getInt(cursor.getColumnIndex("id")));
           user.setUsername(cursor.getString(cursor.getColumnIndex("username")));

       }
       Log.d("user",user.toString());
        return  user;
    }

    //登录
    public boolean Login(String username,String password){

        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from s_user where email=? and passwd=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username,password});


        if(cursor != null){

            //Log.d("dl", String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
            cursor.close();
            return true;
        }
        return false;
    }
    //登录
   /* public User Login(String username,String password){
        User user=null;
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from s_user where email=? and passwd=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username,password});
        Log.d("gg", String.valueOf(cursor.getLong(cursor.getColumnIndex("id"))));
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            user.setId(cursor.getLong(cursor.getColumnIndex("id")));
            user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            cursor.close();


        }
        return user;
    }*/
    //判断用户是否存在
    public boolean ifUserExist(String username) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from s_user where email=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }










}
