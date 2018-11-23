package project.com.training.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import project.com.training.HomeActivity;
import project.com.training.R;
import project.com.training.fragment.ShouYeFragment;

public class ShouCangAdapter extends BaseAdapter implements View.OnClickListener{
    //数据
    private List<Map<String, String>> data;
    //添加布局
    private LayoutInflater layoutInflater;
    private MyClickListener mListener;//所有listview的item共用同一个
    private Context context;

    public ShouCangAdapter(Context context, List<Map<String, String>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    public ShouCangAdapter(Context context,List<Map<String, String>> data,MyClickListener listener){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.clickListener(v);
    }
    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(View v);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Content{
        public ImageView picture,add_gw,del;
        public TextView name;
        //public Button view;
        public TextView count;//库存
        public TextView jhi_number;//型号
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
            convertView=layoutInflater.inflate(R.layout.sc_item,null);

           content.picture=convertView.findViewById(R.id.picture);
            content.name=convertView.findViewById(R.id.name);
            content.price=convertView.findViewById(R.id.price);
            content.count=convertView.findViewById(R.id.kucun);
            content.jhi_number=convertView.findViewById(R.id.number);
            content.add_gw=convertView.findViewById(R.id.add_gw);
            content.del=convertView.findViewById(R.id.del);





            convertView.setTag(content);
        }else{
            content=(Content) convertView.getTag();
        }
        //绑定数据
//        content.picture.setBackgroundResource(Integer.parseInt(data.get(position).get("picture")));
        content.name.setText((String)data.get(position).get("name"));
        content.price.setText((String)data.get(position).get("price"));
        content.jhi_number.setText((String) data.get(position).get("jhi_number"));
        content.count.setText((String) data.get(position).get("count"));

        content.add_gw.setOnClickListener(this);
        content.add_gw.setTag(position);

        content.del.setOnClickListener(this);
        content.del.setTag(position);


        return convertView;
    }

}
