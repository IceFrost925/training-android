package project.com.training;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button loginKey;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginKey = findViewById(R.id.btn_login);
        userName = findViewById(R.id.ev_useName);
        password = findViewById(R.id.ev_password);

        Drawable drawable_user = getResources().getDrawable(R.drawable.ico_user);
        drawable_user.setBounds(0, 0, 50, 50);
        userName.setCompoundDrawables(drawable_user, null, null, null);

        Drawable drawable_password = getResources().getDrawable(R.drawable.ico_password);
        drawable_password.setBounds(0, 0, 50, 50);
        password.setCompoundDrawables(drawable_password, null, null, null);


        loginKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= userName.getText().toString();
                String password0= password.getText().toString();
                if(name.equals("1602343108") && password0.equals("123456")){
                    Intent instant = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(instant);
                }else{
                    Toast.makeText(MainActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
