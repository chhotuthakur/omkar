package com.pheriwala.app.fragments.customers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pheriwala.app.R;
import com.pheriwala.app.adapters.FirebaseProductAdapter;
import com.pheriwala.app.models.Products;
import com.pheriwala.app.models.Vendors;
import com.pheriwala.app.utils.FirebaseVendorAdapter;


public class VendorFragment extends Fragment {



    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseVendorAdapter adapter;


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


        recyclerView = v.findViewById(R.id.vendor_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        FirebaseRecyclerOptions<Vendors> products =
                new FirebaseRecyclerOptions.Builder<Vendors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), Vendors.class)
                        .build();


        adapter = new FirebaseVendorAdapter(products);
        recyclerView.setAdapter(adapter);
        adapter.startListening();



        return v;
    }
}