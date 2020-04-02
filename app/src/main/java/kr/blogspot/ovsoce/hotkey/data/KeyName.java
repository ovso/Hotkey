package kr.blogspot.ovsoce.hotkey.data;


public interface KeyName {

  enum Prefs {
    VIEW_PAGER_POSITION("now_tab_position");
    private String value;

    public String getValue() {
      return value;
    }

    Prefs(String $value) {
      this.value = $value;
    }
  }
}
