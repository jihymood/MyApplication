package com.xpro.test1;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectSocket(); //使用的是BaseBaseActivity中的public方法
        addStr="增加";//使用的是BaseBaseActivity中的public变量
//        removeStr="";//报错

    }

}
