package project.com.training.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import project.com.training.R;
import project.com.training.dao.UserDao;
import project.com.training.model.User;
import project.com.training.request.BookRequest;


public class LoginFragment extends Fragment {


    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.savePassword)
    CheckBox savePassword;
    @BindView(R.id.forgetPassword)
    TextView forgetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;
    private MyListener myListener;//②作为属性定义

    //①定义回调接口
    public interface MyListener{
        public void sendContent(String info);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myListener = (MyListener) getActivity();
    }

    //User user;
   // private Context mContext;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.mContext = getActivity();//初始化context，解决Toast空指针异常
       // btnLogin.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


   /* Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO: http request.
            //Now now = weatherRequest.getNow("https://free-api.heweather.com/s6/weather", city_name);
            //BookRequest bookRequest = new BookRequest(mContext);
            // user = bookRequest.Login("http://118.25.132.164:8080/training-0.0.1-SNAPSHOT/api/permit/suser/login",name, pwd);
            UserDao userDao=new UserDao(getActivity());
            name=userName.getText().toString();
            pwd=password.getText().toString();

            //Cursor cursor=userDao.Login(name,pwd);
            if (!userDao.Login(name,pwd)) {
                Looper.prepare();
                Toast.makeText(mContext, "登录失败！请重新输入。", Toast.LENGTH_SHORT).show();
                //HomeActivity homeActivity = new HomeActivity();
                Looper.loop();

            } else {
            //if (cursor != null) {//在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。
                Looper.prepare();
                Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                Log.d("ggg","------------------------");
                Looper.loop();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            //data.putSerializable("value", now);
           // data.putSerializable("user", (Serializable) cursor);

            msg.setData(data);
            Handler handler = new Handler();
            handler.sendMessage(msg);
        }


    };*/
  /*Runnable runnable = new Runnable() {
       @Override
       public void run() {
           // TODO: http request.
           Message msg = new Message();
           Bundle data = new Bundle();
           //data.putSerializable("value", now);
           String name = userName.getText().toString();
           String pwd = password.getText().toString();
           if(name==""||pwd==""){
               Looper.prepare();
               Toast.makeText(mContext,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
               Looper.loop();
               Log.d("g","-----------用户名或密码不能为空！-------------");

           }else{
               UserDao userDao=new UserDao(mContext);
               User user=userDao.LoginUser(name,pwd);
               if (user.getUsername()!=null&&user.getId()!=null) {
                   Looper.prepare();
                   Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                   Looper.loop();
                   Log.d("gg","-----------登录成功！-------------");
                   //HomeActivity homeActivity = new HomeActivity();
                   //user.setId(user.getId());
                   //user.setUsername(user.getUsername());
                   data.putSerializable("user", (Serializable) user);

               } else {
                   //if (cursor != null) {//在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。
                   Looper.prepare();
                   Toast.makeText(mContext, "登录失败！请重新输入。", Toast.LENGTH_SHORT).show();
                   Looper.loop();

                   Log.d("ggg","------------登录失败！请重新输入------------");



               }
           }


           msg.setData(data);
           Handler handler = new Handler();
           handler.sendMessage(msg);
       }


   };*/

    @OnClick(R.id.btn_login)
    public void onClick() {
        Log.d("gg","uuuuu");
       String name = userName.getText().toString();
       String pwd = password.getText().toString();
        if(name.equals("")||pwd.equals("")){
            Toast.makeText(getActivity(),"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
        }else{
            UserDao userDao=new UserDao(getActivity());
            User user=userDao.LoginUser(name,pwd);
           /// / if (!userDao.Login(name,pwd)) {
            //if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            if(!String.valueOf(user.getId()).equals("")){
                Toast.makeText(getActivity(),"登录成功!",Toast.LENGTH_SHORT).show();
                ShouCangFragment shouCangFragment=new ShouCangFragment(user);
                shouCangFragment = (ShouCangFragment)getActivity()
                        .getFragmentManager()
                        .findFragmentByTag("shouCangFragment");


                Log.d("id", String.valueOf(user.getId()));
                myListener.sendContent(user.getUsername());//将内容进行回传

               // shouCangFragment.setUser(Long.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
               // Log.d("hh",String.valueOf(cursor.getLong(cursor.getColumnIndex("id"))));
                               // .getSupportFragmentManager()
                                //.findFragmentByTag("mainFragment");
               // mainFragment.setData(mDatas.get(position));
                //HomeActivity homeActivity = new HomeActivity();



            } else {
                //if (cursor != null) {//在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。

                Toast.makeText(getActivity(), "登录失败！请重新输入。！", Toast.LENGTH_SHORT).show();
                Log.d("ggg","------------------------");

               // data.putSerializable("user", (Serializable) user);



            }
        }
       //new Thread(runnable).start();

    }



}

