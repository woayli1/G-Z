package com.woayli1.iphoneMaxPlan.main.ui.fragment;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.woayli1.iphoneMaxPlan.main.ui.activity.MainActivity;
import com.woayli1.iphoneMaxPlan.R;
import com.woayli1.iphoneMaxPlan.util.TimeStampManager;
import com.woayli1.iphoneMaxPlan.main.adapter.ListAdapter;
import com.woayli1.iphoneMaxPlan.base.BaseFragment;
import com.woayli1.iphoneMaxPlan.base.ibase.IBaseContract;
import com.woayli1.iphoneMaxPlan.main.bean.BaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

public class FragmentMain extends BaseFragment {

    @BindView(R.id.mainListView)
    RecyclerView mainListView;
    @BindView(R.id.eat)
    Button eat;
    @BindView(R.id.noEat)
    Button noEat;
    @BindView(R.id.AllMoneys)
    TextView AllMoneys;

    private List<BaseBean> baseBean;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected IBaseContract.IBasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {

        Refresh();

        noEat.setOnClickListener(v -> {
            Log.d(TAG, "今天没吃");
            String temp = String.valueOf(new Date().getTime()).substring(0, 10) + "000";
            if (checkExist(temp)) {
                MainActivity.dataHelper.insertInto(temp, "+30");
                Refresh();
            } else {
                showMessage("今天已经记录啦~");
            }
        });

        eat.setOnClickListener(v -> {
            Log.d(TAG, "今天吃了");
            String temp2 = String.valueOf(new Date().getTime()).substring(0, 10) + "000";
            if (checkExist(temp2)) {
                MainActivity.dataHelper.insertInto(temp2, "-90");
                Refresh();
            } else {
                showMessage("今天已经记录啦~");
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    private void Refresh() {

        baseBean = new ArrayList<>();
        List<Integer> allMoneysList = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<String> money = new ArrayList<>();

        int length = MainActivity.dataHelper.getAllItem("data_name").size();

        for (int i = 0; i < length; i++) {
            data.add(MainActivity.dataHelper.getAllItem("data_name").get(i));
            money.add(MainActivity.dataHelper.getAllItem("money_name").get(i));
            countAllMoney(allMoneysList, MainActivity.dataHelper.getAllItem("money_name").get(length - 1 - i));
        }

        for (int j = 0; j < data.size(); j++) {
            baseBean.add(new BaseBean(data.get(j), money.get(j), allMoneysList.get(allMoneysList.size() - 1 - j)));
        }

        mainListView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapter listAdapter = new ListAdapter(baseBean);
        mainListView.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener((adapter, view, position) -> {
            TextView textView = view.findViewById(R.id.data);
            dialog(textView.getText().toString());
        });

        if (allMoneysList.size() <= 0) {
            AllMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), "0"));
        } else {
            AllMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), allMoneysList.get(allMoneysList.size() - 1).toString()));
        }

    }

    private void countAllMoney(List<Integer> items, String money) {
        Integer a;
        if (items.size() <= 0) {
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

    public boolean checkExist(String value) {
        long temp, temp2;
        try {
            String str = baseBean.get(0).getData();
            str = TimeStampManager.getInstance().stampToTime(str).substring(0, 10);
            temp = Long.parseLong(TimeStampManager.getInstance().shortTimeToStamp(str));
            temp2 = Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            Log.e(TAG, "Sting to long err:" + e);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "获取items1长度 err" + e);
            return true;
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "数据为空 err" + e);
            return true;
        }
        return temp + 86400000 < temp2;
    }

    public void dialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认删除记录吗？");
        builder.setTitle(str);
        builder.setPositiveButton("确认", (dialog, which) -> {
            MainActivity.dataHelper.delete(TimeStampManager.getInstance().timeToStamp(str));
            showMessage("删除成功");
            Refresh();
            dialog.dismiss();
        }).setNegativeButton("取消", null).show();
    }
}
