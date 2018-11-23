package project.com.training.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import project.com.training.R;

public class ShouYeAdapter extends BaseAdapter implements View.OnClickListener {

    //数据
   // private List<Map<String, Object>> data;
    private List<Map<String, String>> data;
    //添加布局
    private LayoutInflater layoutInflater;
    private MyClickListener mListener;//所有listview的item共用同一个
    private Context context;

    @Override
    public void onClick(View v) {
        mListener.clickListener(v);
    }

    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(View v);
    }

    public ShouYeAdapter(Context context,List<Map<String, String>> data,MyClickListener listener){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mListener=listener;
    }
    public ShouYeAdapter(Context context,List<Map<String, String>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);;
    }
    public ShouYeAdapter(Context context,LayoutInflater layoutInflater, List<Map<String, String>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=layoutInflater;
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Content{
        public ImageView picture;
        public TextView name;
        //public Button view;
        public TextView price;
        private Button btn_gw;
        private Button btn_sc;
        private TextView book_id;

    }
    /*
     * 获得数据的长度*/
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Content content=null;
        if(convertView==null){
            content=new Content();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.shouye_item, null);
            content.picture=convertView.findViewById(R.id.image);
            content.name=convertView.findViewById(R.id.bookname);
            content.price=convertView.findViewById(R.id.price);
            content.btn_gw=convertView.findViewById(R.id.btn_shopCar);
            content.btn_sc=convertView.findViewById(R.id.btn_recieve);
            content.book_id=convertView.findViewById(R.id.book_id);
            convertView.setTag(content);
        }else{
            content=(Content) convertView.getTag();
        }


        //绑定数据
       // content.picture.setImageResource((Integer) data.get(position).get("picture"));
       // content.picture.setBackgroundResource((Integer)data.get(position).get("picture"));
        content.name.setText((String)data.get(position).get("name"));
        content.price.setText("￥"+(String)data.get(position).get("price"));
        content.book_id.setText(data.get(position).get("bookid"));

        content.btn_gw.setOnClickListener(this);
        content.btn_gw.setTag(position);

        content.btn_sc.setOnClickListener(this);
        content.btn_sc.getTag(position);
        return convertView;
    }

}
