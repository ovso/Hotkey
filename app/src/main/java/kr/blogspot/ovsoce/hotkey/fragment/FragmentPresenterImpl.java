package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FragmentPresenterImpl implements FragmentPresenter {
    private FragmentPresenter.View mView;
    public FragmentPresenterImpl(FragmentPresenter.View view) {
        mView = view;
    }
}
