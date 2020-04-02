package kr.blogspot.ovsoce.hotkey.fragment;

import android.content.Context;

public class BaseFragmentModel extends FragmentModel {

    private int tabPosition = 0;

    public BaseFragmentModel(Context context) {
        super(context);
    }

    @Override
    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    public String getTTSString(String name) {
        return name;
    }
}
