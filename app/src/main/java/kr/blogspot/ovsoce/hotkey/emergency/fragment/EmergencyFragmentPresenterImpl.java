package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.Manifest;
import com.tbruyelle.rxpermissions2.RxPermissions;
import kr.blogspot.ovsoce.hotkey.R;

public class EmergencyFragmentPresenterImpl implements EmergencyFragmentPresenter {

  private EmergencyFragmentPresenter.View mView;
  private EmergencyFragmentContactModel mContactModel;
  private RxPermissions permissions;

  EmergencyFragmentPresenterImpl(EmergencyFragmentPresenter.View view) {
    mView = view;
    mContactModel = new EmergencyFragmentContactModel(mView.getContext());
    permissions = new RxPermissions(view.getActivity());
  }

  @Override public void onActivityCreate(int type) {
    mContactModel.setType(type);
    mView.setRecyclerView(mContactModel.getContactList(mContactModel.getType()));
  }

  @Override public void onItemClick(int position) {
    final EmergencyContact contact = mContactModel.getContact(mContactModel.getType(), position);
    reqPermission(contact);
  }

  private void reqPermission(EmergencyContact contact) {
    permissions.request(Manifest.permission.CALL_PHONE).subscribe(granted -> {
      if (granted) { // Always true pre-M
        mView.showMakeCallDialog(contact);
      } else {
        // Oups permission denied
        mView.showPermissionAlert(R.string.call_phone_denied_msg);
      }
    });
  }
}