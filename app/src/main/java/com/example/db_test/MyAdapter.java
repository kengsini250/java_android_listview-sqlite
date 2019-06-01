package com.example.db_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    static class ViewHolder
    {
        TextView name;
        TextView url;
    }

    private LayoutInflater mInflater;
    private ArrayList<WebData> data;

    MyAdapter(Context context,ArrayList<WebData> wd)
    {
        data = wd;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.mylist, null);

            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.textView3);
            holder.url = view.findViewById(R.id.textView4);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        WebData wd = data.get(i);

        holder.name.setText(wd.getName());
        holder.url.setText(wd.getUrl());
        return view;
    }

    public void refresh(ArrayList<WebData> wd) {
        data = wd;
        notifyDataSetChanged();
    }
}
