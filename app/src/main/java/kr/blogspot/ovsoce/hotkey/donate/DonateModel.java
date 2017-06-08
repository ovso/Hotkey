package kr.blogspot.ovsoce.hotkey.donate;

import android.content.Context;
import kr.blogspot.ovsoce.hotkey.main.Model;
import lombok.Getter;
import lombok.Setter;

public class DonateModel extends Model {

  public DonateModel(Context context) {
    super(context);
  }

  @Getter @Setter private boolean isLoaded;
}
