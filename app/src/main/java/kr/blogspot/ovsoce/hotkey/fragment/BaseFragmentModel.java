package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

public class BaseFragmentModel extends FragmentModel {

    private int menuId = -1;

    public BaseFragmentModel(Context context) {
        super(context);
    }

    @Override
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
