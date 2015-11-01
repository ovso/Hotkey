package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import java.io.Serializable;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.dialog.lib.BlurDialogFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-10-27.
 */
public class MyBlurDialogFragment extends BlurDialogFragment implements DialogPresenter.View, View.OnClickListener {

    public static MyBlurDialogFragment getInstance(ContactsItem item, OnBlurDialogDismissListener listener) {
        MyBlurDialogFragment fragment = new MyBlurDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        bundle.putSerializable("dismiss", listener);

        fragment.setArguments(bundle);

        return fragment;
    }
    private View mContentView;
    private AlertDialog.Builder mAlertDialogBuilder;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        Log.d("here ");
        mAlertDialogBuilder = new AlertDialog.Builder(getActivity());
        mContentView = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        mAlertDialogBuilder.setView(mContentView);
        mAlertDialogBuilder.setTitle(R.string.dialog_title);
        mAlertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = ((EditText) mContentView.findViewById(R.id.et_name)).getText().toString().trim();
                String number = ((EditText) mContentView.findViewById(R.id.et_number)).getText().toString().trim();

                Log.d("tag = " + mContentView.findViewById(R.id.scroll_container).getTag());

                ContactsItem oldItem = (ContactsItem) getArguments().getSerializable("item");
                ContactsItemImpl nowItem = new ContactsItemImpl();
                nowItem.setId(oldItem.getId());
                nowItem.setName(name);
                nowItem.setNumber(number);
                nowItem.setColor(mContentView.findViewById(R.id.scroll_container).getTag().toString());
                nowItem.setMenuType(oldItem.getMenuType());

                int update = mPresenter.setContacts(getActivity(), nowItem);
                Log.d("update = " + update);
                if(update > 0) {
                    ((OnBlurDialogDismissListener)getArguments().getSerializable("dismiss")).onDismiss(nowItem.getId());
                } else {

                }


            }
        });
        mAlertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        setCancelable(false);
        return mAlertDialogBuilder.create();
    }

    private GridView mColorGridView;
    private DialogPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("here ");
        mPresenter = new DialogPresenterImpl(this);
        mPresenter.init(this);
    }

    @Override
    protected float getDownScaleFactor() {
        return 8f;//super.getDownScaleFactor();
    }

    @Override
    protected int getBlurRadius() {
        return 8;//super.getBlurRadius();
    }

    @Override
    protected boolean isDimmingEnable() {
        return super.isDimmingEnable();
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        return super.isRenderScriptEnable();
    }

    @Override
    public void initScrollView(String[] colors, int colorPosition) {
        ViewGroup scrollContainer = (ViewGroup) mContentView.findViewById(R.id.scroll_container);

        for (int i = 0; i < colors.length; i++) {
            View scrollItem = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color_item, null);
            View line = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_color_item_line, null);
            scrollItem.setTag(i);
            scrollItem.findViewById(R.id.item_rect).setBackgroundColor(Color.parseColor(colors[i]));
            scrollItem.setOnClickListener(this);
            scrollContainer.addView(scrollItem);
        }

        Log.d("colorPosition = " + colorPosition);
        mPresenter.setColorSelected(colorPosition, mContentView.findViewById(R.id.scroll_container));

        //mPresenter.setColorSelected();
    }

    @Override
    public void setVisible(View v, int visible) {
        v.setVisibility(visible);
    }

    @Override
    public void setName(String name) {
        ((EditText)mContentView.findViewById(R.id.et_name)).setText(name);
    }

    @Override
    public void setNumber(String number) {
        ((EditText)mContentView.findViewById(R.id.et_number)).setText(number);
    }


    @Override
    public void onClick(View v) {
        mPresenter.setColorSelected((int) v.getTag(), mContentView.findViewById(R.id.scroll_container));
    }

    public interface OnBlurDialogDismissListener extends Serializable {
        void onDismiss(String itemId);
    }
}