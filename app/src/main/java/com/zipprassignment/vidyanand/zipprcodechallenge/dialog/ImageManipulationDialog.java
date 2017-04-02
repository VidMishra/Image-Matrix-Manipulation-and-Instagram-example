package com.zipprassignment.vidyanand.zipprcodechallenge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;
import com.zipprassignment.vidyanand.zipprcodechallenge.adapter.OriginalImageAdapter;
import com.zipprassignment.vidyanand.zipprcodechallenge.adapter.RearrangedImageAdapter;
import com.zipprassignment.vidyanand.zipprcodechallenge.utils.CommonUtils;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by vidyanand on 2/7/16.
 */
public class ImageManipulationDialog extends Dialog {

    private Context mContext;

    private Bitmap mBitmap;

    private GridView mGvRearranged;

    private AppProgressDialog mProgressDialog;

    private TreeMap<Double, Bitmap> mTreeMapBitmap;

    private ArrayList<Bitmap> mBreakPhotosList;

    public ImageManipulationDialog(Context context, Bitmap bitmap, AppProgressDialog progressDialog) {
        super(context, R.style.DialogTheme);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        this.mContext = context;
        this.mBitmap = bitmap;
        this.mProgressDialog = progressDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image_manipulation);

        GridView gvOriginal = (GridView) findViewById(R.id.gv_original);

        mGvRearranged = (GridView) findViewById(R.id.gv_rearranged);

        if (mBitmap != null) {
            mBreakPhotosList = CommonUtils.getBreakPhotosList(16, mBitmap);

            if (mBreakPhotosList != null) {

                OriginalImageAdapter adapter = new OriginalImageAdapter(mContext, mBreakPhotosList);
                gvOriginal.setAdapter(adapter);

                new RearrangeOperation().execute();
            }
        }
    }

    private void creatingRearrangedView(ArrayList<Bitmap> breakPhotosList) {

        mTreeMapBitmap = new TreeMap<Double, Bitmap>();

        for (int i = 0; i < breakPhotosList.size(); i++) {

            double avgRedValue = getAvgRedValueOfEachPhoto(breakPhotosList.get(i));

            mTreeMapBitmap.put(avgRedValue, breakPhotosList.get(i));
        }
    }

    private double getAvgRedValueOfEachPhoto(Bitmap bitmap) {

        double redValue = 0;
        double pxCount = 0;

        for (int y = 0; y < bitmap.getHeight(); y++) {

            for (int x = 0; x < bitmap.getWidth(); x++) {
                int c = bitmap.getPixel(x, y);
                pxCount++;
                redValue += Color.red(c);
            }
        }

        double avgRedValue = (redValue / pxCount);

        return avgRedValue;
    }

    private class RearrangeOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            creatingRearrangedView(mBreakPhotosList);
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            RearrangedImageAdapter rearrangedImageAdapter = new RearrangedImageAdapter(mContext, mTreeMapBitmap);
            mGvRearranged.setAdapter(rearrangedImageAdapter);
            mProgressDialog.cancel();
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}