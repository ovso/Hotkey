package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;

public interface MainPresenter {
    void onNavigationItemSelected(int id);
    void onCreate();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onTabSelected(int position);

    void onTabReselected(int position);

    void onTabNameEditDialogButtonClick(String tabName, int which);

    void onDestroy();

    void onAddTabClick();

    interface View {
        void navigateToShare(String playStoreUrl);
        void navigateToReview(String reviewUrl);
        void navigateToHelp();
        void setVersionName(String versionName);
        void setAd(String appCode);

        void navigateToSettings();

        void restart();

        void setViewPagerCurrentItem(int position);

        void showTabNameEditDialog(String name);

        void setTabTitle(String name, int position);

        void showToast(int resId);
        void showToast(String msg);

        void showEditNameError(int resId);

        Context getContext();

        void setToolbar();

        void setDrawableLayout();

        void setListener();

        void setViewPager(int count);

        void setTabLayout();

        void addTab();

        void updateViewPager(int count);
    }
}
