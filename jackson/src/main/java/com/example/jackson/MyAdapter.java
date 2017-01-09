package com.example.jackson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jackson.entity.Person;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.name;
import static com.example.jackson.R.id.nameText;
import static com.example.jackson.R.id.tel;

/**
 * Created by huangjh on 2016/12/23 0023 15:15
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Person> list;

    public MyAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = (Person) getItem(position);
        viewHolder.nameText.setText(person.getName());
        viewHolder.tel.setText(person.getTel());
        viewHolder.image.setImageResource(person.getImage());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.nameText)
        TextView nameText;
        @Bind(R.id.tel)
        TextView tel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
