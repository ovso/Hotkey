package kr.blogspot.ovsoce.hotkey.fragment.others;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by ovso on 2015. 10. 24..
 */
public class OthersPresenterImpl implements OthersPresenter {
    private OthersPresenter.View mView;
    private OthersModel mModel;
    public OthersPresenterImpl(OthersPresenter.View view) {
        mView = view;
        mModel = new OthersModel();
    }

    @Override
    public void init(Context context) {
        List<ContactsItem> list = mModel.getContactsItemList(context);
        mView.initRecyclerView(new MyAdapter(list,mView), new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }
}
