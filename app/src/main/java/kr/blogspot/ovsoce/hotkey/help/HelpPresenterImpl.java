package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

public class HelpPresenterImpl implements HelpPresenter {

    private HelpPresenter.View mView;
    private HelpModel mModel;
    HelpPresenterImpl(HelpPresenter.View view) {
        mView = view;
        mModel = new HelpModel(view.getContext());
    }
    @Override
    public void onClick(android.view.View view) {

    }

    @Override
    public void onCreate(Context context) {
        mView.setRootView();
        mView.onInit();
        mView.initWebView(mModel.getHelpUrl());
    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == android.R.id.home) {
            mView.activityFinish();
        }
    }
}
