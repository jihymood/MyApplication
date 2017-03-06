package com.xpro.test1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.xutils.x;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huangjh on 2017/3/6 0006 11:11
 * Email：huangjihy@163.com
 * 拍照上传：先拍照存储在本地，显示出来，然后调用接口将所有图片上传
 */
public class TakePhotoActivity extends AppCompatActivity {


    private static final int PIC_TAKEPHOTO = 0x11;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.scrollView)
    HorizontalScrollView scrollView;
    @Bind(R.id.linear_layout)
    LinearLayout linearLayout;
    @Bind(R.id.shangchuan)
    Button shangchuan;

    private File file;
    private String fileName;
    private Uri imageUri;
    private String path;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PIC_TAKEPHOTO:
                if (resultCode == RESULT_OK) {
                    showPhoto();
                }
                break;
        }
    }

    public void showPhoto() {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 120);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.setMargins(10, 10, 10, 10);
        imageView.setLayoutParams(params);
        x.image().bind(imageView, path);
        linearLayout.addView(imageView);

    }

    @OnClick({R.id.button, R.id.linear_layout,R.id.shangchuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                fileName = System.currentTimeMillis() + ".jpg";
                file = new File(Environment.getExternalStorageDirectory(), fileName);

                path = file.getAbsolutePath();
                Log.e("TakePhotoActivity", path);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(file);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, PIC_TAKEPHOTO);
                break;
            case R.id.shangchuan:

                break;
        }
    }

}
