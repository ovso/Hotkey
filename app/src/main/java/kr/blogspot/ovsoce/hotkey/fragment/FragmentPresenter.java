package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public interface FragmentPresenter{
    void init(Context context, RecyclerView recyclerView);
    void setItemId(Context context, RecyclerView recyclerView, String itemId);
    interface View {
        void initRecyclerView(MyAdapter adapter, RecyclerView.LayoutManager layoutManager);
        void showItemSetDialog(ContactsItem item);
        void hideItemSetDialog();
        void makeACall(Intent intent);
        void updateRecyclerViewItem();
        //void showToast(String msg);
    }
}
