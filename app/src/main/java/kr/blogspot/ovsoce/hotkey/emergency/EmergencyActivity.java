package kr.blogspot.ovsoce.hotkey.emergency;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.AdaptiveBanner;
import kr.blogspot.ovsoce.hotkey.Ads;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.emergency.fragment.EmergencyFragment;

public class EmergencyActivity extends AppCompatActivity implements EmergencyPresenter.View {
  @BindView(R.id.ad_container)
  ViewGroup adContainer;
  private EmergencyPresenter mPresenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new EmergencyPresenterImpl(this);
    mPresenter.onCreate();
  }

  private Unbinder mUnbinder;

  @Override
  public void setRootView() {
    setContentView(R.layout.activity_emergency);
    mUnbinder = ButterKnife.bind(this);
  }

  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  @Override
  public void setToolbar() {
    mToolbar.setTitle(R.string.activity_emergency_label);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @BindView(R.id.viewpager)
  ViewPager mViewPager;

  @Override
  public void setViewPager(int tabCount) {
    List<Fragment> fragmentList = new ArrayList<>();
    for (int i = 0; i < tabCount; i++) {
      fragmentList.add(EmergencyFragment.getInstance(i));
    }
    EmergencyPagerAdapter adapter
      = new EmergencyPagerAdapter(getSupportFragmentManager(), fragmentList);
    mViewPager.setAdapter(adapter);
  }

  @BindView(R.id.tablayout)
  TabLayout mTabLayout;

  @Override
  public void setTabLayout() {

    mTabLayout.setupWithViewPager(mViewPager);
    mTabLayout.getTabAt(0).setText(R.string.tab_emergency_label);
    mTabLayout.getTabAt(1).setText(R.string.tab_living_info_label);
    mTabLayout.getTabAt(2).setText(R.string.tab_complaints_label);
    mTabLayout.getTabAt(3).setText(R.string.tab_child_label);
    mTabLayout.getTabAt(4).setText(R.string.tab_teen_label);
    mTabLayout.getTabAt(5).setText(R.string.tab_female_label);
    mTabLayout.getTabAt(6).setText(R.string.tab_old_disabled_label);
    mTabLayout.getTabAt(7).setText(R.string.tab_disease_addicted_label);
    mTabLayout.getTabAt(8).setText(R.string.tab_family_label);
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
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    mUnbinder.unbind();
    super.onDestroy();
  }
}
