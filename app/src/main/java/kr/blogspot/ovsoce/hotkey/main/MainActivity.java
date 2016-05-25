package kr.blogspot.ovsoce.hotkey.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.help.HelpActivity;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());

    }
    @Override
    public void onInit() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbarTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //findViewById(R.id.btn_menu_edit).setOnClickListener(this);
        findViewById(R.id.fab).setVisibility(View.GONE);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        initTab();
    }

    private void initTab() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_options_menu, menu);
        return true;
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mPresenter.onNavigationItemSelected(getApplicationContext(), item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void navigateToEmail(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void navigateToShare(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void navigateToReview(Intent intent) {
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

    @Override
    public void showEditNameDialog(int position) {
        new EditNameAlertDialogBuilder(this, R.layout.dialog_custom_edit_tab_name)
                .setTitle(R.string.dialog_title_edit_name)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setToolbarTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    @Override
    public void setVersionName(String versionName) {
        ((TextView)findViewById(R.id.tv_version)).setText(versionName);
    }

    @Override
    public void initAd(CaulyAdView view) {
        ViewGroup adContainer = (ViewGroup)findViewById(R.id.ad_container);
        adContainer.addView(view);
    }

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
    private final static int[] DEFAULT_TITLE_RES_ID = {
            R.string.menu_title_family,R.string.menu_title_friends,R.string.menu_title_others};
}