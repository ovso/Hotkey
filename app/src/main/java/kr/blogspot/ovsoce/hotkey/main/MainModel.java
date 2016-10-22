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

    MainModel(Context context) {
        super(context);
    }

    public String getPlayStoreUrl() {
        return URL_PLAYSTORE;
    }
    public String getReviewUrl() {
        return URL_REVIEW;
    }

    public void setFontsSize() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        float fonts_size = Float.parseFloat(sharedPreferences.getString("fonts_size", "1.0"));
        Log.d("fonts_size = " + fonts_size);
        TypefaceUtil.fontsSize(mContext, fonts_size);
    }
    @Override
    public String getVersionName() {
        return new StringBuilder(mContext.getString(R.string.app_ver))
                .append(super.getVersionName()).toString();
    }

    public String getAppCode() {
        return AD_ID_CAULY;
    }
}
