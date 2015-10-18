package kr.blogspot.ovsoce.hotkey.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;

/**
 * Created by ovso on 2015. 10. 17..
 */
public class MyAdapter extends RecyclerView.Adapter {
    List<ContactsItem> mList;
    View.OnClickListener mOnClickListener;
    public MyAdapter(List<ContactsItem> list, View.OnClickListener listener) {
        mList = list;
        mOnClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(layoutView, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactsItem data = mList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));
        myViewHolder.nameTv.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        View blockV;

        public MyViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            blockV = itemView.findViewById(R.id.v_block);
            Log.d("MyViewHolder", "blockV = " + blockV);
            Log.d("MyViewHolder", "nameTv = " + nameTv);
            itemView.setOnClickListener(listener);
        }
    }
}
