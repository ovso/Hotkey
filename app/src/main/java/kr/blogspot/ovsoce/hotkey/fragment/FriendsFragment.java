package kr.blogspot.ovsoce.hotkey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FriendsFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return mView = inflater.inflate(R.layout.fragment_friends, null);
    }
}
