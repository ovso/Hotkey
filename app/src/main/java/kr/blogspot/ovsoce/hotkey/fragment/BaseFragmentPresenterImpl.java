package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.fragment.adapter.MyAdapter;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

class BaseFragmentPresenterImpl implements BaseFragmentPresenter{
    private View mView;
    private BaseFragmentModel mModel;
    BaseFragmentPresenterImpl(View view) {
        mView = view;
        mModel = new BaseFragmentModel(mView.getContext());
    }

    @Override
    public void init(int sectionNumber, final RecyclerView recyclerView) {
        mModel.setMenuId(sectionNumber);

        List<ContactsItem> list = mModel.getContactsItemList();

        MyAdapter adapter = new MyAdapter(list, new MyAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Intent intent = mModel.getMakeACallIntent(position);
                if(intent != null) {
                    mView.makeACall(intent);
                } else {
                    onLongClick(v);
                }
            }

            @Override
            public boolean onLongClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                ContactsItem item = mModel.getContactsItem(position);
                mView.showItemSetDialog(item);
                return true;
            }
        });

        mView.initRecyclerView(adapter, new GridLayoutManager(mView.getContext(), mModel.getGridLayoutSpanCount()));
    }

    @Override
    public void setItemId(Context context,RecyclerView recyclerView, String itemId) {
        ContactsItem item = mModel.getContactsItem(Integer.valueOf(itemId));
        MyAdapter adapter = (MyAdapter) recyclerView.getAdapter();
        adapter.setUpdateItem(item);
        mView.updateRecyclerViewItem();
    }
}
