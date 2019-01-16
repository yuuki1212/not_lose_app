package com.example.kirin_dev31.toreger.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.domain.models.Item;
import com.example.kirin_dev31.toreger.domain.models.Space;

import java.util.List;

public class SpaceAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Space> spaceList;
    private int layoutId;

    private static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    public SpaceAdapter(Context context, int layoutId, List<Space> sList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.spaceList = sList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return spaceList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            holder = new ViewHolder();

            holder.imageView = convertView.findViewById(R.id.item_image);
            holder.textView = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textView.setText(spaceList.get(position).name);
        holder.imageView.setImageURI();
        return null;
    }
}
