package kr.blogspot.ovsoce.hotkey.fragment.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.framework.Log;

/**
 * RecyclerView Adapter
 */
public class MyAdapter extends RecyclerView.Adapter {
    private List<ContactsItem> mList;
    private OnAdapterItemClickListener mListener;
    private Disposable subscribe;

    public MyAdapter(List<ContactsItem> list, OnAdapterItemClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recyclerview_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactsItem data = mList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        MyApplication app = (MyApplication) myViewHolder.blockV.getContext()
                .getApplicationContext();
        String colorCode = app.getDatabaseHelper().getDefaultColors()[Integer.parseInt(data
                .getColor())];

        myViewHolder.blockV.setBackgroundColor(Color.parseColor(colorCode));
        myViewHolder.nameTv.setText(data.getName());
        subscribe = RxView.clicks(myViewHolder.itemView).throttleFirst(2, TimeUnit
                .SECONDS, AndroidSchedulers
                .mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(o -> mListener
                .onClick(myViewHolder.itemView));
        myViewHolder.itemView.setOnLongClickListener(mListener);
    }

    public void setUpdateItem(ContactsItem item) {
        mList.set(Integer.parseInt(item.getId()), item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void onDetach() {
        if (subscribe != null) {
            subscribe.dispose();
        }
    }

    public interface OnAdapterItemClickListener extends android.view.View.OnClickListener,
            android.view.View.OnLongClickListener {

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView nameTv;
        @BindView(R.id.v_block)
        View blockV;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
