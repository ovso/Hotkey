package kr.blogspot.ovsoce.hotkey.main;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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
import kr.blogspot.ovsoce.hotkey.navigation.NavMenuEditActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View, View.OnClickListener{
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
        setToolbarTitle(getString(R.string.app_name) + " : " + getString(R.string.menu_title_family));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_family);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_family));

        findViewById(R.id.btn_menu_edit).setOnClickListener(this);
        findViewById(R.id.fab).setVisibility(View.GONE);
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

//    public boolean onCreateOptionsMenu(Menu menu) {
//        Log.d(getClass().getSimpleName(), "oncreateOptions()");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_main_drawer, menu);
//        return true;
//    }
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
    public void navigateToNavMenuEdit() {
        Intent intent = new Intent(this, NavMenuEditActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, NavMenuEditActivity.REQUEST_CODE);
    }

    @Override
    public void replaceFragment(int containerViewId, BaseFragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
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

    @Override
    public void onClick(View v) {
        mPresenter.onClick(v);
    }
}