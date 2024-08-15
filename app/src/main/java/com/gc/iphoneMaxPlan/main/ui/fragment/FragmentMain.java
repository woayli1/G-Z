package com.gc.iphoneMaxPlan.main.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.gc.iphoneMaxPlan.main.base.BaseFragment;
import com.gc.iphoneMaxPlan.R;
import com.gc.iphoneMaxPlan.util.DataHelper;
import com.gc.iphoneMaxPlan.main.adapter.ListAdapter;
import com.gc.iphoneMaxPlan.main.bean.BaseBean;
import com.lxj.xpopup.XPopup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 首页
 */
public class FragmentMain extends BaseFragment {

    private RecyclerView mainListView;
    private TextView AllMoneys;

    private DataHelper dataHelper;
    private List<BaseBean> baseBean;

    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {

        mainListView = view.findViewById(R.id.mainListView);
        Button eat = view.findViewById(R.id.eat);
        Button noEat = view.findViewById(R.id.noEat);
        AllMoneys = view.findViewById(R.id.AllMoneys);

        dataHelper = new DataHelper(context, "Record", null, 1);

        Refresh();

        noEat.setOnClickListener(v -> {
            LogUtils.d("今天没吃");
            long temp = TimeUtils.string2Millis(TimeUtils.getNowString());
            if (checkExist(temp)) {
                dataHelper.insertInto(String.valueOf(temp), "+30");
                Refresh();
            } else {
                showMessage("今天已经记录啦~");
            }
        });

        eat.setOnClickListener(v -> {
            LogUtils.d("今天吃了");
            long temp2 = TimeUtils.string2Millis(TimeUtils.getNowString());
            if (checkExist(temp2)) {
                dataHelper.insertInto(String.valueOf(temp2), "-90");
                Refresh();
            } else {
                showMessage("今天已经记录啦~");
            }
        });

    }

    private void Refresh() {

        baseBean = new ArrayList<>();
        List<Integer> allMoneysList = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<String> money = new ArrayList<>();

        int length = dataHelper.getAllItem("data_name").size();

        for (int i = 0; i < length; i++) {
            data.add(dataHelper.getAllItem("data_name").get(i));
            money.add(dataHelper.getAllItem("money_name").get(i));
            countAllMoney(allMoneysList, dataHelper.getAllItem("money_name").get(length - 1 - i));
        }

        for (int j = 0; j < data.size(); j++) {
            baseBean.add(new BaseBean(data.get(j), money.get(j), allMoneysList.get(allMoneysList.size() - 1 - j)));
        }

        mainListView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapter listAdapter = new ListAdapter();
        listAdapter.addAll(baseBean);
        mainListView.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener((adapter, view, position) -> {
            TextView textView = view.findViewById(R.id.data);
            dialog(textView.getText().toString());
        });

        if (ObjectUtils.isEmpty(allMoneysList)) {
            AllMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), "0"));
        } else {
            AllMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), allMoneysList.get(allMoneysList.size() - 1).toString()));
        }

    }

    private void countAllMoney(List<Integer> items, String money) {
        Integer a;
        if (ObjectUtils.isEmpty(items)) {
            a = 0;
        } else {
            a = items.get(items.size() - 1);
        }

        if (money.charAt(0) == '-') {
            a = a - 90;
        } else if (money.charAt(0) == '+') {
            a = a + 30;
        } else {
            a = 0;
        }
        items.add(a);
    }

    public boolean checkExist(long temp2) {
        if (ObjectUtils.isEmpty(baseBean)) {
            return true;
        }

        long temp;
        try {
            temp = Long.parseLong(baseBean.get(0).getData());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LogUtils.e(e);
            return true;
        }
        return temp + 86400000 < temp2;
    }

    public void dialog(final String msg) {

        new XPopup.Builder(getContext()).isLightStatusBar(true).isLightNavigationBar(true).asConfirm("确认删除记录吗？", msg, () -> {
            dataHelper.delete(String.valueOf(TimeUtils.string2Millis(msg)));
            showMessage("删除成功");
            Refresh();
        }).show();
    }
}
