package com.example.ormlitetest.contentprovider;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ormlitetest.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        contactList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);


    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor=getContentResolver().query(Con)
        } catch (Exception e) {

        }
    }


}
