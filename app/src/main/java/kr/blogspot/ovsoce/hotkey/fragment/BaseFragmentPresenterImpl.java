package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.content.Context;
import com.tbruyelle.rxpermissions2.RxPermissions;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;

class BaseFragmentPresenterImpl implements BaseFragmentPresenter {

  private View mView;
  private BaseFragmentModel mModel;
  private RxPermissions permissions;

  BaseFragmentPresenterImpl(View view) {
    Context context = view.getContext();
    mView = view;
    mModel = new BaseFragmentModel(context);
    permissions = new RxPermissions(view.getActivity());
  }

  @Override public void onActivityCreated(int position) {
    mModel.setTabPosition(position);
    mView.setRecyclerView(mModel.getContactsItemList());
  }

  @Override public void onItemAlertDialogOkClick(String itemId) {
    ContactsItem item = mModel.getContactsItem(Integer.valueOf(itemId));
    mView.updateRecyclerViewItem(item);
  }

  @Override public void onAdapterItemClick(final int position) {
    final String phoneNumber = mModel.getPhoneNumber(position);
    if (!ObjectUtils.isEmpty(phoneNumber)) {
      reqPermission(phoneNumber);
    } else {
      onAdapterItemLongClick(position);
    }
  }

  private void reqPermission(final String phoneNumber) {
    permissions.request(Manifest.permission.CALL_PHONE).subscribe(granted -> {
      if (granted) { // Always true pre-M
        mView.makeCall(phoneNumber);
      } else {
        mView.showPermissionAlert(R.string.call_phone_denied_msg);
      }
    });
  }

  @Override public void onAdapterItemLongClick(int position) {
    ContactsItem item = mModel.getContactsItem(position);
    mView.showItemSetDialog(item);
  }
}
