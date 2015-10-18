package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public interface FragmentPresenter {
    void init(Context context);

    interface View {
        void initRecyclerView(MyAdapter adapter, RecyclerView.LayoutManager layoutManager);
        void onClick(android.view.View v);
    }
}
