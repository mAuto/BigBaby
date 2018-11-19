package com.mauto.bigbaby.news;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class BrowserChromeClient extends WebChromeClient {

    private ContentLoadingProgressBar mPbProgress;
    public BrowserChromeClient(ContentLoadingProgressBar progressBar) {
        mPbProgress = progressBar;
//        mPbProgress.setMax(100);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        Log.e("--> BrowserChromeClient <--", "progress:"+newProgress);
        if (newProgress > 0 && newProgress < 100) {
            mPbProgress.setVisibility(View.VISIBLE);
//            mPbProgress.setProgress(newProgress);
        } else if (newProgress >= 100) {
            mPbProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }
}
