package kr.blogspot.ovsoce.hotkey.main;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
