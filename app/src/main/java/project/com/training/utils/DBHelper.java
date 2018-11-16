package project.com.training.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// SQLite创建数据库及表的辅助类
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, int version) {
        super(context, "training.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. 定义个字符串 - 创建表(_id 整型 主键 自增，name字符串（大小），classmate字符串，age整型）
        String sql = "create table s_user(" +
                "id integer primary key autoincrement," +
                "username varchar(255)," +
                "image varchar(255)," +
                "email varchar(255)," +
                "passwd varchar(255)," +
                "tell varchar(255)," +
                "integral integer(11),"+
                "extra_1 varchar(255)," +
                "extra_2 varchar(255))"
                ;
        db.execSQL(sql);
        String sql2 = "create table books(" +
                "id integer primary key autoincrement," +
                "picture varchar(255)," +
                "name varchar(255)," +
                "jhi_type varchar(255)," +
                "jhi_number varchar(255)," +
                "count integer(11),"+
                "price integer(11)," +
                "jhi_descript varchar(255),"+
                "extra_1 varchar(255)," +
                "extra_2 varchar(255))"
                ;
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除student表
        String sql = "drop table if exists student";
        onCreate(db);
    }
}
