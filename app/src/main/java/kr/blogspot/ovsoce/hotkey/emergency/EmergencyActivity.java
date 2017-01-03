package kr.blogspot.ovsoce.hotkey.emergency;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.emergency.fragment.EmergencyFragment;

public class EmergencyActivity extends AppCompatActivity implements EmergencyPresenter.View {

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
    public void setViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EmergencyFragment.getInstance(0));
        fragmentList.add(EmergencyFragment.getInstance(1));
        fragmentList.add(EmergencyFragment.getInstance(2));
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
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
