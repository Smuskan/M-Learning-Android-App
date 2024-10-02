package com.example.elearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ComputerB extends AppCompatActivity {

    Spinner  semester, serv;
    public String t1="Computer",t2,t3;
    Button btn_viewSyllabus;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    ListView myPDFListView;
    List<uploadPDF> uploadPDFList;
    CardView mycardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_b);

        btn_viewSyllabus=(Button)findViewById(R.id.viewSyllabus);
        mycardview = (CardView)findViewById(R.id.cardview);

        myPDFListView = (ListView)findViewById(R.id.myListView);
        uploadPDFList = new ArrayList<>();

        semester = (Spinner) findViewById(R.id.sem_spineer1);
        serv = (Spinner) findViewById(R.id.servicess);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("MSBTE");

        myPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                uploadPDF uploadPDF = uploadPDFList.get(position);

                String link = uploadPDF.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.sems, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter2);
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                t2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "text : "+t2, Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this,R.array.services, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serv.setAdapter(adapters);
        serv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                t3 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "text : "+t3, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void btnViewSyllabus(View view) {

        viewAllFiles();

    }

    private void viewAllFiles() {

        uploadPDFList.clear();
        databaseReference= FirebaseDatabase.getInstance().getReference("MSBTE").child(t1).child(t2).child(t3);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ps : dataSnapshot.getChildren()){

                    uploadPDF uploadPDF = ps.getValue(uploadPDF.class);
                    uploadPDFList.add(uploadPDF);

                }

                String [] uploads = new String[uploadPDFList.size()];

                for (int i=0;i<uploads.length;i++){

                    uploads[i]=uploadPDFList.get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads);
                myPDFListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}