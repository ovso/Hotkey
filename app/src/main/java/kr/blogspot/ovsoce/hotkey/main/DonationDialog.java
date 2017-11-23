package kr.blogspot.ovsoce.hotkey.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.framework.BaseAlertDialogFragment;
import kr.blogspot.ovsoce.hotkey.framework.GlideApp;
import kr.blogspot.ovsoce.hotkey.framework.OnMyResultListener;
import kr.blogspot.ovsoce.hotkey.network.AppTextNetwork;
import kr.blogspot.ovsoce.hotkey.network.model.AppTextContent;

/**
 * Created by jaeho on 2017. 11. 23
 */

public class DonationDialog extends BaseAlertDialogFragment {

  @BindView(R.id.imageview) ImageView imageview;
  @BindView(R.id.textview) TextView textview;

  @Override protected boolean isNegativeButton() {
    return false;
  }

  @Override protected boolean isPositiveButton() {
    return true;
  }

  private AppTextNetwork network;

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    network = new AppTextNetwork();
    network.req();
    network.setOnMyResultListener(new OnMyResultListener<AppTextContent>() {
      @Override public void onPre() {

      }

      @Override public void onResult(AppTextContent result) {
        GlideApp.with(DonationDialog.this).load(result.getDonation_img_url()).into(imageview);
        textview.setText(result.getDonation());
      }

      @Override public void onPost() {

      }

      @Override public void onError() {

      }
    });
  }

  @Override protected boolean getAttatchRoot() {
    return false;
  }

  @Override protected int getLayoutResId() {
    return R.layout.fragment_donation;
  }

  @Override protected ViewGroup getInflateRoot() {
    return null;
  }

  @Override protected boolean isDialogCancelable() {
    return true;
  }

  @Override protected int getTitle() {
    return R.string.empty;
  }

  @Override protected View.OnClickListener onPositiveClickListener() {
    return view -> dismiss();
  }

  @Override protected View.OnClickListener onNegativeClickListener() {
    return null;
  }

  @Override public void onDetach() {
    super.onDetach();
    if (network != null) {
      network.cancel();
    }
  }
}
