package com.gc.iphonemaxplan.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.iphonemaxplan.MainActivity;
import com.gc.iphonemaxplan.R;

import java.util.Date;

public class FragmentMain extends Fragment {

    private View view;

    private ListView listView; //界面数据容器
    private Button eat;
    private Button noEat;
    private TextView AllMoneys;

    private String money;

    private String TAG = "FragmentMain";

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
                    MainActivity.dataHelper.insertinto(temp, "+30");
                    Refresh();
                } else {
                    showmessgae("今天已经记录啦~");
                }
            }
        });

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "今天吃了");
                String temp2 = String.valueOf(new Date().getTime()).substring(0, 10) + "000";
                if (cheakExist(temp2)) {
                    MainActivity.dataHelper.insertinto(temp2, "-90");
                    Refresh();
                } else {
                    showmessgae("今天已经记录啦~");
                }
            }
        });

        return view;
    }

    private void Refresh() {
        MainActivity.items1 = MainActivity.dataHelper.getAllItem("data_name");
        MainActivity.items2 = MainActivity.dataHelper.getAllItem("money_name");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.data);
                TextView textView2 = view.findViewById(R.id.moneyChange);
                String str = textView.getText().toString();
                String str2 = textView2.getText().toString();
                dialog(str, str2);
            }
        });

        ListAdapter listAdapter = new ListAdapter(getActivity());
        listView.setAdapter(listAdapter);
        try {
            TextView aa = listView.getAdapter().getView(0, view, null).findViewById(R.id.AllMoney);
            money = aa.getText().toString();
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "获取listView对象 err:" + e);
            money = "0";
        }
        AllMoneys.setText("当前总金额为：\n" + money);
    }

    public boolean cheakExist(String value) {
        long temp = 0;
        long temp2 = 0;
        try {
            String str = MainActivity.items1.get(MainActivity.items1.size() - 1).trim();
            str = MainActivity.timeStampManager.stampToTime(str).substring(0, 10);
            temp = Long.parseLong(MainActivity.timeStampManager.shortTimeToStamp(str));
            temp2 = Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            Log.e(TAG, "Sting to long err:" + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            money = "0";
            Log.e(TAG, "获取items1长度 err" + e);
            return true;
        }
        return temp + 86400000 < temp2;
    }

    public void dialog(final String str, final String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认删除吗？");
        builder.setTitle(str);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.dataHelper.delete(MainActivity.timeStampManager.timeToStamp(str));
                showmessgae("删除成功");
                Refresh();
                dialog.dismiss();
            }
        }).setNegativeButton("取消", null).show();
    }

    private void bindView() {
        listView = view.findViewById(R.id.mainListView);
        eat = view.findViewById(R.id.eat);
        noEat = view.findViewById(R.id.noEat);
        AllMoneys = view.findViewById(R.id.AllMoneys);
    }

    public void showmessgae(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
