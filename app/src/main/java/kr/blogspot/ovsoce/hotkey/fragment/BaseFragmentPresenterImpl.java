package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.content.Context;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gun0912.tedpermission.util.ObjectUtils;

import java.util.ArrayList;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

class BaseFragmentPresenterImpl implements BaseFragmentPresenter {
    private View mView;
    private BaseFragmentModel mModel;
    private BaseFragmentDatabaseManager mDbManager;

    BaseFragmentPresenterImpl(View view) {
        Context context = view.getContext();
        mView = view;
        mModel = new BaseFragmentModel(context);
        mDbManager = new BaseFragmentDatabaseManager(context);
    }

    @Override
    public void onActivityCreated(int position) {
        mModel.setTabPosition(position);
        mView.setRecyclerView(mModel.getContactsItemList());
    }

    @Override
    public void onItemAlertDialogOkClick(String itemId) {
        ContactsItem item = mModel.getContactsItem(Integer.valueOf(itemId));
        mView.updateRecyclerViewItem(item);
    }

    @Override
    public void onAdapterItemClick(final int position) {
        final String phoneNumber = mModel.getPhoneNumber(position);
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                if(!ObjectUtils.isEmpty(phoneNumber)) {
                    mView.makeCall(phoneNumber);
                } else {
                    onAdapterItemLongClick(position);
                }
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Log.d(deniedPermissions.toString());
            }
        };
        if (!ObjectUtils.isEmpty(phoneNumber)) {
            new TedPermission(mView.getContext())
                    .setPermissionListener(permissionListener)
                    .setDeniedMessage(R.string.call_phone_denied_msg)
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check();
        } else {
            onAdapterItemLongClick(position);
        }
    }

    @Override
    public void onAdapterItemLongClick(int position) {
        ContactsItem item = mModel.getContactsItem(position);
        mView.showItemSetDialog(item);
    }
}
