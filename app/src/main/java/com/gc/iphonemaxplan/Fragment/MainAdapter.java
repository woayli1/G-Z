package com.gc.iphonemaxplan.Fragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    /**
     * @param fm
     * @param behavior
     * @param fragmentList
     */
    public MainAdapter(FragmentManager fm, int behavior, List<Fragment> fragmentList) {
        super(fm, behavior);

        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
