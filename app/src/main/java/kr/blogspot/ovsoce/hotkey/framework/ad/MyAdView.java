package kr.blogspot.ovsoce.hotkey.framework.ad;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import kr.blogspot.ovsoce.hotkey.R;

public class MyAdView {

  public static AdView getAdmobAdView(Context context) {
    AdView adView = new AdView(context);
    adView.setAdSize(AdSize.SMART_BANNER);
    adView.setAdUnitId(context.getString(R.string.ads_unit_id_banner));
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
    return adView;
  }
}
