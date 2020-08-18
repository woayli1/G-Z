package com.gc.iphonemaxplan.Fragment;

import android.view.View;

import com.gc.iphonemaxplan.R;
import com.gc.iphonemaxplan.base.BaseFragment;
import com.gc.iphonemaxplan.base.ibase.IBaseContract;

import androidx.annotation.Nullable;

public class FragmentRules extends BaseFragment {

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_rules;
    }

    @Override
    protected IBaseContract.IBasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
