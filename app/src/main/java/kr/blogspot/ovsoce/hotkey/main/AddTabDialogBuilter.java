package kr.blogspot.ovsoce.hotkey.main;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.Security;
import lombok.Setter;
import lombok.experimental.Accessors;

public class AddTabDialogBuilter extends AlertDialog.Builder {
  public DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
    @Override public void onClick(DialogInterface dialog, int which) {
      switch (which) {
        case DialogInterface.BUTTON_POSITIVE:
          if (onOkClickListener != null) {
            if (interstitialAd != null && interstitialAd.isLoaded()) {
              interstitialAd.show();
            } else {
              onOkClickListener.onOkClick();
            }
          }
          break;
        case DialogInterface.BUTTON_NEGATIVE:
          dialog.dismiss();
          break;
      }
    }
  };
  private InterstitialAd interstitialAd;

  public AddTabDialogBuilter(@NonNull Context context) {
    super(context);
    initialize();
  }

  public AddTabDialogBuilter(@NonNull Context context, int themeResId) {
    super(context, themeResId);
    initialize();
  }

  private void initialize() {
    setPositiveButton(R.string.btn_ok, onClickListener);
    setNegativeButton(R.string.btn_cancel, onClickListener);
    setMessage(R.string.do_you_want_to_add_a_tab);
    interstitialAd = provideInterstitialAd(getContext());
    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        super.onAdClosed();
        if (onOkClickListener != null) {
          onOkClickListener.onOkClick();
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

  @Setter @Accessors(chain = true) private OnOkClickListener onOkClickListener;

  public interface OnOkClickListener {
    void onOkClick();
  }
}
