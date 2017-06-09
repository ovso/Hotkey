package kr.blogspot.ovsoce.hotkey.donate.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import kr.blogspot.ovsoce.hotkey.R;

class DonateViewHolder extends AbsViewHolder {
  @BindView(R.id.donate_imageview) ImageView donateImageview;
  @BindView(R.id.year_textview) TextView yearTextview;
  DonateViewHolder(View itemView) {
    super(itemView);
  }
}
