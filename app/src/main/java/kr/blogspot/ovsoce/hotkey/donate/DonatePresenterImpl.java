package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import kr.blogspot.ovsoce.hotkey.common.Log;
import kr.blogspot.ovsoce.hotkey.donate.adapter.DonateAdapterDataModel;
import kr.blogspot.ovsoce.hotkey.donate.vo.Donation;

public class DonatePresenterImpl implements DonatePresenter {

  private View mView;
  private DonateModel mModel;
  private DonateAdapterDataModel adapterDataModel;
  private final static String TAG = "DonatePresenterImpl";

  DonatePresenterImpl(View view, DonateAdapterDataModel adapterDataModel) {
    mView = view;
    this.adapterDataModel = adapterDataModel;
    mModel = new DonateModel(view.getContext());
  }

  @Override public void onCreate(Context context) {
    mView.setRootView();
    mView.setToolbar();
    initRecyclerView();
  }

  private void initRecyclerView() {
    mView.showLoading();
    mView.setRecyclerView();
    DatabaseReference reference =
        FirebaseDatabase.getInstance("https://hotkey-e0f76.firebaseio.com/")
            .getReference("donation");
    reference.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        Donation donation = dataSnapshot.getValue(Donation.class);
        for (String imageUrl : donation.getImageurls()) {
          adapterDataModel.add(imageUrl);
        }
        mView.setDescription(donation.getDescription());
        mView.refresh();
        mView.hideLoading();
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        Log.d(databaseError.getMessage());
        mView.hideLoading();
      }
    });
  }
}
