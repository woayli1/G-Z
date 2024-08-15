package com.gc.iphoneMaxPlan.main.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.gc.iphoneMaxPlan.R;
import com.gc.iphoneMaxPlan.main.bean.BaseBean;

public class ListAdapter extends BaseQuickAdapter<BaseBean, QuickViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable BaseBean baseBean) {
        if (ObjectUtils.isNotEmpty(baseBean)) {
            quickViewHolder.setText(R.id.data, TimeUtils.millis2String(Long.parseLong(baseBean.getData())));
            quickViewHolder.setText(R.id.moneyChange, baseBean.getMoney());
            quickViewHolder.setText(R.id.AllMoney, baseBean.getAllMoney().toString());
        }
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.item_main_listview, viewGroup);
    }
}