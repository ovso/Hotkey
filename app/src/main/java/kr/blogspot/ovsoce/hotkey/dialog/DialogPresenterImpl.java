package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;
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
    public void init(Context context) {
        mView.initScrollView(mModel.getDefaultColors(context));
    }

    @Override
    public void setColorSelected(int colorPosition, android.view.View container) {
        ViewGroup group = (ViewGroup) container;
        int itemCount = group.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            mView.setVisible(group.getChildAt(i).findViewById(R.id.item_rect_select), android.view.View.GONE);
        }

        mView.setVisible(group.getChildAt(colorPosition).findViewById(R.id.item_rect_select), android.view.View.VISIBLE);
        container.setTag(colorPosition);
    }

    @Override
    public void setContacts(Context context, String id, String name, String number, int colorPosition, int menuType) {
        String color = mModel.getDefaultColors(context)[colorPosition];
        ContactsItemImpl item = new ContactsItemImpl(id,name, number, color);
        item.setMenuType(menuType);
        mModel.getDatabaseHelper(context).updateContact(item);
    }
}
