package project.com.training;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.com.training.adapter.ViewPagerAdapter;
import project.com.training.fragment.BookDMFragment;
import project.com.training.fragment.GouwucheFragment;
import project.com.training.fragment.KeXueFragment;
import project.com.training.fragment.LoginFragment;
import project.com.training.fragment.RegisterFragment;
import project.com.training.fragment.ShouCangFragment;
import project.com.training.fragment.ShouYeFragment;
import project.com.training.fragment.WenXueFragment;
import project.com.training.model.User;
import project.com.training.request.BookRequest;


public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.yonghu)
    ImageView yonghu;


    @BindView(R.id.fenxiang)
    ImageView fenxiang;

    @BindView(R.id.spinner_caidan)
    Spinner spinnerCaidan;
    @BindView(R.id.view_page)
    ViewPager viewPage;


    @BindView(R.id.frame_content)
    FrameLayout frame_content;
    @BindView(R.id.username)
    TextView username;
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
    @BindView(R.id.gouwuche)
    ImageView gouwuche;
    @BindView(R.id.beijing)
    ImageView beijing;
    @BindView(R.id.tv_pager_title)
    TextView tvPagerTitle;
    @BindView(R.id.lineLayout_dot)
    LinearLayout lineLayoutDot;
    @BindView(R.id.shoucang)
    ImageView shoucang;

    private User user;


    private SparseArray<Fragment> fragments;

    //轮播
    private List<ImageView> mImageList;//轮播的图片集合
    private String[] mImageTitles;//标题集合
    private int previousPosition = 0;//前一个被选中的position
    private List<View> mDots;//小点

    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 5000;//间隔时间

    // 在values文件假下创建了pager_image_ids.xml文件，并定义了4张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.pager_image1, R.id.pager_image2};

