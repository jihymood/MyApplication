package com.example.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by huangjh on 2017/3/17 0017 13:50
 * Email：huangjihy@163.com
 */
public class RetrofitUtils {
    //服务器路径
    private static final String APP_SERVER = "http://www.baicu.com";

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;

    private static Retrofit getInstance() {
        if (null == retrofit) {
            if (null == okHttpClient) {
                okHttpClient = new OkHttpClient();
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(APP_SERVER)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }


}
