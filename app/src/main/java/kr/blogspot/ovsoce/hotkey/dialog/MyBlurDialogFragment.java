package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        bundle.putSerializable("listener", listener);

        fragment.setArguments(bundle);

        return fragment;
    }
    private View mContentView;
    private ItemAlertDialogBuilder mAlertDialogBuilder;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("");
        //return super.onCreateDialog(savedInstanceState);
        mAlertDialogBuilder = new ItemAlertDialogBuilder(null);
        mContentView = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
        mAlertDialogBuilder.setView(mContentView);
        mAlertDialogBuilder.setTitle(R.string.dialog_title);
        mAlertDialogBuilder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
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
                if (update > 0) {
                    ((OnBlurDialogDismissListener) getArguments().getSerializable("listener")).onDismiss(nowItem.getId());
                } else {

                }

            }
        });
        mAlertDialogBuilder.setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        mAlertDialogBuilder.setNeutralButton("neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("presenter = " + mPresenter);
                mPresenter.pickContacts(getActivity());
            }
        });
        setCancelable(false);
        return mAlertDialogBuilder.show();
    }

    private DialogPresenter mPresenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("");
        super.onActivityCreated(savedInstanceState);
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
    public final static int REQUEST_CODE_PIC_CONTACTS = 0X100;
    @Override
    public void navigateToContacts(Intent intent) {
        startActivityForResult(intent, REQUEST_CODE_PIC_CONTACTS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("");
        if(resultCode == getActivity().RESULT_OK) {
            if(requestCode == REQUEST_CODE_PIC_CONTACTS) {
                mPresenter.contactsResult(getActivity(), data);
            }
        }
    }

    @Override
    public void onClick(View v) {
        mPresenter.setColorSelected((int) v.getTag(), mContentView.findViewById(R.id.scroll_container));
    }

    public interface OnBlurDialogDismissListener extends Serializable{
        void onDismiss(String itemId);
    }
}