package com.zipprassignment.vidyanand.zipprcodechallenge.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;

import java.util.ArrayList;

/**
 * Created by vidyanand on 2/7/16.
 */
public class CircleProgressBar extends LinearLayout {

    private Handler handler = new Handler();

    private ArrayList<Drawable> drawableArrayList = new ArrayList<>();

    private int currentItem;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (currentItem < drawableArrayList.size()) {
                drawableArrayList.get(currentItem).setAlpha((currentItem + 1) * 255 / drawableArrayList.size());
                currentItem++;
            } else {
                for (Drawable drawable : drawableArrayList) {
                    drawable.setAlpha(20);
                    currentItem = 0;
                }
            }
            handler.postDelayed(runnable, 300);
        }
    };


    public CircleProgressBar(Context context) {
        super(context);
        init();
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;

        for (int i = 0; i < 6; i++) {

            ImageView imageView = new ImageView(getContext());

            Drawable drawable = getContext().getResources().getDrawable(R.drawable.circle_shape_theme_dark_15dp);
            drawable.setAlpha(20);
            drawableArrayList.add(drawable);
            imageView.setImageDrawable(drawable);
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
        }

        start();
    }

    public void start() {

        handler.postDelayed(runnable, 300);
    }
}
