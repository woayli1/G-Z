package com.gc.iphonemaxplan;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.gc.iphonemaxplan.Fragment.FragmentAbout;
import com.gc.iphonemaxplan.Fragment.FragmentMain;
import com.gc.iphonemaxplan.Fragment.FragmentRules;
import com.gc.iphonemaxplan.Fragment.MainAdapter;
import com.gc.iphonemaxplan.Tools.DataHelper;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mainViewpager;
    private Button main;
    private Button rules;
    private Button about;

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

    }

    public void mainClick(View v) {
        switch (v.getId()) {
            case R.id.main:
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.rules:
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.about:
                mainViewpager.setCurrentItem(2);
                break;
        }
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
        mainViewpager.addOnPageChangeListener(this);
        main.setTextColor(getResources().getColor(R.color.colorBlock));

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                main.setTextColor(getResources().getColor(R.color.colorBlock));
                rules.setTextColor(getResources().getColor(R.color.colorSilver));
                about.setTextColor(getResources().getColor(R.color.colorSilver));
                break;
            case 1:
                main.setTextColor(getResources().getColor(R.color.colorSilver));
                rules.setTextColor(getResources().getColor(R.color.colorBlock));
                about.setTextColor(getResources().getColor(R.color.colorSilver));
                break;
            case 2:
                main.setTextColor(getResources().getColor(R.color.colorSilver));
                rules.setTextColor(getResources().getColor(R.color.colorSilver));
                about.setTextColor(getResources().getColor(R.color.colorBlock));
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void bindView() {
        main = this.findViewById(R.id.main);
        rules = this.findViewById(R.id.rules);
        about = this.findViewById(R.id.about);
        mainViewpager = this.findViewById(R.id.mainViewPager);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(TAG, "返回键:按下了返回键");
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
