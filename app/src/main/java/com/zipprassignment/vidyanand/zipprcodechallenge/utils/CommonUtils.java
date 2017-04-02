package com.zipprassignment.vidyanand.zipprcodechallenge.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

/**
 * Created by vidyanand on 2/7/16.
 */
public class CommonUtils {


    public static void showAlertDialog(Context context, String alertMsg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setMessage(alertMsg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }

    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();

        ImageLoader.getInstance().init(config.build());
    }

    public static ArrayList<Bitmap> getBreakPhotosList(int numBlocks, Bitmap bitmap) {

        ArrayList<Bitmap> blocksImages = new ArrayList<Bitmap>(numBlocks);

        if (bitmap != null) {

            int rows, columns;

            int blockHeight, blockWidth;

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

            rows = columns = (int) Math.sqrt(numBlocks);

            blockHeight = bitmap.getHeight() / rows;
            blockWidth = bitmap.getWidth() / columns;

            int yCoordPxPos = 0;

            for (int x = 0; x < rows; x++) {

                int xCoordPxPos = 0;

                for (int y = 0; y < columns; y++) {
                    blocksImages.add(Bitmap.createBitmap(scaledBitmap, xCoordPxPos,
                            yCoordPxPos, blockWidth, blockHeight));
                    xCoordPxPos += blockWidth;
                }

                yCoordPxPos += blockHeight;
            }
        }

        return blocksImages;
    }
}
