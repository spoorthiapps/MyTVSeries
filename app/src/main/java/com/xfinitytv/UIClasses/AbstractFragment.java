package com.xfinitytv.UIClasses;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;

public class AbstractFragment extends Fragment {

    public AlertDialog.Builder createOneButtonDialog(Context context, String messageBody, String title) {
        final AlertDialog.Builder alertDailogBuilder = new AlertDialog.Builder(context);
        alertDailogBuilder.setCancelable(false);
        if (!TextUtils.isEmpty(title)) {
            alertDailogBuilder.setTitle(title);
        }
        if (!TextUtils.isEmpty(messageBody)) {
            alertDailogBuilder.setMessage(messageBody);
        }
        return alertDailogBuilder;
    }
    
}
