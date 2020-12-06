package kr.blogspot.ovsoce.hotkey.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.dialog.ItemAlertDialogBuilder;
import kr.blogspot.ovsoce.hotkey.fragment.adapter.MyAdapter;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public class BaseFragment extends Fragment implements BaseFragmentPresenter.View {

  private static final String ARG_SECTION_NUMBER = "position";
  private BaseFragmentPresenter mPresenter;

  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerView;

  private ItemAlertDialogBuilder mItemAlertDialogBuilder;
  private Unbinder mUnbinder;
  private MyAdapter.OnAdapterItemClickListener mOnAdapterItemClickListener =
    new MyAdapter.OnAdapterItemClickListener() {

      @Override
      public void onClick(android.view.View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);
        mPresenter.onAdapterItemClick(position);
      }

      @Override
      public boolean onLongClick(android.view.View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);
        mPresenter.onAdapterItemLongClick(position);
        return true;
      }
    };
  private ItemAlertDialogBuilder.OnOkClickListener mOnOkClickListener =
    new ItemAlertDialogBuilder.OnOkClickListener() {
      @Override
      public void onClick(DialogInterface dialog, String itemId) {
        mPresenter.onItemAlertDialogOkClick(itemId);
        dialog.dismiss();
      }
    };

  public static BaseFragment newInstance(int sectionNumber) {
    BaseFragment fragment = new BaseFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == Activity.RESULT_OK) {
      if (requestCode == ItemAlertDialogBuilder.REQUEST_CODE_PIC_CONTACTS) {
        mItemAlertDialogBuilder.onAlertDialogResult(getActivity(), data);
      }
    }
  }

  @Override
  public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_base, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mUnbinder = ButterKnife.bind(this, view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mPresenter = new BaseFragmentPresenterImpl(this);
    if (getArguments() != null) {
      mPresenter.onActivityCreated(getArguments().getInt(ARG_SECTION_NUMBER));
    }
  }

  @Override
  public void setRecyclerView(List<ContactsItem> contactsItemList) {
    MyAdapter adapter = new MyAdapter(contactsItemList, mOnAdapterItemClickListener);
    mRecyclerView.setAdapter(adapter);
  }

  @Override
  public void showItemSetDialog(ContactsItem item) {
    mItemAlertDialogBuilder = new ItemAlertDialogBuilder(this, item);
    mItemAlertDialogBuilder.setOnOkButtonListener(mOnOkClickListener);
    mItemAlertDialogBuilder.show();
  }

  @Override
  public void makeCall(final String phoneNumber) {
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:" + phoneNumber));
    startActivity(intent);
  }

  @Override
  public void onDestroyView() {
    dispose();
    mUnbinder.unbind();
    mPresenter.onDestroyView();
    super.onDestroyView();
  }

  @Override
  public void updateRecyclerViewItem(ContactsItem item) {
    MyAdapter adapter = (MyAdapter) mRecyclerView.getAdapter();
    if (adapter != null) {
      adapter.setUpdateItem(item);
      adapter.notifyDataSetChanged();
    }
  }

  private void dispose() {
    MyAdapter adapter = (MyAdapter) mRecyclerView.getAdapter();
    if (adapter != null) {
      adapter.onDetach();
    }
  }

  @Override
  public Context getContext() {
    return getActivity();
  }

  @Override
  public void showPermissionAlert(int resId) {
    new AlertDialog.Builder(requireActivity())
      .setMessage(resId)
      .setPositiveButton(
        android.R.string.ok, (dialogInterface, which) -> dialogInterface.dismiss())
      .show();
  }
}
