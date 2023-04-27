package com.pheriwala.app.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pheriwala.app.R;
import com.pheriwala.app.models.Customers;
import com.pheriwala.app.models.Vendors;

public class FirebaseCustomerAdapter  extends FirebaseRecyclerAdapter<Customers, FirebaseCustomerAdapter.productViewHolder> {



    public FirebaseCustomerAdapter(@NonNull FirebaseRecyclerOptions<Customers> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull productViewHolder holder, int position, @NonNull Customers model) {

        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.address.setText(model.getAddress());


    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ven_recyc_holder,parent,false);
        return  new productViewHolder(view);
    }

    public class productViewHolder extends RecyclerView.ViewHolder{


        TextView name,phone,address;
        public productViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.ven_rec_name_txt);
            phone = itemView.findViewById(R.id.ven_rec_phone_txt);
            address = itemView.findViewById(R.id.ven_rec_location_txt);




        }
    }

}
