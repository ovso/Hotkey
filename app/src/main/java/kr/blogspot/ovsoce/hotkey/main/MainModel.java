package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;

import kr.blogspot.ovsoce.hotkey.R;

public class MainModel extends Model {
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
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.url_playstore));
        intent.setType("text/plain");

        return Intent.createChooser(intent, context.getString(R.string.share_to_others));
    }
    public Intent getReviewIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(context.getString(R.string.url_review)));
        return intent;
    }
    public CaulyAdView getCaulyAdView(Context context) {
        CaulyAdView view;
        CaulyAdInfo info = new CaulyAdInfoBuilder(context.getString(R.string.ad_id_cauly))
                    .effect(CaulyAdInfo.Effect.Circle.toString())
                    .build();
        view = new CaulyAdView(context);
        view.setAdInfo(info);
        return view;
    }
}
