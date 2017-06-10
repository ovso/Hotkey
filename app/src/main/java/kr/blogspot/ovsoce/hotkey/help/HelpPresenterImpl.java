package kr.blogspot.ovsoce.hotkey.help;

import android.content.Context;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import kr.blogspot.ovsoce.hotkey.common.Log;

public class HelpPresenterImpl implements HelpPresenter {

  private HelpPresenter.View mView;
  private HelpModel mModel;

  HelpPresenterImpl(HelpPresenter.View view) {
    mView = view;
    mModel = new HelpModel(view.getContext());
  }

  @Override public void onClick(android.view.View view) {

  }

  @Override public void onCreate(Context context) {
    mView.setRootView();
    mView.onInit();
    mView.showLoading();
    DatabaseReference reference =
        FirebaseDatabase.getInstance("https://hotkey-e0f76.firebaseio.com/").getReference("help");
    reference.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        mView.setHelpText(mModel.getHelpText((List<String>) dataSnapshot.getValue()));
        mView.hideLoading();
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        Log.d(databaseError.getMessage());
        mView.hideLoading();
      }
    });
  }

  @Override public void onOptionsItemSelected(int itemId) {
    if (itemId == android.R.id.home) {
      mView.activityFinish();
    }
  }
}
