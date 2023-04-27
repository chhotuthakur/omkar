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
import com.pheriwala.app.models.Customers;
import com.pheriwala.app.utils.FirebaseCustomerAdapter;


public class ProfileFragment extends Fragment {

    RecyclerView proRecyc;
    DatabaseReference reference;
    FirebaseCustomerAdapter adapter;
    FirebaseAuth cAuth;
    public static String uid;
    public static String cmail;



    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        proRecyc = view.findViewById(R.id.cust_profile_recy);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mCurrentUser != null) {
            uid = mCurrentUser.getUid();
            cmail = mCurrentUser.getEmail();
        }



        proRecyc.setLayoutManager(new LinearLayoutManager(getContext()));



        FirebaseRecyclerOptions<Customers> customer =
                new FirebaseRecyclerOptions.Builder<Customers>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Customer"), Customers.class)
                        .build();


        adapter = new FirebaseCustomerAdapter(customer);
        proRecyc.setAdapter(adapter);
        adapter.startListening();





        return view ;
    }
}