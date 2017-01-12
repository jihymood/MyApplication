package com.example.socket;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public interface IAcceptServerData {
    //    public static final String SERVERIP = "192.168.0.124"; // 广播地址
//    public static final String SERVERIP = "192.168.191.5"; // 广播地址
    public static final String SERVERIP = "192.168.0.103"; // 广播地址
    public static final int SERVERPORT = 18081; // 端口号

    public static final int temp = 1;

    public void acceptUdpData(String data, int id);
}
