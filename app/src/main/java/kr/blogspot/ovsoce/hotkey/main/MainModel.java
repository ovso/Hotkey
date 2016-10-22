package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.fsn.cauly.CaulyAdViewListener;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.common.TypefaceUtil;

public class MainModel extends Model {

    public final static String URL_PLAYSTORE = "https://play.google.com/store/apps/details?id=kr.blogspot.ovsoce.hotkey";
    public final static String URL_REVIEW = "market://details?id=kr.blogspot.ovsoce.hotkey";
    public final static String AD_ID_CAULY = "V2f5YVvL";
    private Context fonts;
    private int tabSelectedPosition;

    MainModel(Context context) {
        super(context);
    }
    public Intent getEmailIntent() {
        Uri uri = Uri.parse(mContext.getString(R.string.email_uri));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_TEXT, mContext.getString(R.string.email_msg));
        return intent;
    }

    public Intent getShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_TEXT, URL_PLAYSTORE);
        intent.setType("text/plain");

        return Intent.createChooser(intent, mContext.getString(R.string.share_to_others));
    }
    public Intent getReviewIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL_REVIEW));
        return intent;
    }
    public CaulyAdView getCaulyAdView(CaulyAdViewListener listener) {
        CaulyAdView view;
        CaulyAdInfo info = new CaulyAdInfoBuilder(AD_ID_CAULY)
                    .effect(CaulyAdInfo.Effect.Circle.toString())
                    .build();
        view = new CaulyAdView(mContext);
        view.setAdInfo(info);
        view.setAdViewListener(listener);
        return view;
    }

    public void setFontsSize() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        float fonts_size = Float.parseFloat(sharedPreferences.getString("fonts_size", "1.0"));
        Log.d("fonts_size = " + fonts_size);
        TypefaceUtil.fontsSize(mContext, fonts_size);
    }

    public String getVersionName() {
        return new StringBuilder(mContext.getString(R.string.app_ver))
                .append(getAppVersionName()).toString();
    }
}
