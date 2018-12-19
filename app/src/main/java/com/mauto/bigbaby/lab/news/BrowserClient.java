package com.mauto.bigbaby.lab.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserClient extends WebViewClient {

    private Context mContext;
    public BrowserClient(Context context) {
        mContext = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.e("--> BrowserClient <--", "start");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.e("--> BrowserClient <--", "finish");
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        handler.proceed();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.e("--> BrowserClient <--", "url:"+url);
        if (!Constants.ORIGINAL_URL.endsWith(url)){
            Intent intent = new Intent(mContext, BrowserCopyActivity.class);
            intent.putExtra("link_url", url);
            mContext.startActivity(intent);
            return true;
        } else return super.shouldOverrideUrlLoading(view, url);
    }

}
