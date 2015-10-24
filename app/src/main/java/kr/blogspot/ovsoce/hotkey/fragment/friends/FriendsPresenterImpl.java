package kr.blogspot.ovsoce.hotkey.fragment.friends;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by ovso on 2015. 10. 24..
 */
public class FriendsPresenterImpl implements FriendsPresenter {
    private FriendsPresenter.View mView;
    private FriendsModel mModel;
    public FriendsPresenterImpl(FriendsPresenter.View view) {
        mView = view;
        mModel = new FriendsModel();
    }

    @Override
    public void init(Context context) {
        List<ContactsItem> list = mModel.getContactsItemList(context);
        mView.initRecyclerView(new MyAdapter(list,mView), new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }

}
