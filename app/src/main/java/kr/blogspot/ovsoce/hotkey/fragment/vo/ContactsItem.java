package kr.blogspot.ovsoce.hotkey.fragment.vo;

import java.io.Serializable;

public interface ContactsItem extends Serializable {
    String getName();
    String getNumber();
    String getColor();
    String getId();
    int getTabPosition(); // tab position of TabLayout
}
