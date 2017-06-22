package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;

public class HelpActivity extends AppCompatActivity implements HelpPresenter.View {
  private HelpPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new HelpPresenterImpl(this);
    mPresenter.onCreate(getApplicationContext());
  }

  private Unbinder mUnbinder;

  @Override public void setRootView() {
    setContentView(R.layout.activity_help);
    mUnbinder = ButterKnife.bind(this);
  }

  @BindView(R.id.loading_progressbar) ProgressBar mProgressBar;

  @Override public void showLoading() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    mProgressBar.setVisibility(View.GONE);
  }

  @BindView(R.id.textview) TextView mTextView;

  @Override public void setHelpText(String text) {
    if (mTextView != null) {
      mTextView.setText(text);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }

  @Override public void onInit() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(R.string.activity_help_label);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public void showToast(int resId) {
    Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
  }

  @Override public void showToast(String msg) {
    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void activityFinish() {
    onBackPressed();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    mPresenter.onOptionsItemSelected(item.getItemId());
    return super.onOptionsItemSelected(item);
  }

  @Override public Context getContext() {
    return this;
  }
}
