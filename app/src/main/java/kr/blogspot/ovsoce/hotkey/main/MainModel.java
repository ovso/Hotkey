package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.FamilyFragment;
import kr.blogspot.ovsoce.hotkey.fragment.FriendsFragment;
import kr.blogspot.ovsoce.hotkey.fragment.OthersFragment;
import kr.blogspot.ovsoce.hotkey.fragment.WhoFragment;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class MainModel {
    public Intent getEmailIntent(Context context) {
        Uri uri = Uri.parse(context.getString(R.string.email_uri));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.email_msg));
        return intent;
    }
    public int getFragmentContainerViewId() {
        return R.id.fragment_container;
    }
    private FamilyFragment mFamilyFragment;
    private FriendsFragment mFriendsFragment;
    private OthersFragment mOthersFragment;
    private WhoFragment mWhoFragment;
    public BaseFragment getFragment(int id) {
        BaseFragment baseFragment = null;
        if (id == R.id.nav_family) {
            if(mFamilyFragment == null) {
                baseFragment = new FamilyFragment();
            } else {
                baseFragment = mFamilyFragment;
            }
        } else if (id == R.id.nav_friends) {
            if(mFriendsFragment == null) {
                baseFragment = new FriendsFragment();
            } else {
                baseFragment = mFriendsFragment;
            }
        } else if (id == R.id.nav_others) {
            if(mOthersFragment == null) {
                baseFragment = new OthersFragment();
            } else {
                baseFragment = mOthersFragment;
            }
        } else if (id == R.id.nav_who) {
            if(mWhoFragment == null) {
                baseFragment = new WhoFragment();
            } else {
                baseFragment = mWhoFragment;
            }
        }

        return baseFragment;
    }
}