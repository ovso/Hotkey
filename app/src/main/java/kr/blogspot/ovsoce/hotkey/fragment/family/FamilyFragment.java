package kr.blogspot.ovsoce.hotkey.fragment.family;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.MyBlurDialogFragment;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.MyAdapter;

/**
 * Created by jaeho_oh on 2015-10-16.
 */
public class FamilyFragment extends BaseFragment implements FamilyPresenter.View, MyBlurDialogFragment.OnBlurDialogDismissListener {

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
        MyBlurDialogFragment fragment = MyBlurDialogFragment.getInstance(item, this);
        fragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void hideItemSetDialog() {

    }

    @Override
    public void makeACall(Intent intent) {

        if( Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            Intent i = intent;// intnet가 연속으로 와야 전화가 걸린다?
            if(getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, R.layout.activity_main);
            } else {
                startActivity(i);
            }
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDismiss(String itemId) {
        mPresenter.setItemId(getActivity(),mRecyclerView, itemId);
    }

    @Override
    public void updateRecyclerViewItem() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}