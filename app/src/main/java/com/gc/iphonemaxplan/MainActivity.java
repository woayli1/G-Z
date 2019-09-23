package com.gc.iphonemaxplan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gc.iphonemaxplan.Fragment.FragmentAbout;
import com.gc.iphonemaxplan.Fragment.FragmentMain;
import com.gc.iphonemaxplan.Fragment.FragmentRules;
import com.gc.iphonemaxplan.Fragment.MainAdapter;
import com.gc.iphonemaxplan.Tools.DataHelper;
import com.gc.iphonemaxplan.Tools.TimeStampManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {

    private ViewPager mainViewpager;
    private Button main;
    private Button rules;
    private Button about;

    public static DataHelper dataHelper;
    public static TimeStampManager timeStampManager=new TimeStampManager();

    public static ArrayList<String> items1 = new ArrayList<>();
    public static ArrayList<String> items2 = new ArrayList<>();

    private List<Fragment> frameLayoutList = new ArrayList<>();

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this, "Record", null, 1);

        bindView();
        initView();

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

    private void initView() {

        FragmentMain fragmentMain = new FragmentMain();
        FragmentRules fragmentRules = new FragmentRules();
        FragmentAbout fragmentAbout = new FragmentAbout();

        frameLayoutList.add(fragmentMain);
        frameLayoutList.add(fragmentRules);
        frameLayoutList.add(fragmentAbout);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), frameLayoutList);
        mainViewpager.setAdapter(mainAdapter);
        mainViewpager.setCurrentItem(0);
        mainViewpager.setOnPageChangeListener(this);
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


    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(TAG, "返回键:按下了返回键");
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

}
