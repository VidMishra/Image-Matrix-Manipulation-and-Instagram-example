package com.zipprassignment.vidyanand.zipprcodechallenge.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;

import java.util.ArrayList;

/**
 * Created by vidyanand on 2/7/16.
 */
public class OriginalImageAdapter extends BaseAdapter {

    private ArrayList<Bitmap> mListBitmap;

    private Context mContext;

    private LayoutInflater mInflater;

    public OriginalImageAdapter(Context context, ArrayList<Bitmap> listBitmap) {

        this.mContext = context;

        this.mListBitmap = listBitmap;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListBitmap.size();
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

        holder.imgPhoto.setImageBitmap(mListBitmap.get(position));

        return view;
    }

    static class ViewHolder {
        ImageView imgPhoto;
    }
}
