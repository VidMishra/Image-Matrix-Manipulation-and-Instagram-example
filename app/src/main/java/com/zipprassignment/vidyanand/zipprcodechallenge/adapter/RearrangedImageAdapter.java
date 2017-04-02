package com.zipprassignment.vidyanand.zipprcodechallenge.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by vidyanand on 2/7/16.
 */
public class RearrangedImageAdapter extends BaseAdapter {

    private TreeMap<Double, Bitmap> mTreeMapBitmap;

    private Context mContext;

    private LayoutInflater mInflater;

    public RearrangedImageAdapter(Context context, TreeMap<Double, Bitmap> treeMapBitmap) {

        this.mContext = context;
        this.mTreeMapBitmap = treeMapBitmap;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTreeMapBitmap.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_image_manupulated, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.imgPhoto = (ImageView) view.findViewById(R.id.img_photo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Object key = mTreeMapBitmap.keySet().toArray()[position];

        holder.imgPhoto.setImageBitmap(mTreeMapBitmap.get(key));

        return view;
    }

    static class ViewHolder {
        ImageView imgPhoto;
    }
}
