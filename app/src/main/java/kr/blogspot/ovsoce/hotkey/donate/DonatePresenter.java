package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;

interface DonatePresenter {

  void onCreate(Context context);

  interface View {
    Context getContext();

    void setRootView();

    void setToolbar();

    void setRecyclerView();

    void refresh();
  }
}
