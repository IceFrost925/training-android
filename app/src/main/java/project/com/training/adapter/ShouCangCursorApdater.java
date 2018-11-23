package project.com.training.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import project.com.training.R;
import project.com.training.dao.BooksDao;
import project.com.training.dao.CollectDao;
import project.com.training.model.Collect;


public class ShouCangCursorApdater extends CursorAdapter implements View.OnClickListener {
    private ImageView btn_gw,btn_del;

    public ShouCangCursorApdater(Context context, Cursor c) {
        super(context, c, 0);
    }

    // 加载ListView的每个子项item_student.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view= LayoutInflater.from(context).inflate(R.layout.sc_item, viewGroup, false);
        btn_gw=view.findViewById(R.id.add_gw);
        btn_del=view.findViewById(R.id.del);
        btn_gw.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        return view;
    }

    // 绑定cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 1. 根据view获取item_student.xml中的每个控件
        ImageView image = view.findViewById(R.id.image);
        TextView bookname = view.findViewById(R.id.name);
        TextView price = view.findViewById(R.id.price);
        TextView kucun=view.findViewById(R.id.kucun);
        TextView number=view.findViewById(R.id.number);

        // 2. 给每个控件赋值，数据来源为cursor
        //image.setImageResource(Integer.parseInt(cursor.getString(cursor.getColumnIndex("picture"))));
        kucun.setText(cursor.getString(cursor.getColumnIndex("count")));
        number.setText(cursor.getString(cursor.getColumnIndex("jhi_number")));
        bookname.setText(cursor.getString(cursor.getColumnIndex("name")));
        price.setText(cursor.getString(cursor.getColumnIndex("price")));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_shopCar:

                break;
            case R.id.btn_recieve:
             /*   CollectDao collectDao=new CollectDao();

                BooksDao booksDao=new BooksDao(getActivity());
                Cursor cursor=booksDao.findAllbooks();

                Collect collect=new Collect();
                collect.setBook_id(cursor.getLong(cursor.getColumnIndex("id")));
                collect.setSuser_id(Long.valueOf(2));
                if(collectDao.insert(collect)){
                    Toast.makeText(getActivity(),"收藏成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"收藏失败！",Toast.LENGTH_SHORT).show();
                }*/


                break;
        }
    }
}
