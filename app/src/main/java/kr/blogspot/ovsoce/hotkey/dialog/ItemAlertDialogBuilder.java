package kr.blogspot.ovsoce.hotkey.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.framework.Log;
import kr.blogspot.ovsoce.hotkey.fragment.BaseFragment;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItemImpl;

public class ItemAlertDialogBuilder extends AlertDialog.Builder
    implements DialogPresenter.View, View.OnClickListener {
  private BaseFragment mBaseFragment = null;
  private DialogPresenter mPresenter;
  private ContactsItem mItem;

  @BindView(R.id.et_number) EditText mNumberEditText;
  @BindView(R.id.et_name) EditText mNameEditText;
  @BindView(R.id.btn_sms) Button mSmsButton;
  @BindView(R.id.btn_init) Button mInitButton;
  @BindView(R.id.scroll_container) ViewGroup mScrollContainer;

  public ItemAlertDialogBuilder(BaseFragment fragment, ContactsItem item) {
    super(fragment.getActivity());
    mBaseFragment = fragment;
    mItem = item;
    mPresenter = new DialogPresenterImpl(this);
    mPresenter.init(mBaseFragment.getActivity(), item);
  }

  private OnOkClickListener mOnOkClickListener = null;

  public void setOnOkButtonListener(OnOkClickListener listener) {
    mOnOkClickListener = listener;
  }

  @OnClick(R.id.btn_sms) void onClickSms(View v) {
    mPresenter.onClickSMS(v, mNumberEditText.getText().toString().trim());
  }

  @OnClick(R.id.btn_init) void onClickInit() {
    mPresenter.onClickContactsInit();
  }

  @Override public AlertDialog show() {
    final AlertDialog alertDialog = super.create();
    DialogInterface.OnClickListener emptyOnClickListener = null;

    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
        getContext().getText(R.string.dialog_import_contacts), emptyOnClickListener);
    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getContext().getText(R.string.btn_ok),
        emptyOnClickListener);
    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getContext().getText(R.string.btn_cancel),
        emptyOnClickListener);

    alertDialog.show();

    alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL)
        .setOnClickListener(new View.OnClickListener() {

          @Override public void onClick(View v) {
            mPresenter.pickContacts(getContext());
          }
        });
    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        .setOnClickListener(new View.OnClickListener() {

          @Override public void onClick(View v) {
            String name = mNameEditText.getText().toString().trim();
            String number = mNumberEditText.getText().toString().trim();

            ContactsItem oldItem = mItem;
            ContactsItemImpl nowItem = new ContactsItemImpl();
            nowItem.setId(oldItem.getId());
            nowItem.setName(name);
            nowItem.setNumber(number);
            nowItem.setColor(mScrollContainer.getTag().toString());
            nowItem.setTabPosition(oldItem.getTabPosition());

            int update = mPresenter.setContacts(getContext(), nowItem);
            Log.d("db update = " + ((update > 0) ? "ok" : "fail"));
            if (update > 0) {
              mOnOkClickListener.onClick(alertDialog, nowItem.getId());
            }
          }
        });
    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        .setOnClickListener(new View.OnClickListener() {

          @Override public void onClick(View v) {
            alertDialog.dismiss();
          }
        });

    return alertDialog;
  }

  @Override public void setContentView() {
    setView(View.inflate(getContext(), R.layout.dialog_custom, null));
  }

  @Override public AlertDialog.Builder setView(View view) {
    ButterKnife.bind(this, view);
    return super.setView(view);
  }

  @Override public void setDialogTitle(String title) {
    setTitle(title);
  }

  @Override public void initScrollView(String[] colors, int colorPosition) {

    for (int i = 0; i < colors.length; i++) {
      View scrollItem = LayoutInflater.from(getContext()).inflate(R.layout.dialog_color_item, null);
      scrollItem.setTag(i);
      scrollItem.findViewById(R.id.item_rect).setBackgroundColor(Color.parseColor(colors[i]));
      scrollItem.setOnClickListener(this);
      mScrollContainer.addView(scrollItem);
    }

    mPresenter.setColorSelected(colorPosition, mScrollContainer);
  }

  @Override public void setVisible(View v, int visible) {
    v.setVisibility(visible);
  }

  @Override public void setName(String name) {
    mNameEditText.setText(name);
  }

  @Override public void setNumber(String number) {
    mNumberEditText.setText(number);
  }

  public final static int REQUEST_CODE_PIC_CONTACTS = 0x10;

  @Override public void navigateToContacts(Intent intent) {
    mBaseFragment.startActivityForResult(intent, REQUEST_CODE_PIC_CONTACTS);
  }

  @Override public void navigateToSMS(Intent intent) {

  }

  @Override public void showToast(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void onClick(View v) {
    mPresenter.setColorSelected((int) v.getTag(), mScrollContainer);
  }

  public void onAlertDialogResult(Context context, Intent data) {
    mPresenter.contactsResult(context, data);
  }

  public interface OnOkClickListener {
    void onClick(DialogInterface dialog, String itemId);
  }

  @Override public Context getContext() {
    return super.getContext();
  }

  @Override public void setEmptyContacts() {
    mNameEditText.setText(null);
    mNumberEditText.setText(null);
  }
}
