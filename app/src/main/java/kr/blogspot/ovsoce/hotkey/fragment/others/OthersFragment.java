package kr.blogspot.ovsoce.hotkey.fragment.others;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class OthersFragment extends BaseFragment implements OthersPresenter.View {
    protected OthersPresenter mPresenter;
    protected RecyclerView mRecyclerView;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_others, null);
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new OthersPresenterImpl(this);
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
        Toast.makeText(getActivity(), "onClick id = " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View v) {
        int id = mRecyclerView.getChildLayoutPosition(v);
        Toast.makeText(getActivity(), "onLongClick id = " + id, Toast.LENGTH_SHORT).show();
    }

}
