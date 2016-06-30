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

    public Intent getEmailIntent(Context context) {
        Uri uri = Uri.parse(context.getString(R.string.email_uri));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.email_msg));
        return intent;
    }

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
    public CaulyAdView getCaulyAdView(Context context, CaulyAdViewListener listener) {
        CaulyAdView view;
        CaulyAdInfo info = new CaulyAdInfoBuilder(AD_ID_CAULY)
                    .effect(CaulyAdInfo.Effect.Circle.toString())
                    .build();
        view = new CaulyAdView(context);
        view.setAdInfo(info);
        view.setAdViewListener(listener);
        return view;
    }

    public void setFontsSize(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        float fonts_size = Float.parseFloat(sharedPreferences.getString("fonts_size", "1.0"));
        Log.d("fonts_size = " + fonts_size);
        TypefaceUtil.fontsSize(context, fonts_size);

    }

    public void setTabSelectedPosition(int tabSelectedPosition) {
        this.tabSelectedPosition = tabSelectedPosition;
    }

    public int getTabSelectedPosition() {
        return tabSelectedPosition;
    }

    public String getTabName(Context context, int position) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String defValue = context.getString(MainActivity.DEFAULT_TITLE_RES_ID[position]);
        return prefs.getString("tab_"+position, defValue);
    }

    public void setTabName(Context context, String name, int position) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tab_"+position, name);
        editor.apply();
    }
}
