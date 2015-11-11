package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.ContactsItemImpl;

/**
 * Created by jaeho_oh on 2015-11-11.
 */
public class ItemAlertDialogBuilder extends AlertDialog.Builder implements DialogPresenter.View, View.OnClickListener {
    private BaseFragment mBaseFragment = null;
    private View mContentView;
    private DialogPresenter mPresenter;
    private ContactsItem mItem;
    public ItemAlertDialogBuilder(BaseFragment fragment, ContactsItem item) {
        super(fragment.getActivity());

        mBaseFragment = fragment;
        mContentView = View.inflate(getContext(), R.layout.dialog_custom, null);
        setView(mContentView);
        mItem = item;
        mPresenter = new DialogPresenterImpl(this);
        mPresenter.init(getContext(), item);
    }
    public ItemAlertDialogBuilder(Context context) {
        super(context);
    }
    private ItemAlertDialogBuilder.OnClickListener mPositiveButtonListener = null;

    public void setPositiveButton(ItemAlertDialogBuilder.OnClickListener listener) {
        mPositiveButtonListener = listener;
    }

    @Override
    public AlertDialog show() {
        final AlertDialog alertDialog = super.create();

        DialogInterface.OnClickListener emptyOnClickListener = null;

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getContext().getText(R.string.dialog_import_contacts), emptyOnClickListener);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getText(R.string.btn_ok), emptyOnClickListener);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getContext().getText(R.string.btn_cancel), emptyOnClickListener);

        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d("AlertDialog.BUTTON_NEUTRAL");
                mPresenter.pickContacts(getContext());
            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d("AlertDialog.BUTTON_POSITIVE");
                String name = ((EditText) mContentView.findViewById(R.id.et_name)).getText().toString().trim();
                String number = ((EditText) mContentView.findViewById(R.id.et_number)).getText().toString().trim();

                Log.d("tag = " + mContentView.findViewById(R.id.scroll_container).getTag());

                ContactsItem oldItem = mItem;
                ContactsItemImpl nowItem = new ContactsItemImpl();
                nowItem.setId(oldItem.getId());
                nowItem.setName(name);
                nowItem.setNumber(number);
                nowItem.setColor(mContentView.findViewById(R.id.scroll_container).getTag().toString());
                nowItem.setMenuType(oldItem.getMenuType());

                int update = mPresenter.setContacts(getContext(), nowItem);
                Log.d("db pdate = " + ((update>0)?"ok":"fail"));
                if (update > 0) {
                    mPositiveButtonListener.onClick(alertDialog, nowItem.getId());
                } else {

                }


            }
        });
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d("AlertDialog.BUTTON_NEGATIVE");
                alertDialog.dismiss();
            }
       });


        return alertDialog;
    }

    @Override
    public void initScrollView(String[] colors, int colorPosition) {
        ViewGroup scrollContainer = (ViewGroup) mContentView.findViewById(R.id.scroll_container);

        for (int i = 0; i < colors.length; i++) {
            View scrollItem = LayoutInflater.from(getContext()).inflate(R.layout.dialog_color_item, null);
            //View line = LayoutInflater.from(getContext()).inflate(R.layout.dialog_color_item_line, null);
            scrollItem.setTag(i);
            scrollItem.findViewById(R.id.item_rect).setBackgroundColor(Color.parseColor(colors[i]));
            scrollItem.setOnClickListener(this);
            scrollContainer.addView(scrollItem);
        }

        mPresenter.setColorSelected(colorPosition, mContentView.findViewById(R.id.scroll_container));
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

    public final static int REQUEST_CODE_PIC_CONTACTS = 0x10;
    @Override
    public void navigateToContacts(Intent intent) {
        //mNeutralButtonListener.onClick(alertDialog, AlertDialog.BUTTON_NEUTRAL);
//        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//        mBaseFragment.startActivityForResult(intent, MyBlurDialogFragment.REQUEST_CODE_PIC_CONTACTS);
        mBaseFragment.startActivityForResult(intent, REQUEST_CODE_PIC_CONTACTS);
    }

    @Override
    public void onClick(View v) {
        mPresenter.setColorSelected((int) v.getTag(), mContentView.findViewById(R.id.scroll_container));
    }

    public interface OnClickListener {
        void onClick(DialogInterface dialog, String itemId);
    }
}
