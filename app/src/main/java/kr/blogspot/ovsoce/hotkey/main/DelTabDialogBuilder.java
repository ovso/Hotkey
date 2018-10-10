package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import hugo.weaving.DebugLog;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.Security;
import lombok.Setter;
import lombok.experimental.Accessors;

public class DelTabDialogBuilder extends AlertDialog.Builder {

  private InterstitialAd interstitialAd;
  private AlertDialog alertDialog;

  public DelTabDialogBuilder(@NonNull Context context) {
    super(context);
    initialize();
  }

  private void initialize() {
    interstitialAd = provideInterstitialAd(getContext());
    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        super.onAdClosed();
        if (onRemoveClickListener != null) {
          onRemoveClickListener.onRemoveClick();
        }
      }
    });
    setNeutralButton(R.string.btn_del_tab, new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        if (interstitialAd.isLoaded()) {
          interstitialAd.show();
        } else {
          if (onRemoveClickListener != null) {
            onRemoveClickListener.onRemoveClick();
          }
        }
      }
    });
  }

  private InterstitialAd provideInterstitialAd(Context context) {
    InterstitialAd interstitialAd = new InterstitialAd(context);
    interstitialAd.setAdUnitId(Security.ADMOB_INTERSTITIAL_UNIT_ID.getValue());
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
    interstitialAd.loadAd(adRequestBuilder.build());
    return interstitialAd;
  }

  @Override public AlertDialog show() {
    alertDialog = super.show();
    return alertDialog;
  }

  @Setter @Accessors(chain = true) private OnRemoveClickListener onRemoveClickListener;

  public interface OnRemoveClickListener {
    void onRemoveClick();
  }
}
