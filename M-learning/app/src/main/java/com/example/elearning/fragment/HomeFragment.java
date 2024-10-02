package com.example.elearning.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elearning.CivilB;
import com.example.elearning.ComputerB;
import com.example.elearning.ETXB;
import com.example.elearning.AutomobileB;
import com.example.elearning.ITB;
import com.example.elearning.MechanicalB;
import com.example.elearning.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeFragment extends Fragment {

    FloatingActionButton fb;
    CardView tc1,tc2,tc3,tc4,tc5,tc6;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fb = root.findViewById(R.id.btnadd);
        tc1 = root.findViewById(R.id.buttonTc1);
        tc2 = root.findViewById(R.id.buttonTc2);
        tc3 = root.findViewById(R.id.buttonTc3);
        tc4 = root.findViewById(R.id.buttonTc4);
        tc5 = root.findViewById(R.id.buttonTc5);
        tc6 = root.findViewById(R.id.buttonTc6);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        tc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AutomobileB.class);
                getContext().startActivity(intent);
            }
        });

        tc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CivilB.class);
                getContext().startActivity(intent);
            }
        });

        tc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ComputerB.class);
                getContext().startActivity(intent);
            }
        });

        tc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ETXB.class);
                getContext().startActivity(intent);
            }
        });

        tc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ITB.class);
                getContext().startActivity(intent);
            }
        });

        tc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MechanicalB.class);
                getContext().startActivity(intent);
            }
        });

        return root;

    }

}