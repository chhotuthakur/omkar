package com.pheriwala.app.fragments.customers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pheriwala.app.R;


public class LoginFragment extends Fragment {

    TextView  create_text;



    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        create_text = v.findViewById(R.id.create_acc_txt);

        create_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RegisterFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.acc_frag_container,fragment,fragment.toString())
                        .addToBackStack(null)
                        .commit();
            }
        });

        //TODO : add here
        return v;
    }
}