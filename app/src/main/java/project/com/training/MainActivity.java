package project.com.training;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btn_login;
    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_login);
        userName = findViewById(R.id.ev_useName);
        password = findViewById(R.id.ev_password);

        Drawable drawable_user = getResources().getDrawable(R.drawable.ico_user);
        drawable_user.setBounds(0, 0, 50, 50);
        userName.setCompoundDrawables(drawable_user, null, null, null);

        Drawable drawable_password = getResources().getDrawable(R.drawable.ico_password);
        drawable_password.setBounds(0, 0, 50, 50);
        password.setCompoundDrawables(drawable_password, null, null, null);

    }
}
