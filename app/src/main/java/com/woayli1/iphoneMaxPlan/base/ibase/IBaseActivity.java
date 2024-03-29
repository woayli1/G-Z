package com.woayli1.iphoneMaxPlan.base.ibase;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * ================================================
 * 框架要求框架中的每个 {@link Activity} 都需要实现此类,以满足规范
 * ================================================
 */
public interface IBaseActivity {

    /**
     * 返回 0, 框架则不会调用 {@link Activity#setContentView(int)
     *
     * @return
     */
    int getLayoutId();

    /**
     * 初始化 View, 如果 {@link #initView()}
     */
    void initView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);
}