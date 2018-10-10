package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.framework.ad.MyAdView;

public class SettingsActivity extends AppCompatActivity implements SettingsPresenter.View {
  public final static int REQUEST_CODE_SETTING = 1;
  private SettingsPresenter mPresenter;
  private Unbinder unbinder;
  @BindView(R.id.ad_container) ViewGroup adContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    unbinder = ButterKnife.bind(this);
    /**
     * onInit에 구현하니 툴바가 나오지 않았다 HelpActivity에서는 제대로 나온다.
     */
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(R.string.action_settings);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mPresenter = new SettingsPresenterImpl(this);
    mPresenter.onCreate(getApplicationContext());
  }

  @Override
  public void onInit() {
  }

  @Override
  public void showToast(int resId) {
    Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showToast(String msg) {
    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void activityFinish() {
    onBackPressed();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    mPresenter.onOptionsItemSelected(item.getItemId());
    return super.onOptionsItemSelected(item);
  }

  @Override
  public Context getContext() {
    return this;
  }

  @Override public void showAd() {
    adContainer.addView(MyAdView.getAdmobAdView(getApplicationContext()));
  }

  @Override protected void onDestroy() {
    unbinder.unbind();
    super.onDestroy();
  }
}
