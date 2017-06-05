package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;

public class DonatePresenterImpl implements DonatePresenter {

    private View mView;
    private DonateModel mModel;
    DonatePresenterImpl(View view) {
        mView = view;
        mModel = new DonateModel(view.getContext());
    }

    @Override
    public void onCreate(Context context) {
        mView.setRootView();
        mView.setToolbar();
        mView.setWebView(mModel.getDonateUrl());
    }

}
