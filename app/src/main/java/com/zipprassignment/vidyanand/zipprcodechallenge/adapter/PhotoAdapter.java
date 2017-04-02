package com.zipprassignment.vidyanand.zipprcodechallenge.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zipprassignment.vidyanand.zipprcodechallenge.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vidyanand on 2/7/16.
 */
public class PhotoAdapter extends BaseAdapter {

    private ArrayList<String> mListPhotosUrl;

    private Context mContext;

    private DisplayImageOptions mOptions;

    private ImageSelectionDelegate mImageSelectionDelegate;

    private HashMap<Integer, Bitmap> mapLoadedImage;

    private LayoutInflater mInflater;

    public PhotoAdapter(Context context, ArrayList<String> listPhotosUrl, ImageSelectionDelegate imageSelectionDelegate) {

        this.mContext = context;
        this.mListPhotosUrl = listPhotosUrl;
        this.mImageSelectionDelegate = imageSelectionDelegate;

        mInflater = LayoutInflater.from(mContext);

        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_placeholder)
                .showImageForEmptyUri(R.drawable.ic_placeholder)
                .showImageOnFail(R.drawable.ic_placeholder)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        mapLoadedImage = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mListPhotosUrl.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_gridview, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.imgPhoto = (ImageView) view.findViewById(R.id.img_photo);
            holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImageLoader.getInstance().displayImage(mListPhotosUrl.get(position), holder.imgPhoto, mOptions, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setProgress(0);
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
                mapLoadedImage.put(position, loadedImage);
                holder.imgPhoto.setTag(position);
                holder.imgPhoto.setOnClickListener(new ImageOnClickListener());
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                holder.progressBar.setProgress(Math.round(100.0f * current / total));
            }
        });

        return view;
    }

    static class ViewHolder {
        ImageView imgPhoto;
        ProgressBar progressBar;
    }

    private class ImageOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            int position = (Integer) v.getTag();
            Bitmap bitmap = mapLoadedImage.get(position);

            mImageSelectionDelegate.onImageSelected(bitmap);
        }
    }

    public interface ImageSelectionDelegate {
        void onImageSelected(Bitmap bitmap);
    }
}
