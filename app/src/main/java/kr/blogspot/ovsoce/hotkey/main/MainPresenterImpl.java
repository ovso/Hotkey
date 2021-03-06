package kr.blogspot.ovsoce.hotkey.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import kr.blogspot.ovsoce.hotkey.App;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.data.KeyName;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;
import kr.blogspot.ovsoce.hotkey.framework.Prefs;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

class MainPresenterImpl implements MainPresenter {
  private MainPresenter.View mView;
  private MainModel mModel;
  private MainDBManager mDBManager;
  private TabManager mTabManager;
  private AsyncTask<Void, Void, Void> addTabTask;

  MainPresenterImpl(MainPresenter.View view) {
    mView = view;
    mModel = new MainModel(mView.getContext());
    mDBManager = new MainDBManager(mView.getContext());
    mTabManager = new TabManager(mView.getContext(), mDBManager);
  }

  @Override
  public void onNavigationItemSelected(int menuId) {
    if (menuId == R.id.nav_help) {
      mView.showHelpDialog(R.string.help_msg);
    } else if (menuId == R.id.nav_settings) {
      mView.navigateToSettings();
    } else if (menuId == R.id.nav_emergency) {
      mView.navigateToEmergency();
    }
  }

  @Override
  public void onCreate() {
    mView.setVersionName(mModel.getVersionName());
    mModel.setFontsSize();
    mView.setToolbar();
    mView.setDrawableLayout();
    mView.setListener();
    int tabCount = mDBManager.getTabCount();
    mView.setViewPager(tabCount, mDBManager.getPageTitleList(tabCount));
    mView.setTabLayout();
    mView.showAd();

    mView.setViewPagerCurrentItem(
        Prefs.getInt(App.getInstance(), KeyName.Prefs.VIEW_PAGER_POSITION.getValue(), 0));
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
    }
  }

  @Override
  public void onAddTabClick() {
    addTabTask = new AddTabTask(mView, mDBManager);
    addTabTask.execute();
  }

  @Override
  public void onPhoneStateReceiver(Intent intent) {
    if (mModel.isAppExit(intent)) {
      mView.exitApp();
    }
  }

  @Override
  public void onTabRemoveClick() {
    boolean deleted = mDBManager.deleteTable(mTabManager.getTabSelectedPosition());
    if (deleted) {
      mView.removeTab(mTabManager.getTabSelectedPosition());
      int tabCount = mDBManager.getTabCount();
      mView.updateViewPager(tabCount, mDBManager.getPageTitleList(tabCount));
      mView.setTabLayout();
    }
  }

  @Override
  public void onDestroy() {
    if (addTabTask != null) {
      addTabTask.cancel(true);
    }
  }

  private static class AddTabTask extends AsyncTask<Void, Void, Void> {
    private View view;
    private MainDBManager dbManager;

    AddTabTask(View view, MainDBManager dbManager) {
      this.view = view;
      this.dbManager = dbManager;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      view.showProgressBar();
    }

    @Override
    protected Void doInBackground(Void... voids) {
      if (dbManager.createTable()) {
        new Handler(Looper.getMainLooper())
            .post(
                () -> {
                  view.addTab();
                  int tabCount = dbManager.getTabCount();
                  view.updateViewPager(tabCount, dbManager.getPageTitleList(tabCount));
                  view.setTabLayout();
                });
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      view.hideProgressBar();
    }
  }
}
