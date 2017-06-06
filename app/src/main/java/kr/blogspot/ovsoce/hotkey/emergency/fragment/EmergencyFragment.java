package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hugo.weaving.DebugLog;
import kr.blogspot.ovsoce.hotkey.R;

public class EmergencyFragment extends Fragment implements EmergencyFragmentPresenter.View {

    public static Fragment getInstance(int type) {
        Fragment f = new EmergencyFragment();
        Bundle b = new Bundle();
        b.putInt("type", type);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    private Unbinder mUnbinder;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    private EmergencyFragmentPresenter mPresenter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new EmergencyFragmentPresenterImpl(this);
        mPresenter.onActivityCreate(getArguments().getInt("type"));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUnbinder.unbind();
    }
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public void setRecyclerView(List<EmergencyContact> contactList) {
        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.TRANSPARENT);
        paint.setAlpha(20);
        HorizontalDividerItemDecoration divider = new HorizontalDividerItemDecoration
                .Builder(getActivity()).paint(paint).build();
        mRecyclerView.addItemDecoration(divider);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        EmergencyFragmentAdapter adapter = new EmergencyFragmentAdapter(contactList);
        adapter.setOnEmergencyItemClickListener(mOnEmergencyItemClickListener);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showMakeCallDialog(final EmergencyContact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = contact.receptionContents + "\n" + contact.phoneNumber
                + "\n"+contact.relatedInstitutions;
        builder.setMessage(message);
        builder.setPositiveButton(R.string.make_call, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makeCall(contact.phoneNumber);
            }
        }).setNeutralButton(R.string.dialog_btn_text_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigateToSMS(contact.phoneNumber);
            }
        });
        builder.setNegativeButton(R.string.btn_cancel, null);
        builder.show();
    }

    @DebugLog
    public void navigateToSMS(String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+number));
        getContext().startActivity(intent);
    }

    @DebugLog
    public void makeCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private EmergencyFragmentAdapter.OnEmergencyItemClickListener mOnEmergencyItemClickListener
            = new EmergencyFragmentAdapter.OnEmergencyItemClickListener() {
        @Override
        public void onItemClick(int position) {
            mPresenter.onItemClick(position);
        }
    };
}
