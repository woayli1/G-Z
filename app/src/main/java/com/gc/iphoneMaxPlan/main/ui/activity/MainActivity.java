package com.gc.iphoneMaxPlan.main.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gc.iphoneMaxPlan.R;
import com.gc.iphoneMaxPlan.base.BaseActivity;
import com.gc.iphoneMaxPlan.main.adapter.MainAdapter;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentAbout;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentMain;
import com.gc.iphoneMaxPlan.main.ui.fragment.FragmentRules;
import com.gc.iphoneMaxPlan.util.DataHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.bottomView)
    BottomNavigationView bottomView;

    public static DataHelper dataHelper;

    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setEnableImmersion(true);
        setStatusBarDarkFont(true);
        setStatusBarColor(R.color.colorBackgroundWhite);

        initFragment();
        initEvent();
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        dataHelper = new DataHelper(this, "Record", null, 1);
    }

    private void initFragment() {

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), 0);

        mainAdapter.addFragment(new FragmentMain());
        mainAdapter.addFragment(new FragmentRules());
        mainAdapter.addFragment(new FragmentAbout());

        mainViewPager.setAdapter(mainAdapter);
        mainViewPager.setCurrentItem(0);
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        bottomView.setOnNavigationItemSelectedListener(item -> {

            boolean result = false;

            switch (item.getItemId()) {
                case R.id.nav_item_main:
                    mainViewPager.setCurrentItem(0);
                    result = true;
                    break;
                case R.id.nav_item_rules:
                    mainViewPager.setCurrentItem(1);
                    result = true;
                    break;
                case R.id.nav_item_about:
                    mainViewPager.setCurrentItem(2);
                    result = true;
                    break;
            }

            return result;
        });

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
