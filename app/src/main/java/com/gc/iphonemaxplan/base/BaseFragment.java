package com.gc.iphonemaxplan.base;

import com.blankj.utilcode.util.ToastUtils;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

}
