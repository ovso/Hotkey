package kr.blogspot.ovsoce.hotkey.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hugo.weaving.DebugLog;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.dialog.ItemAlertDialogBuilder;
import kr.blogspot.ovsoce.hotkey.fragment.adapter.MyAdapter;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

public class BaseFragment extends Fragment implements BaseFragmentPresenter.View {

    public static final String ARG_SECTION_NUMBER = "position";
    protected BaseFragmentPresenter mPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    TextToSpeech tts;
    private ItemAlertDialogBuilder mItemAlertDialogBuilder;
    private Unbinder mUnbinder;
    private MyAdapter.OnAdapterItemClickListener mOnAdapterItemClickListener =
            new MyAdapter.OnAdapterItemClickListener() {

                @DebugLog
                @Override
                public void onClick(android.view.View v) {
                    int position = mRecyclerView.getChildAdapterPosition(v);
                    mPresenter.onAdapterItemClick(position);
                }

                @DebugLog
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

    @DebugLog
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @DebugLog
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new BaseFragmentPresenterImpl(this);
        mPresenter.onActivityCreated(this.getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void setRecyclerView(List<ContactsItem> contactsItemList) {
        MyAdapter adapter = new MyAdapter(contactsItemList, mOnAdapterItemClickListener);
        GridLayoutManager layout = new GridLayoutManager(getContext(),
                getContext().getResources().getInteger(R.integer
                        .recyclerview_gridlayout_spancount));
        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setAdapter(adapter);
    }

    @DebugLog
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
    public void updateRecyclerViewItem(ContactsItem item) {
        MyAdapter adapter = (MyAdapter) mRecyclerView.getAdapter();
        adapter.setUpdateItem(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        mUnbinder.unbind();
        mPresenter.onDetach();
        super.onDetach();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showPermissionAlert(int resId) {
        new AlertDialog.Builder(getActivity()).setMessage(resId)
                .setPositiveButton(android.R.string.ok, (dialogInterface, which) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }

}