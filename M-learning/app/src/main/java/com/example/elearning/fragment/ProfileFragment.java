package com.example.elearning.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.R;
import com.example.elearning.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    View v;
    TextView aemail,aname,amobile,aid,acollege;
    FirebaseAuth firebaseAuth;
    CircleImageView profileimgview;

    ProgressDialog progressDialog;

    FirebaseDatabase firebaseDatabase;
    String imageurl ;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.profile_fragment, container, false);

        aemail=v.findViewById(R.id.txtaemail);
        aname=v.findViewById(R.id.txtaname);
        amobile=v.findViewById(R.id.txt_amobileno);
        acollege=v.findViewById(R.id.txt_acollege);
        aid=v.findViewById(R.id.txt_aidno);
        profileimgview=v.findViewById(R.id.profile_imageview);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("LOADING ...");

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("Profile");

        progressDialog.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData member = dataSnapshot.getValue(UserData.class);
                aname.setText(member.getUname());
                aemail.setText(member.getUemail());
                amobile.setText(member.getUphone());
                acollege.setText(member.getUcollege());
                aid.setText(member.getUid());
                //imageurl = member.getImgurl();
                /*
                Glide.with(getContext())
                        .load(member.getImgurl())
                        .into(profileimgview);

                 */
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

                Toast.makeText(getContext(), "Retrieve Failed !", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        return v;

    }



}