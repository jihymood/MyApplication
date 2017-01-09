package com.example.jackson.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangjh on 2016/12/23 0023 15:27
 */

public class Person implements Parcelable {
    private String name;
    private String tel;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int image, String name, String tel) {
        this.image = image;
        this.name = name;
        this.tel = tel;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeInt(this.image);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.tel = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
