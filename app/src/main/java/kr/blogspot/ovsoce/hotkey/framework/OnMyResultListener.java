package kr.blogspot.ovsoce.hotkey.framework;

/**
 * Created by jaeho on 2017. 8. 2
 */

public interface OnMyResultListener<T> {
  void onPre();
  void onResult(T result);
  void onPost();
  void onError();
}