package com.example.ormlitetest.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * City部分是整个json串中的一部分，这部分是需要存到数据库的，所以尽量
 * 不要写在CityParseEntity中，否则使用起来不方便。
 * Created by huangjh on 2016/12/24 0024 11:20
 */
@DatabaseTable(tableName = "city")
public class City {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "province")
    private String province;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "country")
    private String country;
    @DatabaseField(columnName = "town")
    private String town;
    @DatabaseField(columnName = "code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public City() {
    }

    public City(String city, String code, String country, String province, String town) {
        this.city = city;
        this.code = code;
        this.country = country;
        this.province = province;
        this.town = town;
    }

    @Override
    public String toString() {
        return "City{" +
                "province='" + province + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                '}';
    }
}
