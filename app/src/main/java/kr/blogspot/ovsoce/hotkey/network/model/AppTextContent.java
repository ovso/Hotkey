package kr.blogspot.ovsoce.hotkey.network.model;

import com.google.firebase.database.IgnoreExtraProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jaeho on 2017. 9. 17
 */

@Getter @IgnoreExtraProperties @ToString @EqualsAndHashCode(callSuper = false)
public class AppTextContent {
  private String donation;
  private String donation_img_url;
}