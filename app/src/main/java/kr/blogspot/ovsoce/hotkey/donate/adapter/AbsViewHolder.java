package kr.blogspot.ovsoce.hotkey.donate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

public abstract class AbsViewHolder extends RecyclerView.ViewHolder {
  public AbsViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }
}
