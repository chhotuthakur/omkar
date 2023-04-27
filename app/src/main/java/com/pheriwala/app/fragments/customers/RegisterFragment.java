package com.pheriwala.app.fragments.customers;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;


public class RegisterFragment extends Fragment {


    TextView log_txt;
    EditText name, mail , password,phone,location ,typ ;
    String id, nme,email,pass,phn,locate,type;
    Button reg ;
    Spinner spinner;
    List<String> typeList;
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
        spinner = v.findViewById(R.id.chck_holder);

        typeList= new ArrayList<>();
        typeList.add("Customer");
        typeList.add("Vendor");

        spinner.setAdapter(new ArrayAdapter<>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,typeList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = typeList.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        typ = v.findViewById(R.id.cst_edt_name_type);
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
                    databaseReference.child("Customer").child(id).child("type").setValue(type);
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