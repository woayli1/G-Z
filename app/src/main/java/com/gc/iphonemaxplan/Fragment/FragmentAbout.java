package com.gc.iphonemaxplan.Fragment;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.gc.iphonemaxplan.R;
import com.gc.iphonemaxplan.base.BaseFragment;
import com.gc.iphonemaxplan.base.ibase.IBaseContract;

import androidx.annotation.Nullable;
import butterknife.BindView;

public class FragmentAbout extends BaseFragment {

    @BindView(R.id.fragmentAbout)
    TextView fragmentAbout;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragmen_about;
    }

    @Override
    protected IBaseContract.IBasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {
        //设置版本号
        fragmentAbout.setText(String.format(getResources().getString(R.string.FragmentAbout_current_version), AppUtils.getAppVersionName()));
    }

    @Override
    public void initData() {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
