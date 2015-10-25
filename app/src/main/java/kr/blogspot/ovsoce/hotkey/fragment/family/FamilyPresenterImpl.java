package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

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
    public void init(Context context, final RecyclerView recyclerView) {
        List<ContactsItem> list = mModel.getContactsItemList(context);

        MyAdapter adapter = new MyAdapter(list, new MyAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(v.getContext(), position+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                ContactsItem item = mModel.getContactsItem(v.getContext(), position);
                mView.showItemSetDialog(item);
                return true;
            }
        }, recyclerView);

        mView.initRecyclerView(adapter, new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }

}
