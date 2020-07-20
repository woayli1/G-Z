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
    PackageManager packageManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_about, container, false);

        bindView();

        //获取版本号
        if (getActivity() != null) {
            packageManager = getActivity().getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
                fragmentAbout.setText(String.format(getResources().getString(R.string.FragmentAbout_current_version), packageInfo.versionName));
            } catch (PackageManager.NameNotFoundException e) {
                fragmentAbout.setText(R.string.FragmentAbout_get_current_version_err);
                Log.e(TAG, "获取版本号错误:" + e);
            }
        } else {
            fragmentAbout.setText(R.string.FragmentAbout_get_current_version_err);
            Log.e(TAG, "getActivity is null");
        }

        return view;
    }

    private void bindView() {
        fragmentAbout = view.findViewById(R.id.fragmentAbout);
    }

}
