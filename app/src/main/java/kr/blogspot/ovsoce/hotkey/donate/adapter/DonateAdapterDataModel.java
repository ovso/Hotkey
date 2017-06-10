package kr.blogspot.ovsoce.hotkey.donate.adapter;

public interface DonateAdapterDataModel {
  void add(String imageUrl);
  String remove(int position);
  String getImageUrl(int position);
  String getItem(int position);

  int getSize();
}
