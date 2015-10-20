package kr.blogspot.ovsoce.hotkey.fragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kr.blogspot.ovsoce.hotkey.R;

/**
 * RecyclerView Adapter
 */
public class MyAdapter extends RecyclerView.Adapter{
    List<ContactsItem> mList;
    FragmentPresenter.View mView;

    public MyAdapter(List<ContactsItem> list, FragmentPresenter.View view) {
        mList = list;
        mView = view;
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
        myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));
        myViewHolder.nameTv.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView nameTv;
        View blockV;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            blockV = itemView.findViewById(R.id.v_block);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mView.onClick(v);
        }

        @Override
        public boolean onLongClick(View v) {
            mView.onLongClick(v);
            return true;
        }
    }
}
