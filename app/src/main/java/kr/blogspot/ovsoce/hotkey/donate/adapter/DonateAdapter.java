package kr.blogspot.ovsoce.hotkey.donate.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.common.Log;

public class DonateAdapter extends RecyclerView.Adapter<DonateViewHolder>
    implements DonateAdapterDataModel, DonateAdapterView {
  //private final static String TAG = "DonateAdapter";
  private ArrayList<String> imageurls;
  private Context context;
  private FirebaseStorage storage;
  private StorageReference reference;

  public DonateAdapter(Context context) {
    this.context = context;
    imageurls = new ArrayList<>();
    storage = FirebaseStorage.getInstance();
    reference = storage.getReferenceFromUrl("gs://hotkey-e0f76.appspot.com/");
  }

  @Override public DonateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.content_donate_item, null);
    return new DonateViewHolder(view);
  }

  @Override public void onBindViewHolder(DonateViewHolder holder, int position) {

    String year = String.valueOf(2016 + position);
    holder.yearTextview.setText(year);
    final String url = imageurls.get(position);
    StorageReference sr = reference.child(imageurls.get(position));
    sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
      @Override public void onSuccess(Uri uri) {
        Log.d("uri = " + url.toString());
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override public void onFailure(@NonNull Exception e) {
        e.printStackTrace();
      }
    });
    Glide.with(context).using(new FirebaseImageLoader()).load(sr).into(holder.donateImageview);
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public String getItem(int position) {
    return imageurls.get(position);
  }

  @Override public void add(String imageUrl) {
    imageurls.add(imageUrl);
  }

  @Override public String remove(int position) {
    return imageurls.remove(position);
  }

  @Override public String getImageUrl(int position) {
    return imageurls.get(position);
  }

  @Override public int getSize() {
    return imageurls.size();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }
}