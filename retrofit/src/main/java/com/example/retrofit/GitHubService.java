package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by huangjh on 2017/3/17 0017 13:43
 * Email：huangjihy@163.com
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Book>> listRepos(@Path("user") String user);

}
