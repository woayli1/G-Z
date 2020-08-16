package com.gc.iphonemaxplan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gc.iphonemaxplan.R;
import com.gc.iphonemaxplan.base.BaseFragment;

import androidx.annotation.Nullable;

public class FragmentAbout extends BaseFragment {

    private View view;

    private TextView fragmentAbout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_about, container, false);

        bindView();

        //设置版本号
        fragmentAbout.setText(String.format(getResources().getString(R.string.FragmentAbout_current_version), AppUtils.getAppVersionName()));

        return view;
    }

    private void bindView() {
        fragmentAbout = view.findViewById(R.id.fragmentAbout);
    }

}
