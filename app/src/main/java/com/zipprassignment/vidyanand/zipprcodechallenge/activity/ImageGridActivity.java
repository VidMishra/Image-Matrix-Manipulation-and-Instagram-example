package com.zipprassignment.vidyanand.zipprcodechallenge.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;
import com.zipprassignment.vidyanand.zipprcodechallenge.adapter.PhotoAdapter;
import com.zipprassignment.vidyanand.zipprcodechallenge.dialog.AppProgressDialog;
import com.zipprassignment.vidyanand.zipprcodechallenge.dialog.ImageManipulationDialog;
import com.zipprassignment.vidyanand.zipprcodechallenge.utils.AppConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by vidyanand on 2/7/16.
 */
public class ImageGridActivity extends AppCompatActivity {

    private AppProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);

        mProgressDialog = new AppProgressDialog(ImageGridActivity.this);
        mProgressDialog.setCanceledOnTouchOutside(false);

        ArrayList<String> listPhotos = getIntent().getStringArrayListExtra(AppConstants.KEY_PHOTO_LIST);

        GridView gvPhotos = (GridView) findViewById(R.id.gv_images);

        if (listPhotos != null) {
            PhotoAdapter photoAdapter = new PhotoAdapter(ImageGridActivity.this, listPhotos, new ImageOnClickListener());
            gvPhotos.setAdapter(photoAdapter);
        }
    }

    private class ImageOnClickListener implements PhotoAdapter.ImageSelectionDelegate {

        @Override
        public void onImageSelected(Bitmap bitmap) {
            ImageManipulationDialog dialog = new ImageManipulationDialog(ImageGridActivity.this, bitmap, mProgressDialog);
            dialog.show();
        }
    }
}