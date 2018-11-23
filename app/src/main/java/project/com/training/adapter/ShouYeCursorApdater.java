package cn.edu.studentsql.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import cn.edu.studentsql.R;


public class StudentCursorApdater extends CursorAdapter {

    public StudentCursorApdater(Context context, Cursor c) {
        super(context, c, 0);
    }

    // 加载ListView的每个子项item_student.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);
    }

    // 绑定cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 1. 根据view获取item_student.xml中的每个控件
        TextView name = view.findViewById(R.id.tv_name);
        TextView classmate = view.findViewById(R.id.tv_classmate);
        TextView age = view.findViewById(R.id.tv_age);

        // 2. 给每个控件赋值，数据来源为cursor
        name.setText(cursor.getString(cursor.getColumnIndex("name")));
        classmate.setText(cursor.getString(cursor.getColumnIndex("classmate")));
        age.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("age"))));
    }
}
