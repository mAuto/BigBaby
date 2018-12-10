package com.mauto.bigbaby.support.base;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mauto.bigbaby.R;
import com.mauto.bigbaby.support.markdown.MarkdownPointer;

public class BigBaseActivity extends AppCompatActivity {

    private MenuItem mMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMenuAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.markdown_entrance_layout, menu);
        mMenuItem = menu.findItem(R.id.md_entrance);
        mMenuItem.setVisible(mMarkdownEntranceEnable);
        if (mMenuItemIcon != -1)
            mMenuItem.setIcon(mMenuItemIcon);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.md_entrance:{
                openMarkdown();
            }break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String mLink;
    private boolean mMarkdownEntranceEnable = false;
    protected void displayMarkdownEntrance(String link) {
        mLink = link;
        mMarkdownEntranceEnable = true;
        if (mMenuItem != null)
            mMenuItem.setVisible(true);
    }

    private Runnable mMenuAction = null;
    private void initMenuAction() {
        mMenuAction = new Runnable() {
            @Override
            public void run() {
                Uri uri = Uri.parse(MarkdownPointer.buildUri(mLink));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
    }

    private void openMarkdown() {
        mMenuAction.run();
    }

    private int mMenuItemIcon = -1;
    protected void resetMenuItemAction(Runnable run) {
        mMenuAction = run != null ? run : mMenuAction;
    }

    protected void resetMenuItemIcon(@DrawableRes int id) {
        mMenuItemIcon = id;
        mMarkdownEntranceEnable = true;
        if (mMenuItem != null) {
            mMenuItem.setIcon(mMenuItemIcon);
            mMenuItem.setVisible(true);
        }
    }

    protected void updateTitle(String title) {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(title);
        }
    }

}
