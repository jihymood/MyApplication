package com.example.ormlitetest.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ormlitetest.R;
import com.example.ormlitetest.helper.MyDataBaseHelper;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDataBaseHelper helper;
    private Button create_database, insert, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        create_database = (Button) findViewById(R.id.create_database);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        create_database.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);

        helper = new MyDataBaseHelper(this, "BookStore.db", null, 3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                helper.getWritableDatabase();
                Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.insert:
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "傻逼");
                values.put("pages", 23);
                values.put("author", "黄继海");
                values.put("price", 22);
                db.insert("Book", null, values);
                break;
            case R.id.delete:

                break;
        }
    }
}
