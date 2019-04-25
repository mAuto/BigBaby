package com.mauto.bigbaby.lab.terminal;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.tools.GlobalAttributes;

/**
 * Created by haohuidong on 19-3-21.
 */

public class TerminalDialog extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = GlobalAttributes.getAttribute().getScreenWidth();
        window.setAttributes(windowParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lab_terminal_dialog_layout, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initViews(view);

        return view;
    }

    private FloatingActionButton mFabIcon;
    private LinearLayout mLLPanel;
    private FrameLayout mFlContainer;
    private void initViews(View view) {
        mFlContainer = view.findViewById(R.id.fl_container);
        mFabIcon = view.findViewById(R.id.fab_icon);
        mLLPanel = view.findViewById(R.id.ll_panel);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!animHasEnded) {
            new Handler().postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    startFabAnim();
                    startPanelAnim();
                    animHasEnded = true;
                }
            }, 100);
        }
    }

    private boolean animHasEnded = false;

    private void startFabAnim() {
        if (mFabIcon == null)
            return;
        Animator animScaleX = ObjectAnimator.ofFloat(mFabIcon, "scaleX", 0f, 1f);
        Animator animScaleY = ObjectAnimator.ofFloat(mFabIcon, "scaleY", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animScaleX).with(animScaleY);
        animSet.setDuration(200);
        animSet.setInterpolator(new LinearOutSlowInInterpolator());
        animSet.start();
        mFabIcon.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startPanelAnim() {
        int width = mLLPanel.getMeasuredWidth();
        int height = mLLPanel.getMeasuredHeight();

        int offset = width - height;

        Animator anim = ViewAnimationUtils.createCircularReveal(mLLPanel, 0, -offset, offset, (float) (Math.sqrt(2)*width) + 50)
                .setDuration(500);
        anim.setInterpolator(new LinearOutSlowInInterpolator());

        AnimatorSet animSet = new AnimatorSet();
        Animator animAlpha = ObjectAnimator.ofFloat(mLLPanel, "alpha", 0.2f,1f);
        animAlpha.setDuration(500);
        animAlpha.setInterpolator(new LinearOutSlowInInterpolator());

        animSet.play(anim).with(animAlpha);
        animSet.start();

        mLLPanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        Log.e(">>>>>>", "TerminalDialog.80 --> " + "onDetach"+"");
        animHasEnded  =false;
        Terminal.getInstance().showTerminalEntrance();
        super.onDetach();
    }


}
