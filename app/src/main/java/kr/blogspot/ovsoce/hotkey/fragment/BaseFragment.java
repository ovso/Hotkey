package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import java.util.Arrays;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.ItemAlertDialogBuilder;
import kr.blogspot.ovsoce.hotkey.fragment.adapter.MyAdapter;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public class BaseFragment extends Fragment implements BaseFragmentPresenter.View, ItemAlertDialogBuilder.OnClickListener{

    public static final String ARG_SECTION_NUMBER = "section_number";

    public static BaseFragment newInstance(int sectionNumber) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private ItemAlertDialogBuilder mItemAlertDialogBuilder;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK) {
            if(requestCode == ItemAlertDialogBuilder.REQUEST_CODE_PIC_CONTACTS) {
                //mPresenter.contactsResult(getActivity(), data);
                mItemAlertDialogBuilder.onAlertDialogResult(getActivity(), data);
            }
        }
    }

    protected BaseFragmentPresenter mPresenter;
    protected RecyclerView mRecyclerView;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_base, null);
        return mView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);

        mPresenter = new BaseFragmentPresenterImpl(this);
        mPresenter.init(getActivity(), this.getArguments().getInt(ARG_SECTION_NUMBER), mRecyclerView);
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
    private final static int REQUEST_CODE_PERMISSIONS_CALL_PHONE = 1;
    @Override
    public void makeACall(final Intent intent) {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent i = intent;
                startActivity(i);
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
/*
        if( Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            Intent i = intent;// intnet가 연속으로 와야 전화가 걸린다?
            if(getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_PERMISSIONS_CALL_PHONE);
            } else {
                startActivity(i);
            }
        } else {
            startActivity(intent);
        }

 */

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
}