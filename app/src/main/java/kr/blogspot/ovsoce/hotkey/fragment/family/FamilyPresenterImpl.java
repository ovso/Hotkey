package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

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
    public void init(Context context) {
        List<ContactsItem> list = mModel.getContactsItemList(context);
        mView.initRecyclerView(new MyAdapter(list,mView), new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }
}
