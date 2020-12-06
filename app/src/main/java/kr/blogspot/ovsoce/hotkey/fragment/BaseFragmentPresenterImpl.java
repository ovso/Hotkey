package kr.blogspot.ovsoce.hotkey.fragment;

import android.Manifest;
import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kr.blogspot.ovsoce.hotkey.App;
import kr.blogspot.ovsoce.hotkey.R;
import kr.blogspot.ovsoce.hotkey.fragment.listener.SimpleUtteranceProgressListener;
import kr.blogspot.ovsoce.hotkey.fragment.vo.ContactsItem;
import kr.blogspot.ovsoce.hotkey.framework.ObjectUtils;
import kr.blogspot.ovsoce.hotkey.framework.Prefs;
import kr.blogspot.ovsoce.hotkey.framework.SystemUtils;

class BaseFragmentPresenterImpl implements BaseFragmentPresenter {

  private View view;
  private BaseFragmentModel model;
  private RxPermissions permissions;
  private TextToSpeech tts;
  private Disposable subscribe;

  BaseFragmentPresenterImpl(View view) {
    Context context = view.getContext();
    this.view = view;
    model = new BaseFragmentModel(context);
    permissions = new RxPermissions(view.getActivity());
  }

  @Override
  public void onActivityCreated(int position) {
    model.setTabPosition(position);
    view.setRecyclerView(model.getContactsItemList());
  }

  @Override
  public void onItemAlertDialogOkClick(String itemId) {
    ContactsItem item = model.getContactsItem(Integer.parseInt(itemId));
    view.updateRecyclerViewItem(item);
  }

  @Override
  public void onAdapterItemClick(final int position) {
    final String phoneNumber = model.getPhoneNumber(position);
    final ContactsItem item = model.getContactsItem(position);
    if (!ObjectUtils.isEmpty(phoneNumber)) {
      reqPermission(item);
    } else {
      onAdapterItemLongClick(position);
    }
  }

  private void reqPermission(final ContactsItem item) {
    subscribe =
        permissions
            .request(Manifest.permission.CALL_PHONE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                granted -> {
                  if (granted) { // Always true pre-M
                    BaseFragmentPresenterImpl.this.makeCall(item);
                  } else {
                    view.showPermissionAlert(R.string.call_phone_denied_msg);
                  }
                });
  }

  private void makeCall(final ContactsItem item) {
    Context context = App.getInstance().getApplicationContext();
    boolean isTTS = Prefs.getBoolean(context, "tts", false);
    if (!isTTS) {
      view.makeCall(item.getNumber());
    } else {
      makeCallAfterTts(item);
    }
  }

  private void makeCallAfterTts(final ContactsItem item) {
    tts =
        new TextToSpeech(
            App.getInstance(),
            status -> {
              if (status == TextToSpeech.SUCCESS) {
                tts.setOnUtteranceProgressListener(
                    new SimpleUtteranceProgressListener() {
                      @Override
                      public void onDone(String utteranceId) {
                        view.makeCall(item.getNumber());
                      }
                    });
                HashMap<String, String> params = new HashMap<>();
                params.put(
                    TextToSpeech.Engine.KEY_PARAM_STREAM,
                    String.valueOf(AudioManager.STREAM_ALARM));
                params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");

                tts.speak(item.getName(), TextToSpeech.QUEUE_FLUSH, params);
              }
            });
    tts.setLanguage(
        SystemUtils.getStringToLocale(SystemUtils.getLocaleToString(App.getInstance())));
  }

  @Override
  public void onDestroyView() {
    if (tts != null) {
      tts.stop();
      tts.shutdown();
    }
    if (subscribe != null) {
      subscribe.dispose();
    }
  }

  @Override
  public void onAdapterItemLongClick(int position) {
    ContactsItem item = model.getContactsItem(position);
    view.showItemSetDialog(item);
  }
}
