package com.pheriwala.app.fragments.customers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pheriwala.app.R;
import com.pheriwala.app.activities.customers.ChomeActivity;


public class LoginFragment extends Fragment {

    TextView  create_text;
    Button login;
    EditText mail,pass;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase database;
    DatabaseReference reference;



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
        mail = v.findViewById(R.id.cst_edt_mail);
        pass = v.findViewById(R.id.cst_edt_pass);
        login = v.findViewById(R.id.cst_log_btn);


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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mail.getText().toString();
                String pas = pass.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getContext(),"Logged In",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(),ChomeActivity.class));

                    }
                });

            }
        });
        return v;
    }
}