package kr.blogspot.ovsoce.hotkey.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SectionsPagerAdatper extends FragmentPagerAdapter {
    private List<Fragment> mList;
    public SectionsPagerAdatper(FragmentManager fm, List<Fragment> list) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
