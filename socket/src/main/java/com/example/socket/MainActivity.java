package com.example.socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * udp心跳包对应eclipse中的tcpServer1服务端项目
 */

public class MainActivity extends AppCompatActivity implements IAcceptServerData{

    private boolean start;
    private String temp = null; // 温度
    private TextView ttt;

    private Handler MyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IAcceptServerData.temp:
                    temp = (String) msg.obj; //msg带来的数据

                    ttt.append(temp);

                    android.util.Log.d("mark", "temp=" + temp);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ttt = (TextView) findViewById(R.id.ttt);

        new Thread(new AcceptThread("1", IAcceptServerData.temp)).start();

    }



    public class AcceptThread implements Runnable {
        private String str;
        private int id;

        public AcceptThread(String str, int id) {
            this.str = str;
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000); //休眠3秒请求一次服务端的数据
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String temp = Tools.getServerData(str);//获得从服务端发来的数据
                acceptUdpData(temp, id);//将temp数据更新到ui上（通过handler更新）
            }
        }
    }

    @Override
    public void acceptUdpData(String data, int id) {
        Message msg = new Message();
        switch (id) {
            case IAcceptServerData.temp:
                msg.what = IAcceptServerData.temp;
                break;

            default:
                break;
        }
        msg.obj = data;
        MyHandler.sendMessage(msg);

    }

    private void showToast(String msg) {
        if (null == msg) msg = "";
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
