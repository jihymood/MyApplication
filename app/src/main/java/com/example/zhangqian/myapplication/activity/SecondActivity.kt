package com.example.zhangqian.myapplication.activity

import android.app.Activity
import android.os.Bundle
import com.example.zhangqian.myapplication.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textView_second.text = "哈哈哈哈";
        button.text="设置新按钮名称啦"
        button.textSize=23f
    }


}
