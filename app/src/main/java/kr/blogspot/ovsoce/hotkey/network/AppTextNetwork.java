package kr.blogspot.ovsoce.hotkey.network;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import hugo.weaving.DebugLog;
import kr.blogspot.ovsoce.hotkey.framework.OnMyResultListener;
import kr.blogspot.ovsoce.hotkey.network.model.AppTextContent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jaeho on 2017. 8. 1
 */

public class AppTextNetwork {

  private DatabaseReference databaseReference;

  public void req() {
    if (onMyResultListener != null) onMyResultListener.onPre();
    databaseReference = FirebaseDatabase.getInstance("https://hotkey-e0f76.firebaseio.com/")
        .getReference()
        .child("app_text");
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        AppTextContent update = dataSnapshot.getValue(AppTextContent.class);
        if (onMyResultListener != null) {
          onMyResultListener.onResult(update);
          onMyResultListener.onPost();
        }
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        if (onMyResultListener != null) {
          onMyResultListener.onError();
        }
      }
    });
  }

  @DebugLog public void cancel() {
    databaseReference.onDisconnect();
    onMyResultListener = null;
  }

  @Getter @Setter private OnMyResultListener onMyResultListener;
}