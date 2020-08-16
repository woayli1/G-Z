package com.gc.iphonemaxplan.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gc.iphonemaxplan.MainActivity;
import com.gc.iphonemaxplan.R;
import com.gc.iphonemaxplan.Tools.TimeStampManager;
import com.gc.iphonemaxplan.base.BaseFragment;
import com.gc.iphonemaxplan.bean.BaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentMain extends BaseFragment {

    private View view;

    private RecyclerView recyclerView; //界面数据容器
    private Button eat;
    private Button noEat;
    private TextView allMoneys;


    private List<BaseBean> baseBean;
    private List<String> data;
    private List<String> money;
    private List<Integer> AllMoneysList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        bindView();
        Refresh();

        noEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "今天没吃");
                String temp = String.valueOf(new Date().getTime()).substring(0, 10) + "000";
                if (cheakExist(temp)) {
                    MainActivity.dataHelper.insertInto(temp, "+30");
                    Refresh();
                } else {
                    showMessage("今天已经记录啦~");
                }
            }
        });

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "今天吃了");
                String temp2 = String.valueOf(new Date().getTime()).substring(0, 10) + "000";
                if (cheakExist(temp2)) {
                    MainActivity.dataHelper.insertInto(temp2, "-90");
                    Refresh();
                } else {
                    showMessage("今天已经记录啦~");
                }
            }
        });

        return view;
    }

    private void Refresh() {

        baseBean = new ArrayList<>();
        AllMoneysList = new ArrayList<>();
        data = new ArrayList<>();
        money = new ArrayList<>();

        int length = MainActivity.dataHelper.getAllItem("data_name").size();

        for (int i = 0; i < length; i++) {
            data.add(MainActivity.dataHelper.getAllItem("data_name").get(i));
            money.add(MainActivity.dataHelper.getAllItem("money_name").get(i));
            countAllMoney(AllMoneysList, MainActivity.dataHelper.getAllItem("money_name").get(length - 1 - i));
        }

        for (int j = 0; j < data.size(); j++) {
            baseBean.add(new BaseBean(data.get(j), money.get(j), AllMoneysList.get(AllMoneysList.size() - 1 - j)));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapter listAdapter = new ListAdapter(baseBean);
        recyclerView.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                TextView textView = view.findViewById(R.id.data);
                dialog(textView.getText().toString());
            }
        });

        if (AllMoneysList.size() <= 0) {
            allMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), "0"));
        } else {
            allMoneys.setText(String.format(getResources().getString(R.string.FragmentAbout_current_money), AllMoneysList.get(AllMoneysList.size() - 1).toString()));
        }

    }

    private void countAllMoney(List<Integer> items, String money) {
        Integer a;
        if (items.size() <= 0) {
            a = 0;
        } else {
            a = items.get(items.size() - 1);
        }

        if (money.substring(0, 1).equals("-")) {
            a = a - 90;
        } else if (money.substring(0, 1).equals("+")) {
            a = a + 30;
        } else {
            a = 0;
        }
        items.add(a);
    }

    public boolean cheakExist(String value) {
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
        builder.setMessage("确认删除吗？");
        builder.setTitle(str);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.dataHelper.delete(TimeStampManager.getInstance().timeToStamp(str));
                showMessage("删除成功");
                Refresh();
                dialog.dismiss();
            }
        }).setNegativeButton("取消", null).show();
    }

    private void bindView() {
        recyclerView = view.findViewById(R.id.mainListView);
        eat = view.findViewById(R.id.eat);
        noEat = view.findViewById(R.id.noEat);
        allMoneys = view.findViewById(R.id.AllMoneys);
    }
}
