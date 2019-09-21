package com.gc.iphonemaxplan.Fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.iphonemaxplan.R;

public class FragmentAbout extends Fragment {

    private View view;

    private TextView fragmentAbout;

    private String TAG = "FragmentAbout";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_about, container, false);

        bindView();

        //获取版本号
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
            fragmentAbout.setText("当前版本号：" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            fragmentAbout.setText("获取版本号错误");
            Log.e(TAG, "获取版本号错误:" + e);
        }

        return view;
    }

    private void bindView() {
        fragmentAbout = view.findViewById(R.id.fragmentAbout);
    }

}
