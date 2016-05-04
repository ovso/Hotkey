package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.fsn.cauly.CaulyAdView;

public interface FragmentPresenter{
    void init(Context context, int menuId, RecyclerView recyclerView);
    void setItemId(Context context, RecyclerView recyclerView, String itemId);
    interface View {
        void initRecyclerView(MyAdapter adapter, RecyclerView.LayoutManager layoutManager);
        void showItemSetDialog(ContactsItem item);
        void makeACall(Intent intent);
        void updateRecyclerViewItem();
    }
}
