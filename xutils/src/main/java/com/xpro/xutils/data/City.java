package com.xpro.xutils.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by huangjh on 2016/12/28 0028 19:36
 */
@Table(name = "city")
public class City {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "code")
    private int code;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public City(String city, int code, String country, String province) {
        this.city = city;
        this.code = code;
        this.country = country;
        this.province = province;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "country='" + country + '\'' +
                ", code=" + code +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
