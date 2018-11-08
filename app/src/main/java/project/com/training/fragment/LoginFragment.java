package project.com.training.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import project.com.training.R;


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

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @OnClick({R.id.savePassword, R.id.forgetPassword, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.savePassword:
                break;
            case R.id.forgetPassword:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
