package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import kr.blogspot.ovsoce.hotkey.R;

public class DelTabDialogBuilder extends AlertDialog.Builder {

  private InterstitialAd interstitialAd;
  @SuppressWarnings("FieldCanBeLocal")
  private AlertDialog alertDialog;

  DelTabDialogBuilder(@NonNull Context context) {
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
    interstitialAd.setAdUnitId(context.getString(R.string.ads_unit_id_interstitial));
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
    interstitialAd.loadAd(adRequestBuilder.build());
    return interstitialAd;
  }

  @Override public AlertDialog show() {
    alertDialog = super.show();
    return alertDialog;
  }

  public OnRemoveClickListener onRemoveClickListener;

  public void setOnRemoveClickListener(OnRemoveClickListener l) {
    onRemoveClickListener = l;
  }
  public interface OnRemoveClickListener {
    void onRemoveClick();
  }
}
