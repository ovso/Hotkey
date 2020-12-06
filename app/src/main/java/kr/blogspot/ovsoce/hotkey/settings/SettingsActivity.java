package kr.blogspot.ovsoce.hotkey.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.AdaptiveBanner;
import kr.blogspot.ovsoce.hotkey.R;

public class SettingsActivity extends AppCompatActivity implements SettingsPresenter.View {
  public final static int REQUEST_CODE_SETTING = 1;
  private SettingsPresenter mPresenter;
  private Unbinder unbinder;
  @SuppressLint("NonConstantResourceId")
  @BindView(R.id.ad_container)
  ViewGroup adContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    unbinder = ButterKnife.bind(this);
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle(R.string.action_settings);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

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

  @Override
  public void showAd() {
    AdaptiveBanner.loadAdaptiveBanner(this, adContainer);
  }

  @Override
  protected void onDestroy() {
    unbinder.unbind();
    super.onDestroy();
  }
}
