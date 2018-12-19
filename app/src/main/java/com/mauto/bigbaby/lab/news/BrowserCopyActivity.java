package com.mauto.bigbaby.lab.news;

import android.content.Intent;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mauto.bigbaby.R;

public class BrowserCopyActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        initResource();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_copy);

        initViews();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private WebView mWvBrowserCopy;
    private ContentLoadingProgressBar mPbProgress;

    private void initViews() {
        mWvBrowserCopy = findViewById(R.id.wv_browser_copy);
        mPbProgress = findViewById(R.id.pb_progress);

        setWebViewClient();

        /////////////////////////////////////////////////////////////
        WebSettings webSettings = mWvBrowserCopy.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webSettings.setJavaScriptEnabled(true);
        //设置适应Html5的一些方法
        webSettings.setDomStorageEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        /////////////////////////////////////////////////////////////
        mWvBrowserCopy.loadUrl(url);
    }


    private void setWebViewClient() {
        mWvBrowserCopy.setWebViewClient(new BrowserClient(this));
        mWvBrowserCopy.setWebChromeClient(new BrowserChromeClient(mPbProgress));
    }


    private String url;
    private void initResource() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("link_url");
        } else {
            url = "https://www.thestartmagazine.com/feed/summary?isDesktop=false&publisherId=Mavericks-Web&key=FAqV8ZHwYXC6S6gyDy3cyvhd129SYyo8&countryCode=US&tag=epUS-one&language=en&personal=true";
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
