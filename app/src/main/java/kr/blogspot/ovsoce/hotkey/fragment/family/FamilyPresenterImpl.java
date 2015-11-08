package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FamilyPresenterImpl implements FamilyPresenter{
    private View mView;
    private FamilyModel mModel;
    public FamilyPresenterImpl(View view) {
        mView = view;
        mModel = new FamilyModel();
    }

    @Override
    public void init(final Context context, final RecyclerView recyclerView) {
        List<ContactsItem> list = mModel.getContactsItemList(context);

        MyAdapter adapter = new MyAdapter(list, new MyAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Intent intent = mModel.getMakeACallIntent(v.getContext(), position);
                if(intent != null) {
                    mView.makeACall(intent);
                } else {
                    //mView.showToast(mModel.getMessage(context, FragmentModel.MESSAGE_TYPE.EMPTY_NUMBER));
                    onLongClick(v);
                }
            }

            @Override
            public boolean onLongClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Log.d("position = " + position);
                ContactsItem item = mModel.getContactsItem(v.getContext(), position);
                mView.showItemSetDialog(item);
                return true;
            }
        }, recyclerView);

        mView.initRecyclerView(adapter, new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }

    @Override
    public void setItemId(Context context,RecyclerView recyclerView, String itemId) {
        ContactsItem item = mModel.getContactsItem(context, Integer.valueOf(itemId));
        MyAdapter adapter = (MyAdapter) recyclerView.getAdapter();
        adapter.setUpdateItem(item);
        mView.updateRecyclerViewItem();
    }
}
