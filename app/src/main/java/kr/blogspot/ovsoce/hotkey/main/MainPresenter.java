package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.StringRes;

import java.util.List;

public interface MainPresenter {
  void onNavigationItemSelected(int id);

  void onCreate();

  void onActivityResult(int requestCode, int resultCode, Intent data);

  void onTabSelected(int position);

  void onTabReselected(int position);

  void onTabNameEditDialogButtonClick(String tabName, int which);

  void onDestroy();

  void onAddTabClick();

  void onPhoneStateReceiver(Intent intent);

  void onTabRemoveClick();

  interface View {

    void showHelpDialog(@StringRes int resId);

    void navigateToEmergency();

    void setVersionName(String versionName);

    void navigateToSettings();

    void restart();

    void setViewPagerCurrentItem(int position);

    void showTabNameEditDialog(String name, boolean isRemoveTab);

    void setTabTitle(String name, int position);

    void showEditNameError(int resId);

    Context getContext();

    void setToolbar();

    void setDrawableLayout();

    void setListener();

    void setViewPager(int count, List<String> pageTitleList);

    void setTabLayout();

    void addTab();

    void updateViewPager(int count, List<String> pageTitleList);

    void removeTab(int tabPosition);

    void showProgressBar();

    void hideProgressBar();

    void exitApp();

    void showAd();
  }
}
