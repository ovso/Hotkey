package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;

import kr.blogspot.ovsoce.hotkey.common.Log;

public class HelpPresenterImpl implements HelpPresenter {
    private final static String URL = "";
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
        mView.onInit();
        String language = mModel.getLanguage(context);
        if(language.equalsIgnoreCase("ko")) {
            mView.initWebView("http://blog.naver.com/share_oneone/220701321810");
        } else {
            mView.initWebView("http://blog.naver.com/share_oneone/220701347695");
        }
        mModel.setScreenTracker("help");
    }

    @Override
    public void onOptionsItemSelected(int itemId) {
        if(itemId == android.R.id.home) {
            mView.activityFinish();
        }
    }
}
