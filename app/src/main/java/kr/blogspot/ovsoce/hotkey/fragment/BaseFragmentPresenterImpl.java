package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.common.Prefs;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;

class BaseFragmentPresenterImpl implements BaseFragmentPresenter {

    private View view;
    private BaseFragmentModel model;
    private RxPermissions permissions;

    BaseFragmentPresenterImpl(View view) {
        Context context = view.getContext();
        this.view = view;
        model = new BaseFragmentModel(context);
        permissions = new RxPermissions(view.getActivity());
    }

    @Override
    public void onActivityCreated(int position) {
        model.setTabPosition(position);
        view.setRecyclerView(model.getContactsItemList());
    }

    @Override
    public void onItemAlertDialogOkClick(String itemId) {
        ContactsItem item = model.getContactsItem(Integer.valueOf(itemId));
        view.updateRecyclerViewItem(item);
    }

    @Override
    public void onAdapterItemClick(final int position) {
        final String phoneNumber = model.getPhoneNumber(position);
        final ContactsItem item = model.getContactsItem(position);
        if (!ObjectUtils.isEmpty(phoneNumber)) {
            reqPermission(item);
        } else {
            onAdapterItemLongClick(position);
        }
    }

    private void reqPermission(final ContactsItem item) {
        permissions.request(Manifest.permission.CALL_PHONE).subscribe(granted -> {
            if (granted) { // Always true pre-M
                makeCall(item);
            } else {
                view.showPermissionAlert(R.string.call_phone_denied_msg);
            }
        });
    }

    private void makeCall(final ContactsItem item) {
        Context context = MyApplication.getInstance().getApplicationContext();
        boolean isTTS = Prefs.getBoolean(context, "tts", false);
        if (!isTTS) {
            view.makeCall(item.getNumber());
        } else {
            String message = model.getTTSString(item.getName());
            view.playTTS(message);
            view.showTTSDialog(message, (dialog, which) -> view.makeCall
                    (item.getNumber()));
        }
    }

    @Override
    public void onAdapterItemLongClick(int position) {
        ContactsItem item = model.getContactsItem(position);
        view.showItemSetDialog(item);
    }
}
