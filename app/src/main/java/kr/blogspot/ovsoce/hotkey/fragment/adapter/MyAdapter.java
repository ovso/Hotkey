package kr.blogspot.ovsoce.hotkey.fragment.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.application.MyApplication;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;

/**
 * RecyclerView Adapter
 */
public class MyAdapter extends RecyclerView.Adapter{
    List<ContactsItem> mList;
    OnAdapterItemClickListener mListener;
    public MyAdapter(List<ContactsItem> list,OnAdapterItemClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactsItem data = mList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        //myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));

        MyApplication app = (MyApplication) myViewHolder.blockV.getContext().getApplicationContext();
        String colorCode = app.getDatabaseHelper().getDefaultColors()[Integer.parseInt(data.getColor())];

        myViewHolder.blockV.setBackgroundColor(Color.parseColor(colorCode));
        myViewHolder.nameTv.setText(data.getName());
    }
    public void setUpdateItem(ContactsItem item) {
        mList.set(Integer.parseInt(item.getId()), item);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        View blockV;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            blockV = itemView.findViewById(R.id.v_block);
            itemView.setOnClickListener(mListener);
            itemView.setOnLongClickListener(mListener);
        }
    }

    public interface OnAdapterItemClickListener extends android.view.View.OnClickListener, android.view.View.OnLongClickListener {

    }
}
