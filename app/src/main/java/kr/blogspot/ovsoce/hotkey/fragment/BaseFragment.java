package kr.blogspot.ovsoce.hotkey.fragment;

import android.app.Fragment;
import android.content.Intent;

import kr.blogspot.ovsoce.hotkey.dialog.ItemAlertDialogBuilder;

/**
 * Created by jaeho_oh on 2015-10-13.
 */
public class BaseFragment extends Fragment{
    protected ItemAlertDialogBuilder mItemAlertDialogBuilder;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == getActivity().RESULT_OK) {
            if(requestCode == ItemAlertDialogBuilder.REQUEST_CODE_PIC_CONTACTS) {
                //mPresenter.contactsResult(getActivity(), data);
                mItemAlertDialogBuilder.onAlertDialogResult(getActivity(), data);
            }
        }
    }
}
