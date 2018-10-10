package kr.blogspot.ovsoce.hotkey.data;

import lombok.Getter;

public interface KeyName {

  @Getter enum Prefs {
    VIEW_PAGER_POSITION("now_tab_position");
    private String value;

    Prefs(String $value) {
      this.value = $value;
    }
  }
}
