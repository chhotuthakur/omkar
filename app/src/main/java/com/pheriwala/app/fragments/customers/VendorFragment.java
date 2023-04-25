package com.pheriwala.app.fragments.customers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pheriwala.app.R;


public class VendorFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase database;
    DatabaseReference databaseReference;


    public VendorFragment() {
        // Required empty public constructor
    }



    public static VendorFragment newInstance(String param1, String param2) {
        VendorFragment fragment = new VendorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vendor, container, false);



        return v;
    }
}