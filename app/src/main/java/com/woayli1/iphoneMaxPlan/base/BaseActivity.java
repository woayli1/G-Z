package com.woayli1.iphoneMaxPlan.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.InflateException;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.woayli1.iphoneMaxPlan.R;
import com.woayli1.iphoneMaxPlan.base.ibase.IBaseActivity;
import com.woayli1.iphoneMaxPlan.base.ibase.IBaseContract;
import com.gyf.immersionbar.ImmersionBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBaseContract.IBasePresenter> extends AppCompatActivity implements IBaseActivity {
    protected final String TAG = this.getClass().getSimpleName();

    private int statusBarColor = R.color.colorWhite;
    private boolean statusBarDarkFont = false;
    private boolean enableImmersion = false;
    public ImmersionBar mImmersionBar;

    protected P mPresenter;
    private Unbinder mUnBinder;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            int layoutResId = getLayoutId();
            if (layoutResId != 0) {

                setContentView(layoutResId);

                mUnBinder = ButterKnife.bind(this);
                mActivity = this;

            }
        } catch (Exception e) {
            if (e instanceof InflateException) {
                throw e;
            }
            e.printStackTrace();
        }

        initView();
        initData(savedInstanceState);

        mImmersionBar = ImmersionBar.with(this);

        initImmersionBar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnBinder != null && mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
            mUnBinder = null;
        }

        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    public void initImmersionBar() {
        if (!isImmersionBarEnabled()) {
            return;
        }

        mImmersionBar.reset()
                .fitsSystemWindows(true)
                .statusBarColor(statusBarColor)
                .statusBarDarkFont(statusBarDarkFont)
                .init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    public Boolean isImmersionBarEnabled() {
        return enableImmersion;
    }

    /**
     * 设置状态栏颜色 默认值colorAccent
     *
     * @param statusBarColor 状态栏颜色
     */
    public void setStatusBarColor(int statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    /**
     * 设置状态栏字体深色或亮色
     *
     * @param statusBarDarkFont true 深色
     */
    public void setStatusBarDarkFont(boolean statusBarDarkFont) {
        this.statusBarDarkFont = statusBarDarkFont;
    }

    public void setEnableImmersion(boolean enableImmersion) {
        this.enableImmersion = enableImmersion;
    }

    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }
}
