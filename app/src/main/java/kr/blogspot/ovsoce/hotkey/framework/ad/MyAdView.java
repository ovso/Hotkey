package kr.blogspot.ovsoce.hotkey.framework.ad;

import android.content.Context;
import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import kr.blogspot.ovsoce.hotkey.Security;

public class MyAdView {

  public static AdView getAdmobAdView(Context context) {
    AdView adView = new AdView(context);
    adView.setAdSize(AdSize.SMART_BANNER);
    adView.setAdUnitId(Security.ADMOB_UNIT_ID.getValue());
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
    return adView;
  }

  public static CaulyAdView getCaulyAdView(Context context) {
    CaulyAdView view;
    CaulyAdInfo info =
        new CaulyAdInfoBuilder(Security.CAULY_APP_CODE.getValue()).effect(
            CaulyAdInfo.Effect.Circle.toString())
            .build();
    view = new CaulyAdView(context);
    view.setAdInfo(info);
    return view;
  }
}