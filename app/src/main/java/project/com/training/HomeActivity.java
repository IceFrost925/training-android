package project.com.training;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.com.training.adapter.HomePagerAdapter;


public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.yonghu)
    ImageView yonghu;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.shouchang)
    ImageView shouchang;
    @BindView(R.id.gouwuce)
    ImageView gouwuce;
    @BindView(R.id.fenxiang)
    ImageView fenxiang;
    @BindView(R.id.spinner_gwc)
    Spinner spinnerGwc;
    @BindView(R.id.spinner_caidan)
    Spinner spinnerCaidan;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.list_item)
    ListView listItem;
    @BindView(R.id.list_item2)
    FrameLayout listItem2;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.kefu)
    TextView kefu;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.center)
    TextView center;
    @BindView(R.id.tell)
    TextView tell;
   //菜单适配器

    private List<View> list;
    private HomePagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initdata();

        //*************************菜单适配器设置****************************************
        //这里的ArrayAdapter的第二个参数是Spinner没有下拉时的布局
        //第三个参数是布局文件中TextView或者CheckedTextView的id
        ArrayAdapter< String > myAdapter = new ArrayAdapter<String >(HomeActivity.this, R.layout.spinner_item,R.id.text) ;
        //下拉框每一条的布局样式
        myAdapter.setDropDownViewResource( R.layout.item_list);

        //这里是Xml文件的资源，添加进Adapter
        final String level[] = getResources().getStringArray(R.array.caidan);//资源文件
        for (int i = 0; i < level.length; i++) {
            myAdapter.add(level[i]);
        }

        //为Spinner添加适配器
        spinnerCaidan.setAdapter(myAdapter);

    }

    private void initdata() {
        List<View> list=new ArrayList<>();
        LayoutInflater lf=getLayoutInflater();
        list.add(lf.inflate(R.layout.viewpage1_home,null));
        list.add(lf.inflate(R.layout.viewpage2_home,null));
        HomePagerAdapter pagerAdapter=new HomePagerAdapter(list);
        viewPage.setAdapter(pagerAdapter);
    }


    //菜单点击事件
    @OnClick(R.id.spinner_caidan)
    public void onClick() {

    }
}
