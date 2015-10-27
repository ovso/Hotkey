package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.MyBlurDialogFragment;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FamilyFragment extends BaseFragment implements FamilyPresenter.View{

    protected FamilyPresenter mPresenter;
    protected RecyclerView mRecyclerView;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_family, null);
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new FamilyPresenterImpl(this);
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
        new MyBlurDialogFragment().show(getFragmentManager(), "dialog");
    }

    @Override
    public void hideItemSetDialog() {

    }

    @Override
    public void makeACall(Intent intent) {
        startActivity(intent);
    }


}
