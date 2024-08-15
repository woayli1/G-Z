package com.gc.iphoneMaxPlan.main.ui.activity;

import android.os.Bundle;

import com.gc.iphoneMaxPlan.R;
import com.gc.iphoneMaxPlan.main.adapter.MainAdapter;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentAbout;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentMain;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentRules;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;


public class MainActivity extends FragmentActivity {

    private ViewPager2 mainViewPager;
    private BottomNavigationView bottomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewPager = findViewById(R.id.mainViewPager);
        bottomView = findViewById(R.id.bottomView);

        initFragment();
        initEvent();
    }


    private void initFragment() {

        MainAdapter mainAdapter = new MainAdapter(this);

        mainAdapter.addFragment(new FragmentMain());
        mainAdapter.addFragment(new FragmentRules());
        mainAdapter.addFragment(new FragmentAbout());

        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.setCurrentItem(0);
        mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomView.getMenu().getItem(position).setChecked(true);
            }
        });

    }

    private void initEvent() {
        bottomView.setItemIconTintList(null);
        bottomView.setOnItemSelectedListener(item -> {
            boolean result = false;
            int resId = item.getItemId();
            if (resId == R.id.nav_item_main) {
                mainViewPager.setCurrentItem(0);
                result = true;
            } else if (resId == R.id.nav_item_rules) {
                mainViewPager.setCurrentItem(1);
                result = true;
            } else if (resId == R.id.nav_item_about) {
                mainViewPager.setCurrentItem(2);
                result = true;
            }
            return result;
        });
    }
}
