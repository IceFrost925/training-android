package project.com.training;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.com.training.adapter.HomePagerAdapter;
import project.com.training.fragment.BookDmFragment;
import project.com.training.fragment.KeXueFragment;
import project.com.training.fragment.LoginFragment;
import project.com.training.fragment.RegisterFragment;
import project.com.training.fragment.ShouYeFragment;
import project.com.training.fragment.WenXueFragment;


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


    @BindView(R.id.frame_content)
    FrameLayout frame_content;


    private SparseArray<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initViewPage();


        //*************************菜单适配器设置****************************************
        //这里的ArrayAdapter的第二个参数是Spinner没有下拉时的布局
        //第三个参数是布局文件中TextView或者CheckedTextView的id
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HomeActivity.this, R.layout.spinner_item, R.id.text);
        //下拉框每一条的布局样式
        myAdapter.setDropDownViewResource(R.layout.item_list);

        //这里是Xml文件的资源，添加进Adapter
        final String level[] = getResources().getStringArray(R.array.caidan_array);//资源文件
        for (int i = 0; i < level.length; i++) {
            myAdapter.add(level[i]);
        }

        //为Spinner添加适配器
        spinnerCaidan.setAdapter(myAdapter);

        //1.加载fragment
        fragments = new SparseArray<>();
        fragments.put(R.id.login, new LoginFragment());
        fragments.put(R.id.register, new RegisterFragment());

        //2.FragmentManager装载Fragment
        replaceFragment(fragments.get(R.id.login));

        spinnerCaidan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    fragments.put((int) id, new ShouYeFragment());
                }
                if (position == 1) {
                    fragments.put((int) id, new ShouYeFragment());
                }
                if (position == 2) {
                    fragments.put((int) id, new BookDmFragment());
                }
                if (position == 3) {
                    fragments.put((int) id, new WenXueFragment());
                }
                if (position == 4) {
                    fragments.put((int) id, new KeXueFragment());
                }


                replaceFragment(fragments.get((int) id));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }



    //方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }

    private void initViewPage() {
        List<View> list=new ArrayList<>();
        LayoutInflater lf=getLayoutInflater();

        list.add(lf.inflate(R.layout.viewpage1_home,null));
        list.add(lf.inflate(R.layout.viewpage2_home,null));


        HomePagerAdapter pagerAdapter=new HomePagerAdapter(list);
        viewPage.setAdapter(pagerAdapter);
        //dotLayout.findViewById(R.id.layout_point);

    }
    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
               replaceFragment(fragments.get(R.id.login));
                break;
            case R.id.register:
                replaceFragment(fragments.get(R.id.register));
                break;
        }
    }
}
