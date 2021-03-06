package com.example.bluetooth;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerActivity extends Activity implements OnClickListener {

    // 视频播放地址videoUrl
    private String videoUrl="http://www.zcycjy.com/coursePath/%E7%BB%A7%E7%BB%AD%E6%95%99%E8%82%B2/%E6%96%B0%E8%AF%BE%E7%A8" +
            "%8B%E8%A7%86%E9%A2%91/%E2%80%9C%E8%90%A5%E6%94%B9%E5%A2%9E%E2%80%9D%E6%94%BF%E7%AD%96%E8%A7%A3%E8%AF%BB%E5%8F%8A%E4%BC%81%E4%B8%9A%E7%A8%8E%E5%8A%A1%E9%A3%8E%E9%99%A9%E4%B8%8E%E7%AD%B9%E5%88%92%E6%8E%A2%E8%AE%A81.mp4";
//    private String videoUrl = "http://192.168.31.162:8080/businspection/userfiles/18f3821417e540f0a1c12a48a022043f/files/20160802162646_13.3gp";
    private SurfaceView video_sv;// 绘图容器对象，用于把视频显示在屏幕上
    private ProgressBar video_pb;
    private LinearLayout video_ll_title;
    private Button video_btn_back;
    private TextView video_tv_name;// 视频名称显示的view
    private LinearLayout video_ll_bottom;
    private Button video_btn_play;// 用于开始和暂停的按钮
    private TextView video_tv_otime;
    private SeekBar video_seekbar_time;// 进度条控件
    private TextView video_tv_ctime;
    private Button video_btn_full;
    private Button video_btn_lock;

    private MediaPlayer mediaPlayer; // 播放器控件
    private upDateSeekBar playingSeekBar; // 更新进度条用
    private int postSize; // 保存义播视频大小
    private boolean flag = true; // 用于判断视频是否在播放中

    private static long lastClickTime;
    private int position = 0;

    private boolean isLocked = false;
    String videopath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /* 去掉title */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /* 设置屏幕常亮 *//* flag：标记 ； */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_player); // 加载布局文件
        initView(); // 初始化数据
        videopath = getIntent().getStringExtra("videopath");
        Log.e("videopath", "videopath:" + videopath);
        playVideo();
        setListener(); // 绑定相关事件

        final List<String> list = new ArrayList<>();
        list.add("http://www.zcycjy.com/coursePath/%E7%BB%A7%E7%BB%AD%E6%95%99%E8%82%B2/%E6%96%B0%E8%AF%BE%E7%A8%8B%E8%A7%86%E9%A2%91/%E2%80%9C%E8%90%A5%E6%94%B9%E5%A2%9E%E2%80%9D%E6%94%BF%E7%AD%96%E8%A7%A3%E8%AF%BB%E5%8F%8A%E4%BC%81%E4%B8%9A%E7%A8%8E%E5%8A%A1%E9%A3%8E%E9%99%A9%E4%B8%8E%E7%AD%B9%E5%88%92%E6%8E%A2%E8%AE%A81.mp4");
        list.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String urlStr : list) {
                    save(urlStr);
                }
            }
        }).start();

    }

    @SuppressWarnings("deprecation")
    private void playVideo() {
        video_pb.setVisibility(View.VISIBLE);
        video_tv_name.setText("视屏名称xxx");// 视频标题
        video_btn_play.setEnabled(false); // 刚进来，设置其不可点击
        video_sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        video_sv.getHolder().setKeepScreenOn(true); // 保持屏幕高亮
        video_sv.getHolder().addCallback(new surFaceView()); // 设置监听事件
        mHandler.sendMessageDelayed(mHandler.obtainMessage(0x124), 3000);// 隐藏控件
    }

    private void initView() {
        video_sv = (SurfaceView) findViewById(R.id.video_sv);
        video_pb = (ProgressBar) findViewById(R.id.video_pb);

        video_ll_title = (LinearLayout) findViewById(R.id.video_ll_title);
        video_btn_back = (Button) findViewById(R.id.video_btn_back);
        video_btn_back.setOnClickListener(this);
        video_tv_name = (TextView) findViewById(R.id.video_tv_name);

        video_ll_bottom = (LinearLayout) findViewById(R.id.video_ll_bottom);
        video_btn_play = (Button) findViewById(R.id.video_btn_play);
        video_btn_play.setOnClickListener(this);
        video_tv_otime = (TextView) findViewById(R.id.video_tv_otime);
        video_seekbar_time = (SeekBar) findViewById(R.id.video_seekbar_time);
        video_tv_ctime = (TextView) findViewById(R.id.video_tv_ctime);
        video_btn_full = (Button) findViewById(R.id.video_btn_full);
        video_btn_full.setOnClickListener(this);

        video_btn_lock = (Button) findViewById(R.id.video_btn_lock);
        video_btn_lock.setOnClickListener(this);

        mediaPlayer = new MediaPlayer(); // 创建一个播放器对象
        playingSeekBar = new upDateSeekBar(); // 创建更新进度条对象
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_btn_back:// 返回
                finish();
                break;
            case R.id.video_btn_lock:
                if (isLocked) {
                    unLockScreen();// 解锁
                    isLocked = false;
                } else {
                    lockScreen();// 锁定
                    isLocked = true;
                }
                break;
            default:
                break;
        }

    }

    private void lockScreen() {
        video_btn_lock
                .setBackgroundResource(R.drawable.player_landscape_screen_off_normal);
        video_ll_title.setVisibility(View.GONE);
        video_ll_bottom.setVisibility(View.GONE);
    }

    private void unLockScreen() {
        video_btn_lock
                .setBackgroundResource(R.drawable.player_landscape_screen_on_noraml);
        video_ll_title.setVisibility(View.VISIBLE);
        video_ll_bottom.setVisibility(View.VISIBLE);
    }

    /**
     * 更新进度条
     */
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x124) {
                video_ll_title.setVisibility(View.GONE);
                video_ll_bottom.setVisibility(View.GONE);
            } else {
                if (mediaPlayer == null) {
                    flag = false;
                } else if (mediaPlayer.isPlaying()) {
                    flag = true;
                    int position = mediaPlayer.getCurrentPosition();
                    int mMax = mediaPlayer.getDuration();
                    int sMax = video_seekbar_time.getMax();
                    if (mMax > 0) {
                        video_tv_otime.setText(change(position / 1000));
                        video_tv_ctime.setText(change(mMax / 1000));
                        video_seekbar_time.setProgress(position * sMax / mMax);
                    } else {
                        Toast.makeText(VideoPlayerActivity.this, "无法播放",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        ;
    };

    class upDateSeekBar implements Runnable {

        @Override
        public void run() {
            mHandler.sendMessage(Message.obtain());
            if (flag) {
                mHandler.postDelayed(playingSeekBar, 1000);
            }
        }
    }

    class PlayMovie extends Thread { // 播放视频的线程
        int post = 0;

        public PlayMovie(int post) {
            this.post = post;
        }

        @Override
        public void run() {
            super.run();
            try {

//                save();

                mediaPlayer.reset(); // 回复播放器默认
//				mediaPlayer.setDataSource(videoUrl); // 设置播放路径
//                mediaPlayer.setDataSource(videopath); // 设置播放路径
                mediaPlayer.setDataSource(pathName); // 设置播放路径
                mediaPlayer.setDisplay(video_sv.getHolder()); // 把视频显示在SurfaceView上
                mediaPlayer.setOnPreparedListener(new PreparedListener(post)); // 设置监听事件
                mediaPlayer.prepare(); // 准备播放
                mediaPlayer.seekTo(50000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class PreparedListener implements OnPreparedListener {
        int postSize;

        public PreparedListener(int postSize) {
            this.postSize = postSize;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.e("onPrepared", "----onPrepared");
            video_pb.setVisibility(View.GONE); // 准备完成后，隐藏控件
            video_btn_play
                    .setBackgroundResource(R.drawable.qiyi_sdk_play_btn_pause);
            if (mediaPlayer != null) {
                mediaPlayer.start(); // 开始播放视频
            } else {
                return;
            }
            if (postSize > 0) { // 说明中途停止过（activity调用过pase方法，不是用户点击停止按钮），跳到停止时候位置开始播放
                mediaPlayer.seekTo(postSize); // 跳到postSize大小位置处进行播放
            }
            new Thread(playingSeekBar).start(); // 启动线程，更新进度条
        }
    }

    private class surFaceView implements Callback { // 上面绑定的监听的事件

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            mediaPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) { // 创建完成后调用
            if (postSize > 0 && videopath != null) { // 说明，停止过activity调用过pase方法，跳到停止位置播放
                new PlayMovie(postSize).start();
                flag = true;
                int sMax = video_seekbar_time.getMax();
                int mMax = mediaPlayer.getDuration();

                video_tv_otime
                        .setText(change(mediaPlayer.getCurrentPosition() / 1000));
                video_tv_ctime
                        .setText(change(mediaPlayer.getDuration() / 1000));

                video_seekbar_time.setProgress(postSize * sMax / mMax);
                postSize = 0;
                video_pb.setVisibility(View.GONE);
            } else {
                new PlayMovie(0).start();// 表明是第一次开始播放
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) { // activity调用过pase方法，保存当前播放位置
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                postSize = mediaPlayer.getCurrentPosition();
                mediaPlayer.stop();
                flag = false;
                video_pb.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        video_btn_play.setEnabled(true);
        video_btn_play
                .setBackgroundResource(R.drawable.qiyi_sdk_play_btn_pause);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                position = mediaPlayer.getCurrentPosition();
                mediaPlayer.stop();
                SharedPreferences share = getSharedPreferences("video_player",
                        0);
                Editor editor = share.edit();
                editor.putInt("position", position);
                editor.commit();
            }
        }
    }

    @Override
    protected void onDestroy() { // activity销毁后，释放资源
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        System.gc();
    }

    private void setListener() {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer
                .setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        video_seekbar_time.setSecondaryProgress(percent);
                    }
                });

        mediaPlayer
                .setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // 视频播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        flag = false;
                        video_tv_otime.setText(change(mediaPlayer.getDuration() / 1000));
                        video_btn_play
                                .setBackgroundResource(R.drawable.qiyi_sdk_play_btn_player);
                        // 播放完成后需要自动播放下一集
                        Log.e("mediaPlayer", "本集结束");
                    }
                });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
                Log.e("mediaPlayer", "无法播放：" + arg1 + "：" + arg2);
                return false;
            }
        });
        /**
         * 如果视频在播放，则调用mediaPlayer.pause();，停止播放视频，反之，mediaPlayer.start()
         * ，同时换按钮背景
         */
        video_btn_play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    video_btn_play
                            .setBackgroundResource(R.drawable.qiyi_sdk_play_btn_player);
                    mediaPlayer.pause();
                    postSize = mediaPlayer.getCurrentPosition();
                } else {
                    video_btn_play
                            .setBackgroundResource(R.drawable.qiyi_sdk_play_btn_pause);
                    mediaPlayer.start();
                    if (flag == false) {
                        flag = true;
                        new Thread(playingSeekBar).start();
                    }
                }
            }
        });
        video_seekbar_time
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        if (isFastDoubleClick()) {
                            int value = video_seekbar_time.getProgress()
                                    * mediaPlayer.getDuration() // 计算进度条需要前进的位置数据大小
                                    / video_seekbar_time.getMax();
                            video_tv_otime.setText(change(value / 1000));
                            mediaPlayer.seekTo(value);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {

                    }
                });
        /**
         * 点击屏幕，切换控件的显示，显示则应藏，隐藏，则显示
         */
        video_sv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 只有全屏时才显示操作按钮
                // if (!isFullScreen) {
                // return;
                // }
                if (isLocked) {
                    video_ll_title.setVisibility(View.GONE);
                    video_ll_bottom.setVisibility(View.GONE);
                } else {
                    video_ll_title.setVisibility(View.VISIBLE);
                    video_ll_bottom.setVisibility(View.VISIBLE);
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(0x124),
                            3000);// 隐藏控件
                }
            }
        });

    }

    // 防止用户频繁操作
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (3000 < timeD) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // 如果是锁定状态不允许使用系统后退键
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isLocked) {
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public static String change(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }

        return h + ":" + d + ":" + s;
    }

    List<String> pathList = new ArrayList<>();
    String pathName;
    public void save(String urlStr) {
        int byteSum = 0;
        int byteRead = 0;
//        String urlStr = remoteUrl;
//        String urlStr = "http://61.155.108.56:19999/businspection/userfiles/18f3821417e540f0a1c12a48a022043f/files/20160826110712_622.mp4";
//        String urlStr = "http://www.zcycjy.com/coursePath/%E7%BB%A7%E7%BB%AD%E6%95%99%E8%82%B2/%E6%96%B0%E8%AF%BE%E7%A8%8B%E8%A7%86%E9%A2%91/%E2%80%9C%E8%90%A5%E6%94%B9%E5%A2%9E%E2%80%9D%E6%94%BF%E7%AD%96%E8%A7%A3%E8%AF%BB%E5%8F%8A%E4%BC%81%E4%B8%9A%E7%A8%8E%E5%8A%A1%E9%A3%8E%E9%99%A9%E4%B8%8E%E7%AD%B9%E5%88%92%E6%8E%A2%E8%AE%A81.mp4";
//        String urlStr = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
        String path = "bus";
//        int a1 = path.lastIndexOf("/");
//        String b = path.substring(a1 + 1);
//        System.out.println(b);
//        String fileName = b;
//        Log.e("qqq", "fileName:" + b);

        String fileName = "33.mp4";
        OutputStream output = null;
        try {
            /*
             * 通过URL取得HttpURLConnection
             * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
             * <uses-permission android:name="android.permission.INTERNET" />
             */
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream input = conn.getInputStream();
            //取得inputStream，并将流中的信息写入SDCard

            /*
             * 写前准备
             * 1.在AndroidMainfest.xml中进行权限配置
             * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
             * 取得写入SDCard的权限
             * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
             * 3.检查要保存的文件上是否已经存在
             * 4.不存在，新建文件夹，新建文件
             * 5.将input流中的信息写入SDCard
             * 6.关闭流
             */
            String SDCard = Environment.getExternalStorageDirectory() + "";
//            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            }else{
//                //内置存储路径
//
//            }
            pathName = SDCard + "/" + path + "/" + fileName;//SD卡文件存储路径
            pathList.add(pathName);
            Log.i("pathName", "pathName:" + pathName);
            File file = new File(pathName);
            if (file.exists()) {
                System.out.println("exits");
                return;
            } else {
                String dir = SDCard + "/" + path;
                new File(dir).mkdirs();//新建文件夹
                file.createNewFile();//新建文件
                output = new FileOutputStream(file);
                //读取大文件
                byte[] buffer = new byte[4 * 1024];
                while ((byteRead = input.read(buffer)) != -1) {
                    byteSum += byteRead;
                    System.out.println(byteSum);
                    output.write(buffer, 0, byteRead);
                }
                output.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }


}
