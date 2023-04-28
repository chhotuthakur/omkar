package com.pheriwala.app.fragments.vendors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pheriwala.app.R;
import com.pheriwala.app.utils.FcmNotificationsSender;


public class NotiFragment extends Fragment {


    EditText tit,con;
    Button send;



    public NotiFragment() {

    }


    public static NotiFragment newInstance(String param1, String param2) {
        NotiFragment fragment = new NotiFragment();

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
        View v = inflater.inflate(R.layout.fragment_noti, container, false);


        FirebaseMessaging.getInstance().subscribeToTopic("all");

        tit = v.findViewById(R.id.noti_title);
        con = v.findViewById(R.id.noti_content);
        send = v.findViewById(R.id.noti_send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title , content;

                if (tit.getText().toString().isEmpty() && con.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"please input title and content",Toast.LENGTH_LONG).show();
                }else {

                    title = tit.getText().toString();
                    content = con.getText().toString();

                    FcmNotificationsSender fcmNotificationsSender = new FcmNotificationsSender("/topics/all", title, content, getActivity().getApplicationContext(), getActivity());
                    fcmNotificationsSender.SendNotifications();
                }

            }
        });


        return v;

    }
}