package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;

public class DonateActivity extends AppCompatActivity implements DonatePresenter.View {
    private DonatePresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DonatePresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());
    }
    private Unbinder mUnbinder;
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_help);
        mUnbinder = ButterKnife.bind(this);
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Override
    public void setToolbar() {
        mToolbar.setTitle(R.string.activity_donate_label);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @BindView(R.id.wv_help)
    WebView mWebView;

    @Override
    public void setWebView(String donateUrl) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClientExt());
        mWebView.setWebViewClient(new WebViewClientExt());
        mWebView.loadUrl(donateUrl);
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }
    @BindView(R.id.loading_progressbar)
    ContentLoadingProgressBar mLoadingProgressBar;

    private class WebViewClientExt extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mLoadingProgressBar != null) {
                mLoadingProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mLoadingProgressBar != null) {
                mLoadingProgressBar.setVisibility(View.GONE);
            }
        }
    }

    private class WebChromeClientExt extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (mLoadingProgressBar != null) {
                mLoadingProgressBar.setProgress(newProgress);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

}