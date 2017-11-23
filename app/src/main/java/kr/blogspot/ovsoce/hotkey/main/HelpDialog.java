package kr.blogspot.ovsoce.hotkey.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import java.util.List;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.framework.BaseAlertDialogFragment;
import kr.blogspot.ovsoce.hotkey.framework.OnMyResultListener;
import kr.blogspot.ovsoce.hotkey.network.HelpNetwork;

/**
 * Created by jaeho on 2017. 11. 24
 */

public class HelpDialog extends BaseAlertDialogFragment {
  @BindView(R.id.textview) TextView textView;

  @Override protected boolean isNegativeButton() {
    return false;
  }

  @Override protected boolean isPositiveButton() {
    return true;
  }

  HelpNetwork network;

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    network = new HelpNetwork();
    network.setOnMyResultListener(new OnMyResultListener<List<String>>() {
      @Override public void onPre() {

      }

      @Override public void onResult(List<String> results) {
        textView.setText(getHelpText(results));
      }

      @Override public void onPost() {

      }

      @Override public void onError() {

      }
    });
    network.req();
  }

  private String getHelpText(List<String> texts) {
    String helpText = "";
    for (String text : texts) {
      helpText += text + "\n\n";
    }

    return helpText;
  }

  @Override protected boolean getAttatchRoot() {
    return false;
  }

  @Override protected int getLayoutResId() {
    return R.layout.fragment_help;
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
    return view -> {
      dismiss();
    };
  }

  @Override protected View.OnClickListener onNegativeClickListener() {
    return null;
  }

  @Override public void onDetach() {
    super.onDetach();
    network.cancel();
  }
}
