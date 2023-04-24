package com.pheriwala.app.fragments.customers;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.pheriwala.app.models.Customers;


public class RegisterFragment extends Fragment {


    TextView log_txt;
    EditText name, mail , password,phone,location ;
    String id, nme,email,pass,phn,locate,type;
    CheckBox ccheck,vcheck;
    Button reg ;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference databaseReference;



    public RegisterFragment() {
        // Required empty public constructor
    }


    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_register, container, false);


        log_txt = v.findViewById(R.id.log_acc_txt);
        name = v.findViewById(R.id.cst_edt_name_reg);
        mail = v.findViewById(R.id.cst_edt_mail_reg);
        phone = v.findViewById(R.id.cst_edt_numb_reg);
        password = v.findViewById(R.id.cst_edt_pass_reg);
        location = v.findViewById(R.id.cst_edt_addres_reg);
        ccheck = v.findViewById(R.id.ccheck);
        vcheck = v.findViewById(R.id.vcheck);
        reg = v.findViewById(R.id.cust_reg_btn);

//        if (ccheck.isChecked()){
//            type = ccheck.getText().toString();
//        }
//        else if(vcheck.isChecked()){
//            type = vcheck.getText().toString();
//
//        }


        nme = name.getText().toString();
        email = mail.getText().toString();
        pass = password.getText().toString();
        phn = phone.getText().toString();
        locate = location.getText().toString();
        id = nme+phn;

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regUser();

            }
        });



        log_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.acc_frag_container,fragment,fragment.toString())
                        .addToBackStack(null)
                        .commit();
            }
        });

       return v;
    }

    private void regUser() {

        nme = name.getText().toString();
        email = mail.getText().toString();
        pass = password.getText().toString();
        phn = phone.getText().toString();
        locate = location.getText().toString();
        id = nme+phn;
mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");


                            Fragment fragment = new LoginFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.acc_frag_container,fragment,fragment.toString())
                                    .addToBackStack(null)
                                    .commit();



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

}