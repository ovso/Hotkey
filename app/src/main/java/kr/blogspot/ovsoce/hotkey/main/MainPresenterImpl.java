package kr.blogspot.ovsoce.hotkey.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.settings.SettingsActivity;

class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View mView;
    private MainModel mModel;
    private MainDBManager mDBManager;
    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel(mView.getContext());

        mDBManager = new MainDBManager(mView.getContext());
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
        mModel.setTabSelectedPosition(position);
    }

    @Override
    public void onTabReselected(int position) {
        if(position == mModel.getTabSelectedPosition()) {
            String name = mModel.getTabName(position);
            mView.showEditNameDialog(name, position);
        }
    }
    @Override
    public void onClickEditNameOk(String name, int position) {
        if(name.length()<1) {
            String tabName = mModel.getTabName(position);
            mView.showEditNameDialog(tabName, position);
            //mView.showToast(R.string.empty_input_text);
            mView.showEditNameError(R.string.empty_input_text);
        } else {
            mModel.setTabName(name, position);
            String tabName = mModel.getTabName(position);
            mView.setTabTitle(tabName, position);
        }

    }

    @Override
    public void addTab() {

    }
}
