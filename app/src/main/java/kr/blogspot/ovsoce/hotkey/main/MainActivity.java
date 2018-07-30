package kr.blogspot.ovsoce.hotkey.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.App;
import kr.blogspot.ovsoce.hotkey.emergency.EmergencyActivity;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.framework.MyProgressDialog;
import kr.blogspot.ovsoce.hotkey.framework.ad.MyAdView;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {
  public final static int[] DEFAULT_TITLE_RES_ID = {
      R.string.menu_title_family, R.string.menu_title_friends, R.string.menu_title_others
  };
  @BindView(R.id.nav_view)
  NavigationView mNavigationView;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.viewpager)
  ViewPager mViewPager;
  @BindView(R.id.tabs)
  TabLayout mTabLayout;
  @BindView(R.id.ad_container) ViewGroup adContainer;
  private SectionsPagerAdapter mSectionsPagerAdapter;
  private MainPresenter mPresenter;
  private Unbinder mUnbinder;
  private TabLayout.OnTabSelectedListener mOnTabSelectedListener =
      new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
          mPresenter.onTabSelected(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
          mPresenter.onTabReselected(tab.getPosition());
        }
      };
  private MyProgressDialog mProgressBar;
  private View mTabNameEditDialogView;
  private DialogInterface.OnClickListener mOnTabNameEditDialogClickListener =
      new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          EditText nameEdit = mTabNameEditDialogView.findViewById(R.id
              .et_edit_name);
          String name = nameEdit.getText().toString().trim();
          mPresenter.onTabNameEditDialogButtonClick(name, which);
        }
      };
  private BroadcastReceiver mPhoneStateBroadcastReceiver = new BroadcastReceiver() {
    public void onReceive(Context context, Intent intent) {
      mPresenter.onPhoneStateReceiver(intent);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new MainPresenterImpl(this);
    mPresenter.onCreate();

    registerReceiver(mPhoneStateBroadcastReceiver,
        new IntentFilter("android.intent.action.PHONE_STATE"));
  }

  @Override
  protected void attachBaseContext(Context newBase) {
    App.setFont();
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override
  public void setListener() {
    mNavigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void setToolbar() {
    setSupportActionBar(mToolbar);
  }

  @Override
  public void setViewPager(int count, List<String> pageTitleList) {
    List<Fragment> fragmentList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      fragmentList.add(BaseFragment.newInstance(i));
    }
    mSectionsPagerAdapter =
        new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList, pageTitleList);
    mViewPager.setAdapter(mSectionsPagerAdapter);
  }

  @Override
  public void updateViewPager(int count, List<String> pageTitleList) {
    List<Fragment> fragmentList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      fragmentList.add(BaseFragment.newInstance(i));
    }
    mSectionsPagerAdapter.updateFragmentList(fragmentList, pageTitleList);
    mSectionsPagerAdapter.notifyDataSetChanged();
  }

  @Override
  public void setTabLayout() {
    mTabLayout.setupWithViewPager(mViewPager);
    mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    mTabLayout.addOnTabSelectedListener(mOnTabSelectedListener);
  }

  @Override
  public void addTab() {
    mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_content_add));
  }

  @Override
  public void removeTab(int tabPosition) {
    mTabLayout.removeTabAt(tabPosition);
  }

  @Override
  public void showProgressBar() {
    mProgressBar = MyProgressDialog.show(this, null, null);
  }

  @Override
  public void hideProgressBar() {
    if (mProgressBar != null) {
      mProgressBar.dismiss();
    }
  }

  @Override
  public void setRootView() {
    setContentView(R.layout.activity_main);
    mUnbinder = ButterKnife.bind(this);
  }

  @Override
  public void setDrawableLayout() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    mPresenter.onNavigationItemSelected(item.getItemId());
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public void showHelpDialog(@StringRes int resId) {
    new AlertDialog.Builder(getContext()).setTitle(R.string.help)
        .setMessage(resId)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override
  public void navigateToEmergency() {
    Intent intent = new Intent(this, EmergencyActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  @Override
  public void navigateToSettings() {
    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    startActivityForResult(intent, SettingsActivity.REQUEST_CODE_SETTING);
  }

  @OnClick(R.id.add_tab_button)
  void onAddTabClick() {
    new AddTabDialogBuilter(this).setOnOkClickListener(new AddTabDialogBuilter.OnOkClickListener() {
      @Override public void onOkClick() {
        mPresenter.onAddTabClick();
      }
    }).show();
  }

  @Override
  public void restart() {
    finish();
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  @Override
  public void setViewPagerCurrentItem(int position) {
    mViewPager.setCurrentItem(position, true);
  }

  @Override
  public void showTabNameEditDialog(String name, boolean isRemoveTab) {
    mTabNameEditDialogView =
        getLayoutInflater().inflate(R.layout.dialog_custom_edit_tab_name, null);
    final EditText nameEdit = mTabNameEditDialogView.findViewById(R.id.et_edit_name);
    nameEdit.setText(name);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(R.string.dialog_title_edit_name);
    builder.setIcon(android.R.drawable.ic_menu_edit);
    builder.setView(mTabNameEditDialogView);
    builder.setPositiveButton(R.string.btn_ok, mOnTabNameEditDialogClickListener);
    builder.setNegativeButton(R.string.btn_cancel, mOnTabNameEditDialogClickListener);
    if (isRemoveTab) {
      builder.setNeutralButton(R.string.btn_del_tab, mOnTabNameEditDialogClickListener);
    }
    builder.show();
  }

  @Override
  public void setTabTitle(String name, int position) {
    TabLayout.Tab tab = mTabLayout.getTabAt(position);
    if (tab != null) {
      tab.setText(name);
    }
  }

  @Override
  public void showToast(int resId) {
    Toast.makeText(MainActivity.this, resId, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showToast(String msg) {
    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showEditNameError(int resId) {
    TextInputLayout t =
        mTabNameEditDialogView.findViewById(R.id.til_edit_tab_name);
    t.setError(getString(resId));
  }

  @Override
  public Context getContext() {
    return this;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mPresenter.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void setVersionName(String versionName) {
    ((TextView) mNavigationView.getHeaderView(0).findViewById(R.id.tv_version)).setText(
        versionName);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.onDestroy();
    mUnbinder.unbind();

    unregisterReceiver(mPhoneStateBroadcastReceiver);
  }

  @Override
  public void exitApp() {
    finish();
  }

  @Override public void showAd() {
    adContainer.addView(MyAdView.getAdmobAdView(getApplicationContext()));
  }

  private class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mPageTitleList;

    SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragmentList,
        List<String> pageTitleList) {
      super(fm);
      mFragmentList = fragmentList;
      mPageTitleList = pageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mPageTitleList.get(position);
    }

    void updateFragmentList(List<Fragment> fragmentList, List<String> pageTitleList) {
      mFragmentList = fragmentList;
      mPageTitleList = pageTitleList;
    }
  }
}