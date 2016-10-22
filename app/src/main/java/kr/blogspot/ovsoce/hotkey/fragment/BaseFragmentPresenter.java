package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

public interface BaseFragmentPresenter extends FragmentPresenter {
    interface View extends FragmentPresenter.View {

        Context getContext();
    }
}
