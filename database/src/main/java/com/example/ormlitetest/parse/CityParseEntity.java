package com.example.ormlitetest.parse;

import com.example.ormlitetest.bean.City;

import java.util.List;

/**
 * Created by huangjh on 2016/12/24 0024 15:19
 */

public class CityParseEntity {


    /**
     * success : true
     * body : {"data":[{"province":"江苏省","city":"连云港","country":"灌云县","code":222200,"town":"南岗乡"},{"province":"江苏省","city":"连云港","country":"灌南县","code":222200,"town":"南岗乡"},{"province":"江苏省","city":"连云港","country":"新浦区","code":222200,"town":"南岗乡"},{"province":"江苏省","city":"连云港","country":"海州区","code":222200,"town":"南岗乡"},{"province":"江苏省","city":"连云港","country":"东海县","code":222200,"town":"南岗乡"},{"province":"江苏省","city":"南京","country":"浦口区","code":222200,"town":"南岗乡"}]}
     * errorCode : -1
     * msg : 成功
     */

    private String success;
    private BodyBean body;
    private String errorCode;
    private String msg;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class BodyBean {
        private List<City> data;

        public List<City> getData() {
            return data;
        }

        public void setData(List<City> data) {
            this.data = data;
        }

        /**
         * 在使用GsonFormat时可以选择不生成
         *
         */

        /*public static class DataBean {
            *//**
             * province : 江苏省
             * city : 连云港
             * country : 灌云县
             * code : 222200
             * town : 南岗乡
             *//*

            private String province;
            private String city;
            private String country;
            private int code;
            private String town;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }
        }*/
    }
}
