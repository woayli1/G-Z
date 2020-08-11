package com.gc.iphonemaxplan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gc.iphonemaxplan.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRules extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rules, container, false);

        return view;
    }
}
