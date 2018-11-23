package project.com.training;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class saveDBFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        saveDBFile("training.db");
    }
    //将assets目录下的db文件写入/data/data/包名/database/目录
    private  void saveDBFile(String dbName) {
        String destPath = "/data" + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases";

        File filePath = new File(destPath);
        if (!filePath.exists()) {
            filePath.mkdirs();//创建文件
        }
        //创建目标目录文的件
        File file = new File(destPath, dbName);
        try {
            //创建输入，输出对象流
            InputStream input = this.getAssets().open(dbName);
            FileOutputStream output = new FileOutputStream(file);
            //将输入流的数据写入输出流
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
