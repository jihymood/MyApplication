package com.xpro.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_75;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class BaseActivity extends AppCompatActivity {

    public String addStr;
    private String removeStr;
    private WebSocketClient client;// 连接客户端
    private DraftInfo selectDraft;// 连接协议
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DraftInfo[] draftInfos = {new DraftInfo("WebSocket协议Draft_17", new Draft_17()), new DraftInfo("WebSocket协议Draft_10", new Draft_10()), new DraftInfo("WebSocket协议Draft_76", new Draft_76()), new DraftInfo("WebSocket协议Draft_75", new Draft_75())};// 所有连接协议
        selectDraft = draftInfos[0];// 默认选择第一个连接协议
        ServerInfo[] serverInfos = {new ServerInfo("连接Java Web后台", "ws://192.168.0.171:8080/EBUS/webSocketServer?personId=25256"), new ServerInfo("连接Java Application后台", "ws://192.168.1" + "" +
                ".104:8887")};// 所有连接后台
        address = serverInfos[0].serverAddress;
        Log.e("wlf", "连接地址：" + address);

//        connectSocket();

        WebSocketImpl.DEBUG = true;
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("java.net.preferIPv4Stack", "true");

    }

    /**
     * 如果这里是private方法，继承BaseActivity的类无法使用该方法
     */
    public void connectSocket() {
        try {
            client = new WebSocketClient(new URI(address), selectDraft.draft) {
                @Override
                public void onOpen(final ServerHandshake serverHandshakeData) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            etDetails.append("已经连接到服务器【" + getURI() + "】\n");

                            Log.e("wlf", "已经连接到服务器【" + getURI() + "】");
                        }
                    });
                }

                @Override
                public void onMessage(final String message) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            etDetails.append("获取到服务器信息【" + message + "】\n");

                            Log.e("wlf", "获取到服务器信息【" + message + "】");
                        }
                    });
                }

                @Override
                public void onClose(final int code, final String reason, final boolean remote) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            etDetails.append("断开服务器连接【" + getURI() + "，状态码： " + code + "，断开原因：" + reason +
//                                    "】\n");

                            Log.e("wlf", "断开服务器连接【" + getURI() + "，状态码： " + code + "，断开原因：" + reason + "】");
                        }
                    });
                }

                @Override
                public void onError(final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            etDetails.append("连接发生了异常【异常原因：" + e + "】\n");

                            Log.e("wlf", "连接发生了异常【异常原因：" + e + "】");
                        }
                    });
                }
            };
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSocket() {
        if (client != null) {
            client.close();
        }
    }

    public class DraftInfo {

        public final String draftName;
        public final Draft draft;

        public DraftInfo(String draftName, Draft draft) {
            this.draftName = draftName;
            this.draft = draft;
        }

        @Override
        public String toString() {
            return draftName;
        }
    }

    /**
     * 如果这里是private方法，继承BaseActivity的类无法使用该方法
     */
    public class ServerInfo {

        private final String serverName;
        private final String serverAddress;

        public ServerInfo(String serverName, String serverAddress) {
            this.serverName = serverName;
            this.serverAddress = serverAddress;
        }

        @Override
        public String toString() {
            return serverName;
        }
    }

}
