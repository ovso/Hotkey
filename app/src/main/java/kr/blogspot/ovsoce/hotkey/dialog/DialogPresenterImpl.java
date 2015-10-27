package kr.blogspot.ovsoce.hotkey.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import kr.blogspot.ovsoce.hotkey.R;

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
    public void setSelected(int position, android.view.View container) {
        ViewGroup group = (ViewGroup) container;
        int itemCount = group.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            mView.setVisible(group.getChildAt(i).findViewById(R.id.item_rect_select), android.view.View.GONE);
        }

        mView.setVisible(group.getChildAt(position).findViewById(R.id.item_rect_select), android.view.View.VISIBLE);

    }
}
