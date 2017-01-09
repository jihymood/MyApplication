package com.example.jackson.entity;

import java.util.List;

/**
 * Created by huangjh on 2016/12/23 0023 9:35
 */

public class WeatherRoot {
    private String success;

    private List<WeatherBody> result;

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setResult(List<WeatherBody> result) {
        this.result = result;
    }

    public List<WeatherBody> getResult() {
        return this.result;
    }

}
