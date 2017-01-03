package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;

public class EmergencyFragment extends Fragment implements EmergencyFragmentPresenter.View {

    public static Fragment getInstance(int index) {
        Fragment f = new EmergencyFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    private Unbinder mUnbinder;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    private EmergencyFragmentPresenter mPresenter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new EmergencyFragmentPresenterImpl(this);
        mPresenter.onActivityCreate(getArguments().getInt("index"));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUnbinder.unbind();
    }
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void setRecyclerView(List<EmergencyContact> contactList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        EmergencyFragmentAdapter adapter = new EmergencyFragmentAdapter(contactList);
        mRecyclerView.setAdapter(adapter);
    }
}
