package kr.blogspot.ovsoce.hotkey.emergency.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        return new EmergencyViewHolder(
                parent.inflate(parent.getContext(), R.layout.fragment_emergency_item, null));
    }

    @Override
    public void onBindViewHolder(EmergencyFragmentAdapter.EmergencyViewHolder holder, int position) {
        EmergencyContact contact = mEmergencyList.get(position);
        holder.mReceptionTv.setText(contact.receptionContents);
        holder.mPhoneNumberTv.setText(contact.phoneNumber);
        holder.mRelatedTv.setText(contact.relatedInstitutions);
    }

    @Override
    public int getItemCount() {
        return mEmergencyList.size();
    }

    final static class EmergencyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail_imageview)
        ImageView mThumbnailTv;
        @BindView(R.id.reception_contents_textview)
        TextView mReceptionTv;
        @BindView(R.id.phone_number_textview)
        TextView mPhoneNumberTv;
        @BindView(R.id.related_institutions_textview)
        TextView mRelatedTv;
        public EmergencyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
