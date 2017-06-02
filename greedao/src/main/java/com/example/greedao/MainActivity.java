package com.example.greedao;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.greedao.bean.Cat;
import com.example.greedao.bean.Person;
import com.example.greedao.db.DBManager;
import com.example.greedao.db.GreenDaoManager;
import com.example.greedao.db.MyManager;
import com.example.greendao.gen.CatDao;
import com.example.greendao.gen.PersonDao;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private GreenDaoManager dbManager1;
    private MyManager myManager;
    private PersonDao personDao;
    private CatDao catDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass(); //堆大小
        Log.e("MainActivity", "heapSize:" + heapSize);

        Runtime runtime = Runtime.getRuntime();
        long l = runtime.maxMemory();//通过maxMemory()方法获取系统可为APP分配的最大内存
        long l1 = runtime.totalMemory();//totalMemory() 获取APP当前所分配的内存heap空间大小
        Log.e("MainActivity", "l:" + l / 1024 + "\nl1:" + l1 / 1024);


        //        dbManager = DBManager.getInstance(this);
//        dbManager1.getSession().g etUserDao().insert(new User(102L, "傻逼", 20));
//        dbManager1 = GreenDaoManager.getInstance(this);
//        dbManager1.getSession().getDogDao().insert(new Dog(2L,2,"黄色","小黑子"));


        myManager = MyManager.getInstance(this);
        personDao=myManager.getDaoSession().getPersonDao();
        personDao.insert(new Person("傻逼"));

        Cat cat = new Cat();
        cat.setColor("黄色");
        cat.setName("阿黄");
        catDao = myManager.getDaoSession().getCatDao();
        catDao.insert(cat);


    }

}
