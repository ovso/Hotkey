package kr.blogspot.ovsoce.hotkey.fragment;

import java.io.Serializable;

/**
 * Created by jaeho_oh on 2015-10-22.
 */
public interface ContactsItem extends Serializable {
    String getName();
    String getNumber();
    String getColor();
    String getId();
    int getMenuType();
    void setMenuType(int menuType);
}
