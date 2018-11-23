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

import java.util.List;
import java.util.Map;

import project.com.training.R;

public class GouWuCheAdapter extends BaseAdapter implements View.OnClickListener{
    //数据
  //  private List<Map<String, Object>> data;
    private List<Map<String,String>> data;
    private MyClickListener mListener;
    //添加布局
    private LayoutInflater layoutInflater;

    private Context context;
    /*public GouWuCheAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }*/
    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(View v);
    }

    public GouWuCheAdapter(Context context,List<Map<String, String>> data,MyClickListener listener){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mListener=listener;
    }
    public GouWuCheAdapter(Context context, List<Map<String,String>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public void onClick(View v) {
        mListener.clickListener(v);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Content{
        public ImageView picture;
        public TextView name;
        //public Button view;
        public TextView num;//数量
        public TextView jhi_number;//型号
        public TextView price;

        private Button add,del;


    }
    Content content = new Content();
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
            convertView=layoutInflater.inflate(R.layout.gouwuche_item, null);
           content.picture=convertView.findViewById(R.id.picture);
            content.name=convertView.findViewById(R.id.name);
            content.price=convertView.findViewById(R.id.price);
           content.num=convertView.findViewById(R.id.num);
            content.jhi_number=convertView.findViewById(R.id.number);
            content.add=convertView.findViewById(R.id.add);
            content.del=convertView.findViewById(R.id.del);

            convertView.setTag(content);
        }else{
            content=(Content) convertView.getTag();
        }
        //绑定数据
        //content.picture.setBackgroundResource(Integer.parseInt(data.get(position).get("picture")));
        content.name.setText((String)data.get(position).get("name"));
        content.price.setText((String)data.get(position).get("price"));
        content.num.setText((String)data.get(position).get("number"));//editText
        content.jhi_number.setText((String)data.get(position).get("jhi_number"));

/*
        content.picture.setBackgroundResource((Integer) data.get(position));
        content.name.setText((String)data.get(position));
        content.price.setText((Integer) data.get(position));
        content.num.setText((Integer) data.get(position));//editText
        content.jhi_number.setText((String)data.get(position));
*/
        final TextView all=convertView.findViewById(R.id.all);
        final TextView num=convertView.findViewById(R.id.num);
        final TextView price=convertView.findViewById(R.id.price);
        TextView sum=convertView.findViewById(R.id.sum);


        all.setText(String.valueOf(Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString())));




        content.add.setOnClickListener(this);
        content.add.setTag(position);
        content.del.setOnClickListener(this);
        content.del.setTag(position);

        return convertView;
    }

}
