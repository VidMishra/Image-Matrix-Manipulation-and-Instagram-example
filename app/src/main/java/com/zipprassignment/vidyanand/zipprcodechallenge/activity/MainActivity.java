package com.zipprassignment.vidyanand.zipprcodechallenge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;
import com.zipprassignment.vidyanand.zipprcodechallenge.utils.AppConstants;
import com.zipprassignment.vidyanand.zipprcodechallenge.utils.CommonUtils;

import java.util.ArrayList;

import ly.kite.instagramphotopicker.InstagramPhoto;
import ly.kite.instagramphotopicker.InstagramPhotoPicker;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INSTAGRAM = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtils.initImageLoader(getApplicationContext());

        Button btnStart = (Button) findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new StartOnClickListener());
    }

    private class StartOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            InstagramPhotoPicker.startPhotoPickerForResult(MainActivity.this, AppConstants.CLIENT_ID, AppConstants.CALLBACK_URL, REQUEST_CODE_INSTAGRAM);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_INSTAGRAM) {

            if (resultCode == Activity.RESULT_OK) {

                InstagramPhoto[] instaPhotos = InstagramPhotoPicker.getResultPhotos(data);


                if (instaPhotos != null && instaPhotos.length > 0) {

                    ArrayList<String> listPhotos = null;

                    if (instaPhotos.length >= 20) {
                        listPhotos = getPhotosList(instaPhotos, 19);
                    } else {
                        listPhotos = getPhotosList(instaPhotos, instaPhotos.length);
                    }

                    Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                    intent.putStringArrayListExtra(AppConstants.KEY_PHOTO_LIST, listPhotos);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    CommonUtils.showAlertDialog(MainActivity.this, getString(R.string.msg_no_photo_found));
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {

                CommonUtils.showAlertDialog(MainActivity.this, getString(R.string.msg_unable_to_fetch_images));
            } else {

                CommonUtils.showAlertDialog(MainActivity.this, getString(R.string.msg_something_went_wrong));
            }
        }
    }

    private ArrayList<String> getPhotosList(InstagramPhoto[] instaPhotos, int size) {

        ArrayList<String> listPhotosUrl = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            listPhotosUrl.add(instaPhotos[i].getFullURL().toString());
        }

        return listPhotosUrl;
    }
}
