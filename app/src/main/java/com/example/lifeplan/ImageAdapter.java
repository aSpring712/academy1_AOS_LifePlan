package com.example.lifeplan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Integer> mID = null;

    public ImageAdapter(Context c, ArrayList<Integer> mID) {
        mContext = c;
        this.mID = mID;
    }

    @Override
    public int getCount() {
        return mID.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(mID.get(i));

        return imageView;
    }
}
