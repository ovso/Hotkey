package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class DialogPresenterImpl implements DialogPresenter {
    DialogPresenter.View mView;
    DialogModel mModel;

    public DialogPresenterImpl(DialogPresenter.View view) {
        mView = view;
        mModel = new DialogModel();
    }

    @Override
    public void init(DialogFragment fragment) {
        ContactsItem item = (ContactsItem) fragment.getArguments().getSerializable("item");
        mView.initScrollView(mModel.getDefaultColors(fragment.getActivity()), Integer.parseInt(item.getColor()));
        mView.setName(item.getName());
        mView.setNumber(item.getNumber());
    }

    @Override
    public void setColorSelected(int colorPosition, android.view.View container) {
        ViewGroup group = (ViewGroup) container;
        int itemCount = group.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            mView.setVisible(group.getChildAt(i).findViewById(R.id.item_rect_select), android.view.View.GONE);
        }

        mView.setVisible(group.getChildAt(colorPosition).findViewById(R.id.item_rect_select), android.view.View.VISIBLE);

        container.setTag(String.valueOf(colorPosition));
    }

    @Override
    public int setContacts(Context context, ContactsItem item) {
        return mModel.getDatabaseHelper(context).updateContact(item);
    }


}
