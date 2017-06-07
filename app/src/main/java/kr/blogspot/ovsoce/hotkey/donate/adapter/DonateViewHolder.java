package kr.blogspot.ovsoce.hotkey.donate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.blogspot.ovsoce.hotkey.R;

public class DonateViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.donate_imageview) ImageView donateImageview;

  public DonateViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
