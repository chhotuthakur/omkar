package com.pheriwala.app.fragments.customers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.pheriwala.app.R;

import java.io.IOException;
import java.util.List;


public class ChomeFragment extends Fragment {


    private FusedLocationProviderClient fusedLocationClient;
    TextView location_text;
    private final int REQUEST_CODE =100;




    public ChomeFragment() {
        // Required empty public constructor
    }


    public static ChomeFragment newInstance(String param1, String param2) {
        ChomeFragment fragment = new ChomeFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        View v = inflater.inflate(R.layout.fragment_chome, container, false);


        location_text = v.findViewById(R.id.location_txt_chome);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastLocation();

        return v;
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder = new Geocoder(getContext());
                                List<Address> addressList = null;
                                try {
                                    addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                location_text.setText(addressList.get(0).getLocality());
                            }
                        }
                    });
        }else{
            askPermission();
        }
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
            else {
                Toast.makeText(getContext(), "Location permission not granted, " + "restart the app if you want the feature", Toast.LENGTH_SHORT).show();
            }
        }
    }
}