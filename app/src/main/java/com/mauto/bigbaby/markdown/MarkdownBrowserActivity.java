package com.mauto.bigbaby.markdown;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.base.BigBaseActivity;

public class MarkdownBrowserActivity extends BigBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markdown_activity_main);

        updateTitle("Markdown browser");
//        resetMenuItemIcon(R.mipmap.ic_download);

        handlerIntent();
        initViews();
    }

    /////////////////////////////////////////--> 18-11-19 下午12:30 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> handle intent <-- ↓↓↓/////////////////////////////////////
    private String mMdLink;

    private void handlerIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            if (uri != null && !TextUtils.isEmpty(uri.toString())) {
                mMdLink = uri.getQueryParameter(MarkdownPointer.MD_KEY_LINK);
            }
        }
    }
    /////////////////////////////////////↑↑↑ --> handle intent <-- ↑↑↑/////////////////////////////////////

    private WebView mWvBrowser;
    private void initViews() {
        mWvBrowser = findViewById(R.id.wv_markdown);
        mWvBrowser.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        /////////////////////////////////////////////////////////////
        WebSettings webSettings = mWvBrowser.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);

        if (Build.VERSION.SDK_INT > 16)
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        /////////////////////////////////////////////////////////////

        if (TextUtils.isEmpty(mMdLink))
            return;
        mWvBrowser.loadUrl(mMdLink);
    }

    @Override
    public void onBackPressed() {
        if (!mWvBrowser.canGoBack())
            super.onBackPressed();
        else mWvBrowser.goBack();
    }
}
