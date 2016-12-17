package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

public interface BaseFragmentPresenter extends FragmentPresenter {
    void onAdapterItemClick(int position);

    void onAdapterItemLongClick(int position);

    interface View extends FragmentPresenter.View {

        Context getContext();
    }
}
