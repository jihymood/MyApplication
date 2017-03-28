package com.example.fileread;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.fileread.R.raw.bus;

/**
 * 将raw目录下的文件读取到SD卡中
 */
public class MainActivity extends AppCompatActivity {

    private InputStream fis;
    private FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        read();
        read1();
    }

    public void read() {
        Toast.makeText(MainActivity.this, "开始创建文件", Toast.LENGTH_SHORT).show();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String dirPath = Environment.getExternalStorageDirectory().getPath();
            String fileName = "bus.txt";
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file1 = new File(dirPath + "/" + fileName);
            try {
                if (!file1.exists()) {
                    fis = getResources().openRawResource(bus);
                    fos = new FileOutputStream(file1);
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = fis.read(bytes, 0, bytes.length)) != -1) {
                        fos.write(bytes, 0, len);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    fos.close();
                    Toast.makeText(MainActivity.this, "创建文件成功",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public void read1() {
        String path = Environment.getExternalStorageDirectory() + "/WeiPics" + "bus.txt";
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();

                fis = getResources().openRawResource(R.raw.bus);
                fos = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = fis.read(buf, 0, buf.length)) != -1) {
                    fos.write(buf, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                Toast.makeText(MainActivity.this, "创建文件成功",
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
