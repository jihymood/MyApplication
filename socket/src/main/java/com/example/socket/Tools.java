package com.example.socket;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class Tools {
    public static String getServerData(String str) {

        String acceptStr = null;
        try {
            //客户端发给服务端的数据
            InetAddress serverAddr = InetAddress.getByName(IAcceptServerData.SERVERIP);
            Log.d("Tools", "serverAddr:" + serverAddr);
            DatagramSocket socket = new DatagramSocket();
            Log.d("Tools", "socket:" + socket);
            byte[] buf = str.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, IAcceptServerData.SERVERPORT);
            socket.send(packet);

            //获得从服务端发来的数据
            byte[] buffer = new byte[1024];
            DatagramPacket recvPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(recvPacket);
            InetAddress ad = recvPacket.getAddress();
            String s = ad.getHostAddress();
            acceptStr = new String(recvPacket.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acceptStr;

    }
}
