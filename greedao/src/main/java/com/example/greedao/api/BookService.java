package com.example.greedao.api;

import com.example.greedao.bean.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HASEE on 2017/5/17 11:17
 */

public interface BookService {

    @GET("/")
    Call<Weather> getDetail();

}
