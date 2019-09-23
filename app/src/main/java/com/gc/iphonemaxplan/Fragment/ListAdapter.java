package com.gc.iphonemaxplan.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gc.iphonemaxplan.MainActivity;
import com.gc.iphonemaxplan.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;

    private String TAG = "ListAdapter";

    private ArrayList<String> AllMoneys = new ArrayList<>();

    ListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.items1.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.items1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_main_listview, null);
        //日期
        TextView data = view.findViewById(R.id.data);
        //金额变化
        TextView moneyChange = view.findViewById(R.id.moneyChange);
        //所有金额
        TextView allMoney = view.findViewById(R.id.AllMoney);

        countAllMoney(MainActivity.items2);
        data.setText(MainActivity.timeStampManager.stampToTime(MainActivity.items1.get(position)));
        moneyChange.setText(MainActivity.items2.get(position));
        allMoney.setText(AllMoneys.get(AllMoneys.size() - 1 - position));
        return view;
    }

    private void countAllMoney(ArrayList<String> items) {
        if (!AllMoneys.isEmpty()) {
            AllMoneys.clear();
        }
        int a = 0;
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).substring(0, 1).equals("-")) {
                a = a - 90;
            } else {
                a = a + 30;
            }
            try {
                AllMoneys.set(items.size() - 1 - i, String.valueOf(a));
            } catch (IndexOutOfBoundsException e) {
                AllMoneys.add(items.size() - 1 - i, String.valueOf(a));
            }
        }

    }

}
