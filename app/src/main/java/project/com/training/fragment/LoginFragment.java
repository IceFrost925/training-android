package project.com.training.fragment;


import android.app.Fragment;
import android.content.Context;
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

    String name, pwd;
    User user;
    private Context mContext;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();//初始化context，解决Toast空指针异常
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


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO: http request.
            //Now now = weatherRequest.getNow("https://free-api.heweather.com/s6/weather", city_name);
            BookRequest bookRequest = new BookRequest(mContext);
            try {
                user = bookRequest.Login("http://118.25.132.164:8080/training-0.0.1-SNAPSHOT/api/permit/suser/login",name, pwd);
                Log.d("user",user.getUsername());
                if (user == null) {//在子线程中弹出Toast，会报错：java.lang.RuntimeException: Can't toast on a thread that has not called Looper.prepare()。
                    Looper.prepare();
                    Toast.makeText(mContext, "登录失败！请重新输入。", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(mContext, "登录成功！", Toast.LENGTH_SHORT).show();
                    //HomeActivity homeActivity = new HomeActivity();
                    Looper.loop();

                }
                Message msg = new Message();
                Bundle data = new Bundle();
                //data.putSerializable("value", now);
                data.putSerializable("user", (Serializable) user);

                msg.setData(data);
                Handler handler = new Handler();
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    };

    @OnClick(R.id.btn_login)
    public void onClick() {
        Log.d("gg","uuuuu");
        name = userName.getText().toString();
        pwd = password.getText().toString();
        new Thread(runnable).start();
    }


   /* */
}

