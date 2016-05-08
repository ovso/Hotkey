package kr.blogspot.ovsoce.hotkey.fragment.vo;

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
}