//轮播

    public HomeActivity(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //initViewPage();
        // addImageView();
        init();





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
        fragments.put(R.id.shoucang, new ShouCangFragment());
        fragments.put(R.id.gouwuche, new GouwucheFragment());
        if(spinnerCaidan.getSelectedItem().toString()=="首页"){
            fragments.put(Integer.parseInt(spinnerCaidan.getSelectedItem().toString()), new  ShouYeFragment());
            //2.FragmentManager装载Fragment
            replaceFragment(fragments.get(Integer.parseInt(spinnerCaidan.getSelectedItem().toString())));

        }

        spinnerCaidan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               //if (position == 0) {
                    //fragments.put((int) id, new ShouYeFragment());
               // }
                if (position == 0) {
                    fragments.put((int) id, new ShouYeFragment());
                }
                if (position == 1) {
                    fragments.put((int) id, new BookDMFragment());
                }
                if (position == 2) {
                    fragments.put((int) id, new WenXueFragment());
                }
                if (position == 3) {
                    fragments.put((int) id, new KeXueFragment());
                }


                replaceFragment(fragments.get((int) id));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        BookRequest bookRequest= new BookRequest(HomeActivity.this);



    }


    //方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commit();
    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
             user = (User) data.getSerializable("user");
             if(user.getUsername().toString()==""){
                 username.setText(user.getEmail().toString());
             }else{
                 username.setText(user.getUsername().toString());
             }


            //futuresList = (List<Futures>) data.getSerializable("futures");
            //initHomeInfo();
            //initViewPage();
        }
    };




    /**
     * 第一步、初始化控件
     */
    public void init() {

        initData();//初始化数据
        initView();//初始化View，设置适配器
        autoPlayView();//开启线程，自动播放
    }

    /**
     * 第二步、初始化数据（图片、标题、点击事件）
     */
    public void initData() {
        //初始化标题列表和图片
        mImageTitles = new String[]{"让优秀成为一种习惯", "勤能补拙，孰能生巧"};
        int[] imageRess = new int[]{R.drawable.lunbo1, R.drawable.lunbo2};

        //添加图片到图片列表里
        mImageList = new ArrayList<>();
        ImageView iv;
        for (int i = 0; i < imageRess.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageRess[i]);//设置图片
            iv.setId(imgae_ids[i]);//顺便给图片设置id
            iv.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            mImageList.add(iv);
        }
        //添加轮播点

        mDots = addDots(lineLayoutDot, fromResToDrawable(this, R.drawable.ic_dot_normal), mImageList.size());//其中fromResToDrawable()方法是我自定义的，目的是将资源文件转成Drawable


    }

    //图片点击事件
    private class pagerImageOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pager_image1:
                    Toast.makeText(HomeActivity.this, "图片1被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image2:
                    Toast.makeText(HomeActivity.this, "图片2被点击", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    }

    /**
     * 第三步、给PagerViw设置适配器，并实现自动轮播功能
     */
    public void initView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mImageList, viewPage);
        viewPage.setAdapter(viewPagerAdapter);
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //伪无限循环，滑到最后一张图片又从新进入第一张图片

                if (mImageList.size() != 0) {


                    int newPosition = position % mImageList.size();
                    // 把当前选中的点给切换了, 还有描述信息也切换
                    tvPagerTitle.setText(mImageTitles[newPosition]);//图片下面设置显示文本
                    //设置轮播点
                    LinearLayout.LayoutParams newDotParams = (LinearLayout.LayoutParams) mDots.get(newPosition).getLayoutParams();
                    newDotParams.width = 24;
                    newDotParams.height = 24;

                    LinearLayout.LayoutParams oldDotParams = (LinearLayout.LayoutParams) mDots.get(previousPosition).getLayoutParams();
                    oldDotParams.width = 16;
                    oldDotParams.height = 16;

                    // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                    previousPosition = newPosition;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setFirstLocation();
    }

    /**
     * 第四步：设置刚打开app时显示的图片和文字
     */
    private void setFirstLocation() {
        tvPagerTitle.setText(mImageTitles[previousPosition]);
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;

        try {
            int m = (Integer.MAX_VALUE / 2) % mImageList.size();
            int currentPosition = Integer.MAX_VALUE / 2 - m;
            viewPage.setCurrentItem(currentPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 第五步: 设置自动播放,每隔PAGER_TIOME秒换一张图片
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                viewPage.setCurrentItem(viewPage.getCurrentItem() + 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    SystemClock.sleep(PAGER_TIOME);
                }
            }
        }).start();
    }


    /**
     * 资源图片转Drawable
     *
     * @param context
     * @param resId
     * @return
     */
    public Drawable fromResToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }


    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return
     */
    public int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            dot.setBackground(backgount);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                dot.setId(View.generateViewId());
            }
        }

        linearLayout.addView(dot);
        return dot.getId();
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout
     * @param backgount
     * @param number
     * @return
     */
    public List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout, backgount);
            dots.add(findViewById(dotId));
        }
        return dots;
    }

   /* private void initViewPage() {
        List<View> list = new ArrayList<>();
        LayoutInflater lf = getLayoutInflater();

        list.add(lf.inflate(R.layout.viewpage1_home, null));
        list.add(lf.inflate(R.layout.viewpage2_home, null));


        HomePagerAdapter pagerAdapter = new HomePagerAdapter(list);
        viewPage.setAdapter(pagerAdapter);
        //dotLayout.findViewById(R.id.layout_point);

    }
   private void addImageView(){
       List<ImageView> mItems = new ArrayList<>();
       ImageView view0 = new ImageView(this);
       view0.setImageResource(R.drawable.lunbo1);
       ImageView view1 = new ImageView(this);
       view1.setImageResource(R.drawable.lunbo2);

       view0.setScaleType(ImageView.ScaleType.CENTER_CROP);
       view1.setScaleType(ImageView.ScaleType.CENTER_CROP);


       mItems.add(view0);
       mItems.add(view1);
       MyAdapter pagerAdapter = new MyAdapter(mItems);
       viewPage.setAdapter(pagerAdapter);


   }*/



    @OnClick({R.id.login, R.id.register, R.id.yonghu, R.id.shoucang, R.id.gouwuche, R.id.fenxiang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                replaceFragment(fragments.get(R.id.login));
                break;
            case R.id.register:
                replaceFragment(fragments.get(R.id.register));
                break;
            case R.id.yonghu:
                break;
            case R.id.shoucang:
                replaceFragment(fragments.get(R.id.shoucang));
                break;
            case R.id.gouwuche:
                replaceFragment(fragments.get(R.id.gouwuche));
                break;
            case R.id.fenxiang:
                break;
        }
    }
}
