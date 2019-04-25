package com.mauto.bigbaby.lab.terminal;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;

import com.mauto.bigbaby.R;

/**
 * Created by haohuidong on 19-3-21.
 */

public class Terminal {

    //region >>> singleton
    /*
    * 19-3-21 下午4:57
    * */
    /////////////////////////////////////↓↓↓ --> init <-- ↓↓↓/////////////////////////////////////
    private Terminal(){}

    private static class InstanceHolder{
        private static Terminal instance = new Terminal();
    }

    public static Terminal getInstance() {
        return InstanceHolder.instance;
    }
    /////////////////////////////////////↑↑↑ --> init <-- ↑↑↑/////////////////////////////////////
    //endregion <<< singleton

    //region >>> init terminal entrance
    /*
    * 19-3-21 下午4:59
    * */
    /////////////////////////////////////↓↓↓ --> init views <-- ↓↓↓/////////////////////////////////////
    private FloatingActionButton mTerminalEntrance = null;
    private TerminalDialog mDialog = null;

    public void addTerminal(final AppCompatActivity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed())
            return;

        mTerminalEntrance = new FloatingActionButton(activity);
        mTerminalEntrance.setBackgroundTintList(ColorStateList.valueOf(activity.getResources().getColor(R.color.terminal_bg)));
        mTerminalEntrance.setImageResource(R.mipmap.ic_terminal_cover);
        mTerminalEntrance.setRippleColor(activity.getResources().getColor(R.color.terminal_ripple_bg));

        addToRoot(activity);
        setEntranceParams(activity);

        mTerminalEntrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTerminalEntrance.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                    @Override
                    public void onHidden(FloatingActionButton fab) {
                        new TerminalDialog().show(activity.getSupportFragmentManager(), "terminal");
                    }
                });
            }
        });
    }

    private void setEntranceParams(AppCompatActivity activity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mTerminalEntrance.getLayoutParams();
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        params.bottomMargin = activity.getResources().getDimensionPixelSize(R.dimen.terminal_margin_bottom);
        params.rightMargin = activity.getResources().getDimensionPixelSize(R.dimen.terminal_margin_right);
        params.width = activity.getResources().getDimensionPixelSize(R.dimen.terminal_size);
        params.height = activity.getResources().getDimensionPixelSize(R.dimen.terminal_size);
    }

    private void addToRoot(AppCompatActivity activity) {
        FrameLayout rootView = (FrameLayout) activity.getWindow().getDecorView();
        rootView.addView(mTerminalEntrance);
    }

    public void showTerminalEntrance() {
        if (mTerminalEntrance != null)
            mTerminalEntrance.show();
    }
    /////////////////////////////////////↑↑↑ --> init views <-- ↑↑↑/////////////////////////////////////
    //endregion <<< init terminal entrance

}
