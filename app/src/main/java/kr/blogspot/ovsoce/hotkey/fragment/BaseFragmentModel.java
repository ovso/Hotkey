package kr.blogspot.ovsoce.hotkey.fragment;

public class BaseFragmentModel extends FragmentModel {

    private int menuId = -1;

    @Override
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
