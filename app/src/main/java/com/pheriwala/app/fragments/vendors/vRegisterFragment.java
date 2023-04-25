package com.pheriwala.app.fragments.vendors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pheriwala.app.R;
import com.pheriwala.app.fragments.customers.LoginFragment;
import com.pheriwala.app.models.Customers;


public class vRegisterFragment extends Fragment {


    TextView log_txt;
    EditText name, mail , password,phone,location ;
    String id, nme,email,pass,phn,locate,type;
    RadioButton ccheck,vcheck;
    Button reg ;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference databaseReference;
    RadioGroup radioGroup;



    public vRegisterFragment() {
        // Required empty public constructor
    }


    public static vRegisterFragment newInstance(String param1, String param2) {
        vRegisterFragment fragment = new vRegisterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_vregister, container, false);


        log_txt = v.findViewById(R.id.log_acc_txt);
        name = v.findViewById(R.id.cst_edt_name_reg);
        mail = v.findViewById(R.id.cst_edt_mail_reg);
        phone = v.findViewById(R.id.cst_edt_numb_reg);
        password = v.findViewById(R.id.cst_edt_pass_reg);
        location = v.findViewById(R.id.cst_edt_addres_reg);
        reg = v.findViewById(R.id.cust_reg_btn);





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
        id= phn;

        mAuth = FirebaseAuth.getInstance();




        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "success, please login", Toast.LENGTH_LONG).show();
                    databaseReference = FirebaseDatabase.getInstance().getReference();
//                Products products = new Products();

                    Customers customers = new Customers();

                    databaseReference.child("Customer").child(id).child("name").setValue(nme);
                    databaseReference.child("Customer").child(id).child("email").setValue(email);
                    databaseReference.child("Customer").child(id).child("phone").setValue(phn);
                    databaseReference.child("Customer").child(id).child("address").setValue(locate);
//                    databaseReference.child("Customer").child(id).child("type").setValue(type);


                    Toast.makeText(getContext(),"Registered Succesfully! Please Login",Toast.LENGTH_LONG).show();
                    Fragment fragment = new LoginFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.acc_frag_container,fragment,fragment.toString())
                            .addToBackStack(null)
                            .commit();




                }
            }
        });
    }


}