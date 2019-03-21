package com.mauto.bigbaby.lab.Toast;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mauto.bigbaby.BabyApplication;
import com.mauto.bigbaby.R;

public class ToastUtils {

    public static void showToast(CharSequence message) {

        Toast mToast = Toast.makeText(BabyApplication.getApplication(),
                message, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showToast(int rId) {
        Toast mToast = Toast.makeText(BabyApplication.getApplication(), rId,
                Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showInteractiveToast(Context context, String content, View.OnClickListener listener) {
//        Toast mToast = Toast.makeText(BabyApplication.getApplication(), content,
//                Toast.LENGTH_SHORT);
//        mToast.setGravity(Gravity.CENTER, 0, 0);
//
//        View contentView = mToast.getView();
//        TextView tvContent = contentView.findViewById(com.android.internal.R.id.message);
//        tvContent.setCompoundDrawables(null, null, context.getResources().getDrawable(R.mipmap.ic_big_launcher), null);
//
//        mToast.show();
    }

}
