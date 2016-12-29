package kr.blogspot.ovsoce.hotkey.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gun0912.tedpermission.util.ObjectUtils;

import java.util.ArrayList;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View mView;
    private MainModel mModel;
    private MainDBManager mDBManager;
    private TabManager mTabManager;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel(mView.getContext());
        mDBManager = new MainDBManager(mView.getContext());
        mTabManager = new TabManager(mView.getContext(), mDBManager);
    }
    @Override
    public void onNavigationItemSelected(int menuId) {
        if (menuId == R.id.nav_share) {
            mModel.setSendEventTracker("share");
            mView.navigateToShare(mModel.getPlayStoreUrl());
        } else if (menuId == R.id.nav_review) {
            mModel.setSendEventTracker("review");
            mView.navigateToReview(mModel.getReviewUrl());
        } else if (menuId == R.id.nav_help) {
            mView.navigateToHelp();
        } else if (menuId == R.id.nav_settings) {
            mView.navigateToSettings();
        }
    }

    @Override
    public void onCreate() {
        mView.setRootView();
        mView.setVersionName(mModel.getVersionName());
        mModel.setFontsSize();
        mView.setToolbar();
        mView.setDrawableLayout();
        mView.setListener();
        int tabCount = mDBManager.getTabCount();
        mView.setViewPager(tabCount, mDBManager.getPageTitleList(tabCount));
        mView.setTabLayout();
        mView.setAd(mModel.getAppCode());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SettingsActivity.REQUEST_CODE_SETTING) {
                if (data != null) {
                    if (data.getBooleanExtra("restart", false)) {
                        mView.restart();
                    }
                }
            }
        }
    }

    @Override
    public void onTabSelected(int position) {
        mView.setViewPagerCurrentItem(position);
        mTabManager.setTabSelectedPosition(position);
    }

    @Override
    public void onTabReselected(int position) {
        if (position == mTabManager.getTabSelectedPosition()) {
            String name = mDBManager.getTabName(position);
            mView.showTabNameEditDialog(name, mTabManager.isRemoveTab());
        }
    }

    @Override
    public void onTabNameEditDialogButtonClick(String tabName, int which) {
        if (which == TabManager.BUTTON_TYPE_OK) {
            if (ObjectUtils.isEmpty(tabName)) {
                String oldTabName = mDBManager.getTabName(mTabManager.getTabSelectedPosition());
                mView.showTabNameEditDialog(oldTabName, mTabManager.isRemoveTab());
                mView.showEditNameError(R.string.empty_input_text);
            } else {
                mDBManager.setTabName(tabName, mTabManager.getTabSelectedPosition());
                mView.setTabTitle(tabName, mTabManager.getTabSelectedPosition());
            }
        } else if (which == TabManager.BUTTON_TYPE_DEL) {
            boolean deleted = mDBManager.deleteTable(mTabManager.getTabSelectedPosition());
            if (deleted) {
                mView.removeTab(mTabManager.getTabSelectedPosition());
                int tabCount = mDBManager.getTabCount();
                mView.updateViewPager(tabCount, mDBManager.getPageTitleList(tabCount));
                mView.setTabLayout();
            }
        } else {
            Log.d("cancel");
        }
    }

    @Override
    public void onAddTabClick() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mView.showProgressBar();
            }

            @Override
            protected Void doInBackground(Void... params) {
                if (mDBManager.createTable()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mView.addTab();
                            int tabCount = mDBManager.getTabCount();
                            mView.updateViewPager(tabCount, mDBManager.getPageTitleList(tabCount));
                            mView.setTabLayout();
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mView.hideProgressBar();
            }
        }.execute();

    }

    @Override
    public void onPhoneStateReceiver(Intent intent) {
        if (mModel.isAppExit(intent)) {
            mView.exitApp();
        }
    }

    @Override
    public void onDestroy() {

    }

}
