package kr.blogspot.ovsoce.hotkey.fragment.others;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by ovso on 2015. 10. 24..
 */
public class OthersPresenterImpl implements OthersPresenter{
    private OthersPresenter.View mView;
    private OthersModel mModel;
    public OthersPresenterImpl(OthersPresenter.View view) {
        mView = view;
        mModel = new OthersModel();
    }

    @Override
    public void init(Context context, final RecyclerView recyclerView) {
        List<ContactsItem> list = mModel.getContactsItemList(context);
        MyAdapter adapter = new MyAdapter(list, new MyAdapter.OnAdapterItemClickListener() {
            @Override
            public void onClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(v.getContext(), position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onLongClick(android.view.View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                ContactsItem item = mModel.getContactsItem(v.getContext(), position);
                return true;
            }
        }, recyclerView);
        mView.initRecyclerView(adapter, new GridLayoutManager(context, mModel.getGridLayoutSpanCount(context)));
    }

    @Override
    public void setItemId(Context context, RecyclerView recyclerView, String itemId) {

    }

}
