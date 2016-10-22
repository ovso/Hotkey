package kr.blogspot.ovsoce.hotkey.main;

import android.app.Activity;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;
import com.gun0912.tedpermission.util.ObjectUtils;

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
        if(menuId == R.id.nav_share) {
            mView.navigateToShare(mModel.getShareIntent());
        } else if(menuId == R.id.nav_review) {
            mView.navigateToReview(mModel.getReviewIntent());
        } else if(menuId == R.id.nav_help) {
            //mView.replaceFragment(mModel.getFragmentContainerViewId(), mModel.getFragment(menuId));
            //mView.setToolbarTitle(context.getString(R.string.app_name) + " : " + mModel.getToolbarTitle(context, menuId));
            mView.navigateToHelp();
        } else if(menuId == R.id.nav_settings) {
            mView.navigateToSettings();
        }
    }

    @Override
    public void onCreate() {
        mView.setVersionName(mModel.getVersionName());
        mModel.setFontsSize();
        mView.setToolbar();
        mView.setDrawableLayout();
        mView.setListener();
        mView.setViewPager();
        mView.setTabLayout();
        mView.initAd(mModel.getCaulyAdView(caulyAdViewListener));

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
        mTabManager.setTabSelectedPosition(position);
    }

    @Override
    public void onTabReselected(int position) {
        if(position == mTabManager.getTabSelectedPosition()) {
            String name = mDBManager.getTabName(position);
            mView.showTabNameEditDialog(name);
        }
    }

    @Override
    public void onTabNameEditDialogButtonClick(String tabName, int which) {
        if(which == TabManager.BUTTON_TYPE_OK) {
            if(ObjectUtils.isEmpty(tabName)) {
                String oldTabName = mDBManager.getTabName(mTabManager.getTabSelectedPosition());
                mView.showTabNameEditDialog(oldTabName);
                mView.showEditNameError(R.string.empty_input_text);
            } else {
                mDBManager.setTabName(tabName, mTabManager.getTabSelectedPosition());
                mView.setTabTitle(tabName, mTabManager.getTabSelectedPosition());
            }
        } else if(which == TabManager.BUTTON_TYPE_DEL) {
            Log.d("");
        } else {
            Log.d("cancel");
        }
    }

}
