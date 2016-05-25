package kr.blogspot.ovsoce.hotkey.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

public class MainPresenterImpl implements MainPresenter {
    MainPresenter.View mView;
    MainModel mModel;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void onNavigationItemSelected(Context context, int menuId) {
        if(menuId == R.id.nav_share) {
            mView.navigateToShare(mModel.getShareIntent(context));
        } else if(menuId == R.id.nav_review) {
            mView.navigateToReview(mModel.getReviewIntent(context));
        } else if(menuId == R.id.nav_help) {
            //mView.replaceFragment(mModel.getFragmentContainerViewId(), mModel.getFragment(menuId));
            //mView.setToolbarTitle(context.getString(R.string.app_name) + " : " + mModel.getToolbarTitle(context, menuId));
            mView.navigateToHelp();
        } else if(menuId == R.id.nav_settings) {
            mView.navigateToSettings();
        }
    }

    @Override
    public void onCreate(Context context) {
        mModel.setFontsSize(context);
        mView.onInit();
        mView.setVersionName(context.getString(R.string.app_ver)+mModel.getVersionName(context));
        mView.initAd(mModel.getCaulyAdView(context));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == SettingsActivity.REQUEST_CODE_SETTING) {
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
        mModel.setTabSelectedPosition(position);
    }

    @Override
    public void onTabReselected(Context context, int position) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String name = prefs.getString("tab_"+position,
                context.getString(MainActivity.DEFAULT_TITLE_RES_ID[position]));


        if(position == mModel.getTabSelectedPosition()) {
            mView.showEditNameDialog(name, position);
        }
    }

    @Override
    public void onClickEditNameOk(Context context, String name, int position) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("tab_"+position, name);
        editor.apply();
        mView.setTabTitle(name, position);
    }
}
