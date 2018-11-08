package project.com.training.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import project.com.training.R;

public class ShouYeAdapter extends BaseAdapter {
    //数据
    private List<Map<String, Object>> data;
    //添加布局
    private LayoutInflater layoutInflater;

    private Context context;
    public ShouYeAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Content{
        public ImageView image;
        public TextView bookname;
        //public Button view;
        public TextView price;

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
           content.image=convertView.findViewById(R.id.image);
            content.bookname=convertView.findViewById(R.id.bookname);
            content.price=convertView.findViewById(R.id.price);

            convertView.setTag(content);
        }else{
            content=(Content) convertView.getTag();
        }
        //绑定数据
        content.image.setBackgroundResource((Integer)data.get(position).get("image"));
        content.bookname.setText((String)data.get(position).get("bookname"));
        content.price.setText((String)data.get(position).get("price"));

        return convertView;
    }

}
