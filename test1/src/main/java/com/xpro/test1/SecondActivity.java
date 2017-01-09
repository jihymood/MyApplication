package com.xpro.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.buttton)
    Button buttton;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

    }

    public String getLocalMacAddress() {
        String Mac = null;
        try {

            String path = "sys/class/net/wlan0/address";
            if ((new File(path)).exists()) {
                FileInputStream fis = new FileInputStream(path);
                byte[] buffer = new byte[8192];
                int byteCount = fis.read(buffer);
                if (byteCount > 0) {
                    Mac = new String(buffer, 0, byteCount, "utf-8");
                }
            }
            if (Mac == null || Mac.length() == 0) {
                path = "sys/class/net/eth0/address";
                FileInputStream fis_name = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis_name.read(buffer_name);
                if (byteCount_name > 0) {
                    Mac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
            }
            if (Mac == null || Mac.length() == 0) {
                return "";
            }
        } catch (Exception io) {
            return "";
        }
        return Mac.trim();
    }

    @OnClick(R.id.buttton)
    public void onClick() {
        String macStr = getLocalMacAddress();
        text.setText(macStr);
    }
}
