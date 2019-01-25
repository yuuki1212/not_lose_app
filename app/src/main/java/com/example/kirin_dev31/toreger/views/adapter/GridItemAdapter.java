package com.example.kirin_dev31.toreger.views.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcelable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.domain.models.GridItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GridItemAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<GridItem> gridItemList;
    private int layoutId;

    private int screenWidthHalf = 0;

    private static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    public GridItemAdapter(Context context, int layoutId, List<GridItem> gList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.gridItemList = gList;
        this.layoutId = layoutId;

        // 画面の横幅の半分のサイズを取得
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);
            int screenWidth = size.x;
            this.screenWidthHalf = screenWidth/2;
        }
    }

    @Override
    public int getCount() {
        return gridItemList.size();
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
        View view;

        if (convertView == null) {
            view = inflater.inflate(layoutId, parent, false);
        } else {
            view = convertView;
        }

        TextView textView = view.findViewById(R.id.item_name);
        textView.setText(addText(position));
        ImageView imageView = view.findViewById(R.id.item_image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.with(context).load(addUrl(position)).resize(screenWidthHalf, screenWidthHalf).into(imageView);
        return view;
    }

    private String addUrl(int position) {
        return String.format(Locale.US, "http://www.atmarkit.co.jp/ait/articles/1612/01/l_r20_05-01.PNG");
    }

    private String addText(int position) {
        String result = "";
        for (GridItem gridItem : gridItemList) {
            if (gridItem.item != null) {
                result = gridItem.item.name;
            } else if (gridItem.space != null) {
                result = gridItem.space.name;
            }
        }
        return result;
    }

    public ArrayList<GridItem> getItemList() {
        return (ArrayList<GridItem>) gridItemList;
    }
}
