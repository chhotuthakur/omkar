package com.pheriwala.app.fragments.customers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pheriwala.app.R;

public class ChomeFragment extends Fragment {



    public ChomeFragment() {
        // Required empty public constructor
    }


    public static ChomeFragment newInstance(String param1, String param2) {
        ChomeFragment fragment = new ChomeFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View v = inflater.inflate(R.layout.fragment_chome, container, false);
        return v;
    }
}