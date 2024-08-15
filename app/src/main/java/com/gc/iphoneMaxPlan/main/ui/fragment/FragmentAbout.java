package com.gc.iphoneMaxPlan.main.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gc.iphoneMaxPlan.R;
import com.gc.iphoneMaxPlan.main.base.BaseFragment;


/**
 * 关于作者
 */
public class FragmentAbout extends BaseFragment {

    @Override
    public Object setLayout() {
        return R.layout.fragmen_about;
    }

    @Override
    public void initView(View view) {
        TextView fragmentAbout = view.findViewById(R.id.fragmentAbout);
        //设置版本号
        fragmentAbout.setText(String.format(getResources().getString(R.string.FragmentAbout_current_version), AppUtils.getAppVersionName()));
    }
}
