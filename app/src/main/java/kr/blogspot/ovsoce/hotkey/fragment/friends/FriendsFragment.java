package kr.blogspot.ovsoce.hotkey.fragment.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by ovso on 2015. 10. 24..
 */
public class FriendsFragment extends BaseFragment implements FriendsPresenter.View {

    protected FriendsPresenter mPresenter;
    protected RecyclerView mRecyclerView;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_friends, null);
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new FriendsPresenterImpl(this);
        mPresenter.init(getActivity(), mRecyclerView);
    }

    @Override
    public void initRecyclerView(MyAdapter adapter,RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showItemSetDialog(ContactsItem item) {
        Log.d("name = " + item.getName());
    }

    @Override
    public void hideItemSetDialog() {

    }

    @Override
    public void makeACall(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void updateRecyclerViewItem() {

    }
}
