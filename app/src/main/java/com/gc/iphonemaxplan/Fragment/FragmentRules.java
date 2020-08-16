package com.gc.iphonemaxplan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gc.iphonemaxplan.R;
import com.gc.iphonemaxplan.base.BaseFragment;

import androidx.annotation.Nullable;

public class FragmentRules extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rules, container, false);

        return view;
    }
}
