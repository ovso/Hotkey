package kr.blogspot.ovsoce.hotkey.donate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import kr.blogspot.ovsoce.hotkey.R;

public class DonateAdapter extends RecyclerView.Adapter<DonateViewHolder>
    implements DonateAdapterDataModel, DonateAdapterView {
  //private final static String TAG = "DonateAdapter";
  private ArrayList<String> imageurls;
  private Context context;

  public DonateAdapter(Context context) {
    this.context = context;
    imageurls = new ArrayList<>();
  }

  @Override public DonateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.content_donate_item, null);
    return new DonateViewHolder(view);
  }

  @Override public void onBindViewHolder(DonateViewHolder holder, int position) {

    String url = imageurls.get(position);
    Glide.with(context).load(url).into(holder.donateImageview);
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