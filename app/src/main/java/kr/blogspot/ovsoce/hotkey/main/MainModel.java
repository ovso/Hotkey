package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;

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

    public BaseFragment getFragment(int id) {
        BaseFragment baseFragment = null;
        if (id == R.id.nav_family) {
            if(mFamilyFragment == null) {
                mFamilyFragment = new FamilyFragment();
            }
            baseFragment = mFamilyFragment;
        } else if (id == R.id.nav_friends) {
            if(mFriendsFragment == null) {
                mFriendsFragment = new FriendsFragment();
            }
            baseFragment = mFriendsFragment;
        } else if (id == R.id.nav_others) {
            if(mOthersFragment == null) {
                mOthersFragment = new OthersFragment();
            }
            baseFragment = mOthersFragment;
        }

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
    private CaulyAdView mCaulyAdView;
    public CaulyAdView getCaulyAdView(Context context) {
        if( mCaulyAdView == null ) {
            CaulyAdInfo info = new CaulyAdInfoBuilder(context.getString(R.string.ad_id_cauly))
                    .effect(CaulyAdInfo.Effect.Circle.toString())
                    .build();
            mCaulyAdView = new CaulyAdView(context);
            mCaulyAdView.setAdInfo(info);
        }
        return mCaulyAdView;
    }
}
