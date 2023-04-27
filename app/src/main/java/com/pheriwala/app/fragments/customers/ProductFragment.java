package com.pheriwala.app.fragments.customers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pheriwala.app.R;
import com.pheriwala.app.adapters.FirebaseProductAdapter;
import com.pheriwala.app.models.Products;


public class ProductFragment extends Fragment {


    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseProductAdapter adapter;


    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();

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
        View v = inflater.inflate(R.layout.fragment_product, container, false);

       recyclerView = v.findViewById(R.id.pro_holder_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        FirebaseRecyclerOptions<Products> products =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), Products.class)
                        .build();


        adapter = new FirebaseProductAdapter(products);
        recyclerView.setAdapter(adapter);
        adapter.startListening();


        return v;
    }
}