package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.R;

public class MainModel extends Model {

    public final static String URL_PLAYSTORE = "https://play.google.com/store/apps/details?id=kr.blogspot.ovsoce.hotkey";
    public final static String URL_REVIEW = "market://details?id=kr.blogspot.ovsoce.hotkey";
    public final static String AD_ID_CAULY = "V2f5YVvL";

    public Intent getEmailIntent(Context context) {
        Uri uri = Uri.parse(context.getString(R.string.email_uri));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.email_msg));
        return intent;
    }
/*
    public int getFragmentContainerViewId() {
        return R.id.fragment_container;
    }
*/
    public Intent getShareIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, URL_PLAYSTORE);
        intent.setType("text/plain");

        return Intent.createChooser(intent, context.getString(R.string.share_to_others));
    }
    public Intent getReviewIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL_REVIEW));
        return intent;
    }
    public CaulyAdView getCaulyAdView(Context context) {
        CaulyAdView view;
        CaulyAdInfo info = new CaulyAdInfoBuilder(AD_ID_CAULY)
                    .effect(CaulyAdInfo.Effect.Circle.toString())
                    .build();
        view = new CaulyAdView(context);
        view.setAdInfo(info);
        return view;
    }
}
