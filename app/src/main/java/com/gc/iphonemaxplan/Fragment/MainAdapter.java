package com.gc.iphonemaxplan.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    /**
     * @param fm
     * @deprecated
     */
    public MainAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
