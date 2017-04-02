package com.zipprassignment.vidyanand.zipprcodechallenge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.zipprassignment.vidyanand.zipprcodechallenge.R;

/**
 * Created by vidyanand on 2/7/16.
 */
public class AppProgressDialog extends Dialog {

    public AppProgressDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    public AppProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AppProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_bar);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }
}
