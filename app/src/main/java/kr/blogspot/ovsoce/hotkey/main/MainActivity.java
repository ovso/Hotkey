package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.help.HelpActivity;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private MainPresenter mPresenter;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();

    }

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    public void setListener() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public void setViewPager() {

        List<Fragment> fragmentList = new ArrayList<>();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @Override
    public void setTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //mViewPager.setCurrentItem(tab.getPosition(), true);
                mPresenter.onTabSelected(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mPresenter.onTabReselected(tab.getPosition());
            }
        });

    }

    @Override
    public void addTab() {
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_content_add));
    }

    @Override
    public void setDrawableLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mPresenter.onNavigationItemSelected(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void navigateToShare(String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.setType("text/plain");

        intent = Intent.createChooser(intent, getString(R.string.share_to_others));
        startActivity(intent);
    }

    @Override
    public void navigateToReview(String reviewUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(reviewUrl));

        startActivity(intent);
    }

    @Override
    public void navigateToHelp() {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void navigateToSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //startActivity(intent);
        startActivityForResult(intent, SettingsActivity.REQUEST_CODE_SETTING);
    }

    @OnClick(R.id.add_tab_button)
    void onAddTabClick() {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onAddTabClick();
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .setMessage(R.string.do_you_want_to_add_a_tab)
                .show();
    }

    @Override
    public void restart() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void setViewPagerCurrentItem(int position) {
        mViewPager.setCurrentItem(position, true);
    }


    private View mTabNameEditDialogView;
    @Override
    public void showTabNameEditDialog(String name) {
        mTabNameEditDialogView = getLayoutInflater().inflate(R.layout.dialog_custom_edit_tab_name, null);
        final EditText nameEdit = (EditText) mTabNameEditDialogView.findViewById(R.id.et_edit_name);
        nameEdit.setText(name);
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_edit_name)
                .setIcon(android.R.drawable.ic_menu_edit)
                .setView(mTabNameEditDialogView)
                .setPositiveButton(R.string.btn_ok, mOnTabNameEditDialogClickListener)
                .setNegativeButton(R.string.btn_cancel, mOnTabNameEditDialogClickListener)
                .setNeutralButton(R.string.btn_del_tab, mOnTabNameEditDialogClickListener)
                .show();
    }

    private DialogInterface.OnClickListener mOnTabNameEditDialogClickListener
            = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            EditText nameEdit = (EditText) mTabNameEditDialogView.findViewById(R.id.et_edit_name);
            String name = nameEdit.getText().toString().trim();
            mPresenter.onTabNameEditDialogButtonClick(name, which);
        }
    };
    @Override
    public void setTabTitle(String name, int position) {
        mTabLayout.getTabAt(position).setText(name);
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
        TextInputLayout t = (TextInputLayout) mTabNameEditDialogView.findViewById(R.id.til_edit_tab_name);
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
        ((TextView)mNavigationView.getHeaderView(0).findViewById(R.id.tv_version)).setText(versionName);
    }

    @Override
    public void setAd(String appCode) {

        CaulyAdView view;
        CaulyAdInfo info = new CaulyAdInfoBuilder(appCode)
                .effect(CaulyAdInfo.Effect.Circle.toString())
                .build();
        view = new CaulyAdView(this);
        view.setAdInfo(info);
        view.setAdViewListener(caulyAdViewListener);


        ViewGroup adContainer = (ViewGroup)findViewById(R.id.ad_container);
        adContainer.addView(view);
    }
    private CaulyAdViewListener caulyAdViewListener = new CaulyAdViewListener() {
        @Override
        public void onReceiveAd(CaulyAdView caulyAdView, boolean b) {

        }

        @Override
        public void onFailedToReceiveAd(CaulyAdView caulyAdView, int i, String s) {
            caulyAdView.reload();
        }

        @Override
        public void onShowLandingScreen(CaulyAdView caulyAdView) {

        }

        @Override
        public void onCloseLandingScreen(CaulyAdView caulyAdView) {

        }
    };

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            return BaseFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return DEFAULT_TITLE_RES_ID.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTabItemName(position);
        }

        private SharedPreferences getSharedPreferences(){
            return PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        private String getTabItemName(int position) {
            SharedPreferences prefs = getSharedPreferences();
            String key = "tab_"+position;
            String defValue = getString(DEFAULT_TITLE_RES_ID[position]);

            return prefs.getString(key, defValue);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mUnbinder.unbind();
    }

    public final static int[] DEFAULT_TITLE_RES_ID = {
            R.string.menu_title_family,R.string.menu_title_friends,R.string.menu_title_others};
}