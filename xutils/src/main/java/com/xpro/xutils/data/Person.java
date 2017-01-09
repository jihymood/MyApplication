package com.xpro.xutils.data;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by huangjh on 2016/12/28 0028 14:21
 */

@Table(name = "person")
public class Person {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "salary")
    private String salary;
    @Column(name = "age")
    private int age;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Person(String address, int age, String name, String salary) {
        this.address = address;
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", age=" + age +
                '}';
    }
}
