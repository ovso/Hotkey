package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.ItemAlertDialogBuilder;
import kr.blogspot.ovsoce.hotkey.fragment.adapter.MyAdapter;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public class BaseFragment extends Fragment implements BaseFragmentPresenter.View, ItemAlertDialogBuilder.OnClickListener {

    public static final String ARG_SECTION_NUMBER = "section_number";
    protected BaseFragmentPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ItemAlertDialogBuilder mItemAlertDialogBuilder;
    private Unbinder mUnbinder;

    public static BaseFragment newInstance(int sectionNumber) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ItemAlertDialogBuilder.REQUEST_CODE_PIC_CONTACTS) {
                //mPresenter.contactsResult(getActivity(), data);
                mItemAlertDialogBuilder.onAlertDialogResult(getActivity(), data);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new BaseFragmentPresenterImpl(this);
        mPresenter.init(this.getArguments().getInt(ARG_SECTION_NUMBER), mRecyclerView);

    }

    @Override
    public void initRecyclerView(MyAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showItemSetDialog(ContactsItem item) {
        mItemAlertDialogBuilder = new ItemAlertDialogBuilder(this, item);
        mItemAlertDialogBuilder.setPositiveButton(this);
        mItemAlertDialogBuilder.show();
    }

    @Override
    public void makeACall(final Intent intent) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Log.d(deniedPermissions.toString());
            }
        };
        new TedPermission(getActivity())
                .setPermissionListener(permissionListener)
                .setDeniedMessage(R.string.call_phone_denied_msg)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();

    }

    @Override
    public void updateRecyclerViewItem() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(DialogInterface dialog, String itemId) {
        mPresenter.setItemId(getActivity(), mRecyclerView, itemId);
        dialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public Context getContext() {
        return this.getActivity();
    }
}