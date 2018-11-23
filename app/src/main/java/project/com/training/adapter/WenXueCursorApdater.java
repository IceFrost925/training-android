package project.com.training.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import project.com.training.R;


public class WenXueCursorApdater extends CursorAdapter {

    public WenXueCursorApdater(Context context, Cursor c) {
        super(context, c, 0);
    }

    // 加载ListView的每个子项item_student.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.shouye_item, viewGroup, false);
    }

    // 绑定cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 1. 根据view获取item_student.xml中的每个控件
        ImageView image = view.findViewById(R.id.image);
        TextView bookname = view.findViewById(R.id.bookname);
        TextView price = view.findViewById(R.id.price);

        // 2. 给每个控件赋值，数据来源为cursor
        //image.setImageResource(Integer.parseInt(cursor.getString(cursor.getColumnIndex("picture"))));
        bookname.setText(cursor.getString(cursor.getColumnIndex("name")));
        price.setText(cursor.getString(cursor.getColumnIndex("price")));
    }
}
