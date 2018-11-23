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
import project.com.training.dao.CollectDao;
import project.com.training.dao.ShoppingDao;
import project.com.training.model.Collect;
import project.com.training.model.Shopping;


public class ShouYeCursorApdater extends CursorAdapter {
  //  private Button btn_gw, btn_sc;
    private Boolean mflag;
    private Context context;
    public ShouYeCursorApdater(Context context, Cursor c) {
        super(context, c, 0);

    }
    public Boolean setFlag(Boolean flag){
        mflag=flag;
        return mflag;
    }

    // 加载ListView的每个子项item_student.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.shouye_item, viewGroup, false);
       // btn_gw = view.findViewById(R.id.btn_shopCar);
        //btn_sc = view.findViewById(R.id.btn_recieve);

        return view;
    }

    // 绑定cursor
    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        // 1. 根据view获取item_student.xml中的每个控件
        ImageView image = view.findViewById(R.id.image);
        TextView bookname = view.findViewById(R.id.bookname);
        TextView price = view.findViewById(R.id.price);

        // 2. 给每个控件赋值，数据来源为cursor
        //image.setImageResource(Integer.parseInt(cursor.getString(cursor.getColumnIndex("picture"))));
        bookname.setText(cursor.getString(cursor.getColumnIndex("name")));
        price.setText("￥" + cursor.getString(cursor.getColumnIndex("price")));

       /* btn_gw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingDao shoppingDao = new ShoppingDao();
                Shopping shopping = new Shopping();
                shopping.setBook_id_id(cursor.getLong(cursor.getColumnIndex("id")));
                shopping.setSuser_id((long) 2);
                shopping.setNumber("1");
                shoppingDao.insert(shopping);
            }
        });
        btn_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectDao collectDao = new CollectDao();
                Collect collect = new Collect();
                collect.setSuser_id((long) 2);
                collect.setBook_id(cursor.getLong(cursor.getColumnIndex("id")));
                collectDao.insert(collect);



            }
        });*/
    }
}





