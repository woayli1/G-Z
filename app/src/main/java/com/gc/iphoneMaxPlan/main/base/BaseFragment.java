package com.gc.iphoneMaxPlan.main.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;

/**
 * author gc
 * company enjoyPartyTime
 * date 2024/8/15
 */
public abstract class BaseFragment extends Fragment {

    protected Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new NullPointerException("viewBinding is null");
        }
        if (rootView != null) {
            context = getContext();
            initView(rootView);
        }
        return rootView;

    }

    public abstract Object setLayout();

    public void initView(View view) {

    }

    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
