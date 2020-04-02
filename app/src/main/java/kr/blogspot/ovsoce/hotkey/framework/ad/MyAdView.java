package kr.blogspot.ovsoce.hotkey.framework.ad;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import kr.blogspot.ovsoce.hotkey.Ads;

public class MyAdView {

  public static AdView getAdmobAdView(Context context) {
    AdView adView = new AdView(context);
    adView.setAdSize(AdSize.SMART_BANNER);
    adView.setAdUnitId(Ads.BANNER_UNIT_ID);
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
    return adView;
  }
}
