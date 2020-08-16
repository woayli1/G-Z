package com.gc.iphonemaxplan;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.gc.iphonemaxplan.Fragment.FragmentAbout;
import com.gc.iphonemaxplan.Fragment.FragmentMain;
import com.gc.iphonemaxplan.Fragment.FragmentRules;
import com.gc.iphonemaxplan.Fragment.MainAdapter;
import com.gc.iphonemaxplan.Tools.DataHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FragmentActivity {

    private ViewPager mainViewpager;
    private BottomNavigationView bottomView;

    public static DataHelper dataHelper;

    private List<Fragment> frameLayoutList = new ArrayList<>();

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this, "Record", null, 1);

        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .navigationBarColor(R.color.colorWhite)
                .init();

        bindView();
        initFragment();
        initEvent();

    }

    private void initFragment() {

        FragmentMain fragmentMain = new FragmentMain();
        FragmentRules fragmentRules = new FragmentRules();
        FragmentAbout fragmentAbout = new FragmentAbout();

        frameLayoutList.add(fragmentMain);
        frameLayoutList.add(fragmentRules);
        frameLayoutList.add(fragmentAbout);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), 0, frameLayoutList);
        mainViewpager.setAdapter(mainAdapter);
        mainViewpager.setCurrentItem(0);
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initEvent() {
        bottomView.setItemIconTintList(null);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean result = false;

                switch (item.getItemId()) {
                    case R.id.nav_item_main:
                        mainViewpager.setCurrentItem(0);
                        result = true;
                        break;
                    case R.id.nav_item_rules:
                        mainViewpager.setCurrentItem(1);
                        result = true;
                        break;
                    case R.id.nav_item_about:
                        mainViewpager.setCurrentItem(2);
                        result = true;
                        break;
                }

                return result;
            }
        });
    }

    private void bindView() {
        mainViewpager = this.findViewById(R.id.mainViewPager);
        bottomView = this.findViewById(R.id.bottomView);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long clickTime = 0; //记录第一次点击的时间

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            ToastUtils.showShort("再按一次退出");
            clickTime = System.currentTimeMillis();
        } else {
            finish();
            ToastUtils.cancel();
        }
    }

}
