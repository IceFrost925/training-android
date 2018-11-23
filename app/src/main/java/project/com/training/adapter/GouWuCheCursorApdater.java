package project.com.training.adapter;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import project.com.training.R;


public class GouWuCheCursorApdater extends CursorAdapter {
//   private TextView all;
//   private TextView sum;
//   private TextView num;
//   private TextView price;
//   private TextView jhi_number;
//   private TextView name;
//   private ImageView picture;

   private LayoutInflater layoutInflater;
    public GouWuCheCursorApdater(Context context, Cursor c) {
        super(context, c, 0);
    }
    public final class Content{

        public TextView num;
        public TextView price;
        private TextView jhi_number;
        private TextView name;
        //private ImageView picture;


    }
    Content content = new Content();
    // 加载ListView的每个子项item_student.xml
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view= LayoutInflater.from(context).inflate(R.layout.gouwuche_item, viewGroup, false);






        return view;
    }

    // 绑定cursor
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // 1. 根据view获取item_student.xml中的每个控件
        Content content=null;
        if(view==null) {
            content = new Content();
           // view=layoutInflater.inflate(R.layout.gouwuche_item, null);
          //  content.picture = view.findViewById(R.id.picture);
            content.name = view.findViewById(R.id.name);
            content.price = view.findViewById(R.id.price);
            content.num = view.findViewById(R.id.num);
            content.jhi_number = view.findViewById(R.id.number);
            view.setTag(content);
        }else{
            content=(Content) view.getTag();
        }

        //content.jhi_number.setText(cursor.getString(cursor.getColumnIndex("jhi_number")));
        content.name.setText(cursor.getString(cursor.getColumnIndex("name")));
        content.price.setText(cursor.getString(cursor.getColumnIndex("price")));
        content.num.setText(cursor.getString(cursor.getColumnIndex("number")));
        content.jhi_number.setText(cursor.getString(cursor.getColumnIndex("jhi_number")));

        final TextView all = view.findViewById(R.id.all);
        final TextView num=view.findViewById(R.id.num);
        final TextView price = view.findViewById(R.id.price);
        TextView sum = view.findViewById(R.id.sum);
        all.setText(String.valueOf(Integer.parseInt(price.getText().toString()) * Integer.parseInt(num.getText().toString())));

        Button add = view.findViewById(R.id.add);
        Button del = view.findViewById(R.id.del);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.setText(String.valueOf(Integer.parseInt(num.getText().toString()) + 1));
                all.setText(String.valueOf(Integer.parseInt(price.getText().toString()) * Integer.parseInt(num.getText().toString())));
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(num.getText().toString()) > 1) {
                    num.setText(String.valueOf(Integer.parseInt(num.getText().toString()) - 1));
                    all.setText(String.valueOf(Integer.parseInt(price.getText().toString()) * Integer.parseInt(num.getText().toString())));
                }
            }
        });




    }
}
