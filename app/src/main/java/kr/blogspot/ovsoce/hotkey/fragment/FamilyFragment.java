package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FamilyFragment extends BaseFragment implements FragmentPresenter.View{
    FragmentPresenter mPresenter;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_family, null);
        return mView;
    }
    private RecyclerView mRecyclerView;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new FragmentPresenterImpl(this);
        mPresenter.init(getActivity());
    }

    @Override
    public void initRecyclerView(MyAdapter adapter,RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = mRecyclerView.getChildLayoutPosition(v);
        Toast.makeText(getActivity(), "id = " + id, Toast.LENGTH_SHORT).show();
    }


}
