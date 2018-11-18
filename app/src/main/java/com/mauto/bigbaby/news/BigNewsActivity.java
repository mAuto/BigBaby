package com.mauto.bigbaby.news;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mauto.bigbaby.R;

public class BigNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_news);

        initViews();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private WebView mWvBrowser;
    private ContentLoadingProgressBar mPbProgress;
    private void initViews() {
        mWvBrowser = findViewById(R.id.wv_news_browser);
        mPbProgress = findViewById(R.id.pb_progress);
        setWebViewClient();

        /////////////////////////////////////////////////////////////
        WebSettings webSettings = mWvBrowser.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webSettings.setJavaScriptEnabled(true);
        //设置适应Html5的一些方法
        webSettings.setDomStorageEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        /////////////////////////////////////////////////////////////
        mWvBrowser.loadUrl("https://www.thestartmagazine.com/feed/summary?isDesktop=false&publisherId=Mavericks-Web&key=FAqV8ZHwYXC6S6gyDy3cyvhd129SYyo8&countryCode=US&tag=epUS-one&language=en&personal=true"); //"https://www.thestartmagazine.com/feed/summary?isDesktop=false&publisherId=Mavericks-Web&key=FAqV8ZHwYXC6S6gyDy3cyvhd129SYyo8&countryCode=US&tag=epUS-one&language=en&personal=true"
    }


    private void setWebViewClient() {
        mWvBrowser.setWebViewClient(new BrowserClient(this));
//        mWvBrowser.setWebChromeClient(new BrowserChromeClient(mPbProgress));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}
