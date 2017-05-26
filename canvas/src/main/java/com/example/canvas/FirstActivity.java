package com.example.canvas;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private final static int MAX_PAGE = 5; // 最多加载5页
    private final static int RESULT = 20; // 每次加载20条
    private final static int MESSAGE_NEWS = 0x1;

    private View footerView;
    private ListView mListView;
    private BaseAdapter mAdapter;
    private boolean isLoadFinish = true;  // 标记是否加载完成
    private List<String> news;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEWS:
                    mAdapter.notifyDataSetChanged();
                    mListView.removeFooterView(footerView);
                    isLoadFinish=true;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        footerView = LayoutInflater.from(FirstActivity.this).inflate(R.layout.footer_news, null);

        intiData();
        mAdapter = new ArrayAdapter<String>(this, R.layout.adapter_item_news, R.id.textView, news);
        mListView.addFooterView(footerView);
        mListView.setAdapter(mAdapter);
        mListView.removeFooterView(footerView);  //  第一次加载不需要 footerView
        mListView.setOnScrollListener(new NewsScrollListener());
    }


    private void intiData() {
        news = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            news.add("天大地大：何处是我家" + i);
        }
    }

    private class NewsScrollListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            /*
            * scrollState 有三种状态
            * SCROLL_STATE_IDLE  停止滚动
            * SCROLL_STATE_TOUCH_SCROLL  正在滚动
            * SCROLL_STATE_FLING  开始滚动
            * */
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int lastVisibleItem = mListView.getLastVisiblePosition();
            if (lastVisibleItem + 1 == totalItemCount) {  // 最后一个可见的条目等于总数
                if (totalItemCount <= 0) {
                    return;
                }
                int currentPage = totalItemCount % RESULT == 0 ? totalItemCount / RESULT : totalItemCount / RESULT + 1;
                if (currentPage < MAX_PAGE && isLoadFinish) {  // 上一次加载加载完成后才重新加载
                    mListView.addFooterView(footerView);
                    isLoadFinish = false;
                    new NewsThread().start();  // 加载数据，可能用在网络加载，因此使用异步方式
                }
            }
        }
    }

    private class NewsThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> newsLoads = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                newsLoads.add("我是加载更多的数据"+i);
            }
            for (String newsLoad : newsLoads) {
                news.add(newsLoad);
            }
            Message message = mHandler.obtainMessage();
            message.obj=news;
            message.what = MESSAGE_NEWS;
            mHandler.sendMessage(message);
        }
    }
}
