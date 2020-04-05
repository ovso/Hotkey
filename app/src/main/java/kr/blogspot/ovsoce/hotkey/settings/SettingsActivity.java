package kr.blogspot.ovsoce.hotkey.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.AdaptiveBanner;
import kr.blogspot.ovsoce.hotkey.Ads;
import kr.blogspot.ovsoce.hotkey.R;

public class SettingsActivity extends AppCompatActivity implements SettingsPresenter.View {
  public final static int REQUEST_CODE_SETTING = 1;
  private SettingsPresenter mPresenter;
  private Unbinder unbinder;
  @BindView(R.id.ad_container)
  ViewGroup adContainer;
  private InterstitialAd interstitialAd = AdaptiveBanner.loadInterstitial(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    unbinder = ButterKnife.bind(this);
    /**
     * onInit에 구현하니 툴바가 나오지 않았다 HelpActivity에서는 제대로 나온다.
     */
    Toolbar toolbar = findViewById(R.id.toolbar);
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

  @Override
  public void showAd() {
    AdaptiveBanner.loadAdaptiveBanner(this, adContainer, Ads.BANNER_UNIT_ID);
  }

  @Override
  protected void onDestroy() {
    unbinder.unbind();
    super.onDestroy();
  }

  @Override
  public void onBackPressed() {
    interstitialAd.setAdListener(new AdListener() {
      @Override
      public void onAdLoaded() {
        super.onAdLoaded();
        interstitialAd.show();
      }

      @Override
      public void onAdFailedToLoad(int i) {
        super.onAdFailedToLoad(i);
        onBackPressed();
      }

      @Override
      public void onAdClosed() {
        super.onAdClosed();
        onBackPressed();
      }
    });
  }
}
