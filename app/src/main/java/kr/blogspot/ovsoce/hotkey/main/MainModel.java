package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import com.gun0912.tedpermission.util.ObjectUtils;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.common.Prefs;
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

    public boolean isAppExit(Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (!ObjectUtils.isEmpty(state) && state.equals("IDLE")) {
            return Prefs.getBoolean(mContext, "auto_end", false);
        } else {
            return false;
        }
    }
}
