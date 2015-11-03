package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.WhoFragment;
import kr.blogspot.ovsoce.hotkey.fragment.family.FamilyFragment;
import kr.blogspot.ovsoce.hotkey.fragment.friends.FriendsFragment;
import kr.blogspot.ovsoce.hotkey.fragment.others.OthersFragment;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class MainModel extends Model {
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
        }
        /*else if (id == R.id.nav_who) {
            if(mWhoFragment == null) {
                baseFragment = new WhoFragment();
            } else {
                baseFragment = mWhoFragment;
            }
        }*/

        return baseFragment;
    }
    public String getToolbarTitle(Context context, int menuId) {
        String title = null;
        if (menuId == R.id.nav_family) {
            title = context.getString(R.string.menu_title_family);
        } else if (menuId == R.id.nav_friends) {
            title = context.getString(R.string.menu_title_friends);
        } else if (menuId == R.id.nav_others) {
            title = context.getString(R.string.menu_title_others);
        }
        return title;
    }
    public Intent getShareIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.url_playstore));
        intent.setType("text/plain");

        return Intent.createChooser(intent, context.getString(R.string.share_to_others));
    }
    public Intent getReviewIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(context.getString(R.string.url_review)));
        return intent;
    }
}
