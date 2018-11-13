package project.com.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);



       /* loginKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= userName.getText().toString();
                String password0= password.getText().toString();
                if(name.equals("1602343108") && password0.equals("123456")){
                    //Intent instant = new Intent(MainActivity.this,Main2Activity.class);
                   // startActivity(instant);
                }else{
                    Toast.makeText(MainActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
                }


            }
        });*/
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        String name= userName.getText().toString();
        String psd=password.getText().toString();
        if(name.equals("yangrong") && (psd.equals("123"))){
            Intent instant = new Intent(MainActivity.this,HomeActivity.class);

            startActivity(instant);
        }else{
            Toast.makeText(MainActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
        }
    }
}
