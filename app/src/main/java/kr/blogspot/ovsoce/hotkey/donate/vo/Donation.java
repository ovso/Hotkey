package kr.blogspot.ovsoce.hotkey.donate.vo;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.List;
import lombok.Getter;

@IgnoreExtraProperties public class Donation {
  @Getter private String description;
  @Getter private List<String> imageurls;

  public Donation() {
  }

  public Donation(String description, List<String> imageurls) {
    this.description = description;
    this.imageurls = imageurls;
  }
}