package com.gc.iphonemaxplan.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gc.iphonemaxplan.MainActivity;
import com.gc.iphonemaxplan.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentMain extends Fragment {

    private View view;

    private ListView listView; //界面数据容器
    private Button eat;
    private Button noEat;

    private String TAG = "FragmentMain";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        bindView();

        ListAdapter listAdapter = new ListAdapter(getActivity());
        listView.setAdapter(listAdapter);

        String a = String.valueOf(new Date().getTime());
        Log.d(TAG, "a=" + a);
        Log.d(TAG, "aa=" + stampToTime(a));

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "今天吃了");
            }
        });

        noEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "今天没吃");
            }
        });

        return view;
    }

    /* //时间戳转换日期 */
    public String stampToTime(String stamp) {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sd = sdf.format(new Date(Long.parseLong(stamp))); // 时间戳转换日期
        return sd;
    }


    private void bindView() {
        listView = view.findViewById(R.id.mainListView);
        eat = view.findViewById(R.id.eat);
        noEat = view.findViewById(R.id.noEat);
    }

    public void showmessgae(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
