package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.Manifest;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import kr.blogspot.ovsoce.hotkey.R;

public class EmergencyFragmentPresenterImpl implements EmergencyFragmentPresenter {

    private EmergencyFragmentPresenter.View mView;
    private EmergencyFragmentContactModel mContactModel;
    EmergencyFragmentPresenterImpl(EmergencyFragmentPresenter.View view) {
        mView = view;
        mContactModel = new EmergencyFragmentContactModel(mView.getContext());
    }

    @Override
    public void onActivityCreate(int type) {
        mContactModel.setType(type);
        mView.setRecyclerView(mContactModel.getContactList(mContactModel.getType()));
    }

    @Override
    public void onItemClick(int position) {
        final EmergencyContact contact = mContactModel.getContact(mContactModel.getType(), position);
        new TedPermission(mView.getContext()).setPermissions(Manifest.permission.CALL_PHONE)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        mView.showMakeCallDialog(contact);
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                }).setDeniedMessage(R.string.call_phone_denied_msg).check();
    }

}
