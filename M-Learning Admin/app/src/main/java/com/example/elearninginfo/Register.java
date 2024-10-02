package com.example.elearninginfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText username,password;
    Button register;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    //-------------- Firebase ---------------

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        register=(Button)findViewById(R.id.createAcc);

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email=username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete( Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information ;

                                    myRef.child("Admin").child(firebaseAuth.getCurrentUser().getUid()).setValue(email);
                                 //   progressDialog.dismiss();
                                    Toast.makeText(Register.this, "Registeration Done", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), CurriculumActivity.class));

                                } else {

                                    String msg= task.getException().toString();
                                    Toast.makeText(Register.this, "Error :"+ msg, Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }

                                // ...
                            }
                        });


            }
        });


    }


}
