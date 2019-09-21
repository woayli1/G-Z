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

//    private ArrayList<String> items1 = new ArrayList<>();
//    private ArrayList<String> items2 = new ArrayList<>();

    TextView data;  //日期
    TextView moneyChange; //金额变化
    TextView AllMoney; //所有金额

    public ListAdapter(Context context) {
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
        data = view.findViewById(R.id.data);
        moneyChange = view.findViewById(R.id.moneyChange);
        AllMoney = view.findViewById(R.id.AllMoney);

        data.setText(MainActivity.items1.get(position));
        moneyChange.setText(MainActivity.items2.get(position));

        return view;
    }
}
