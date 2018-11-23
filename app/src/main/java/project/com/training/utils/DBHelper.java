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
       /* String sql = "create table s_user(" +
                "id integer primary key autoincrement," +
                "username varchar(50)," +
                "image varchar(50)," +
                "email varchar(50)," +
                "passwd varchar(50)," +
                "tell varchar(50)," +
                "integral integer(11),"+
                "extra_1 varchar(50)," +
                "extra_2 varchar(50))"
                ;
        db.execSQL(sql);
        String sql2 = "create table books(" +
                "id integer primary key autoincrement," +
                "picture varchar(50)," +
                "name varchar(50)," +
                "jhi_type varchar(50)," +
                "jhi_number varchar(50)," +
                "count integer(11),"+
                "price integer(11)," +
                "jhi_descript varchar(50),"+
                "extra_1 varchar(50)," +
                "extra_2 varchar(50))"
                ;
        db.execSQL(sql2);
    }*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除student表
        String sql = "drop table if exists s_user";
        onCreate(db);
    }
}
