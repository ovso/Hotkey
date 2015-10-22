package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.common.ContactsItem;
import kr.blogspot.ovsoce.hotkey.common.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FragmentPresenterImpl implements FragmentPresenter{
    private FragmentPresenter.View mView;
    private FragmentModel mModel;
    public FragmentPresenterImpl(FragmentPresenter.View view) {
        mView = view;
        mModel = new FragmentModel();
    }

    @Override
    public void init(Context context) {
        mModel.init(context);

        List<ContactsItem> list = mModel.getContactsItemListData();
        mView.initRecyclerView(new MyAdapter(list,mView), new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }
}
