package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.blogspot.ovsoce.hotkey.R;

public class EmergencyFragmentAdapter extends
        RecyclerView.Adapter<EmergencyFragmentAdapter.EmergencyViewHolder> {
    private List<EmergencyContact> mEmergencyList;

    EmergencyFragmentAdapter(@Nonnull List<EmergencyContact> emergencyList) {
        mEmergencyList = emergencyList;
    }

    @Override
    public EmergencyFragmentAdapter.EmergencyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_emergency_item, parent, false);
        return new EmergencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmergencyFragmentAdapter.EmergencyViewHolder holder, int position) {
        EmergencyContact contact = mEmergencyList.get(position);
        holder.mReceptionTv.setText(contact.receptionContents);
        holder.mPhoneNumberTv.setText(contact.phoneNumber);
        holder.mRelatedTv.setText(contact.relatedInstitutions);
        holder.position = position;
        holder.mOnEmergencyItemClickListener = mOnEmergencyItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mEmergencyList.size();
    }

    private OnEmergencyItemClickListener mOnEmergencyItemClickListener;

    public void setOnEmergencyItemClickListener(@Nonnull OnEmergencyItemClickListener listener) {
        mOnEmergencyItemClickListener = listener;
    }

    public interface OnEmergencyItemClickListener {
        void onItemClick(int position);
    }

    final static class EmergencyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reception_contents_textview)
        TextView mReceptionTv;
        @BindView(R.id.phone_number_textview)
        TextView mPhoneNumberTv;
        @BindView(R.id.related_institutions_textview)
        TextView mRelatedTv;
        int position;
        OnEmergencyItemClickListener mOnEmergencyItemClickListener;
        public EmergencyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnEmergencyItemClickListener.onItemClick(position);
                }
            });
        }
    }
}
