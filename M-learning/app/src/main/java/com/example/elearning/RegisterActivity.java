package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,pass,repass,phone,id,college;      //Declaration
    Button register;
    TextView login;
    TextInputLayout nameError,emailError,phoneError,passError;
    UserData userData;

    //-------------- Firebase ---------------
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    // ------------- Progress Dialog ----------

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repassword);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        id = (EditText) findViewById(R.id.id);
        college = (EditText)findViewById(R.id.collegename);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void SetValidation() {

        userData = new UserData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("REGISTERING ...");

        progressDialog.show();

        //Local Variable to store Password

        String password = pass.getText().toString().trim();
        String repassword = repass.getText().toString().trim();

        // Profile Data Store

        userData.setUname(name.getText().toString());
        userData.setUemail(email.getText().toString());
        userData.setUphone(phone.getText().toString());
        userData.setUid(id.getText().toString());
        userData.setUcollege(college.getText().toString());
        userData.setImgurl("https://firebasestorage.googleapis.com/v0/b/guest-info-app-d9b79.appspot.com/o/profile_nav_logo.png?alt=media&token=4ab4ce18-b46d-4ac6-a71e-f629e5ec3d89");

        // Firebase Database Reference

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();


        // Validate Data

        // Full name

        if (TextUtils.isEmpty(userData.uname)) {

            Toast.makeText(RegisterActivity.this, "Please Enter Full Name ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }


        // Email
        if (TextUtils.isEmpty(userData.uemail)) {

            Toast.makeText(RegisterActivity.this, "Please Enter Email ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        // Email
        if (TextUtils.isEmpty(userData.uid)) {

            Toast.makeText(RegisterActivity.this, "Please Enter Id ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        // College Name
        if (TextUtils.isEmpty(userData.ucollege)) {

            Toast.makeText(RegisterActivity.this, "Please Enter College Name ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        // password

        if (TextUtils.isEmpty(password)) {

            Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        // repassword

        if (TextUtils.isEmpty(repassword)) {

            Toast.makeText(RegisterActivity.this, "Please Enter RePassword", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (password.length() < 6) {

            Toast.makeText(RegisterActivity.this, "Password Must be more than 6 digit & less than 1 digit", Toast.LENGTH_SHORT).show();
        }


        // Mobile Number

        if (TextUtils.isEmpty(userData.uphone)) {

            Toast.makeText(RegisterActivity.this, "Please Enter Mobile Number ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (userData.uphone.length() < 10) {

            Toast.makeText(RegisterActivity.this, "Mobile no. must be more 10 digit number! Enter Valid number. ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }



        // Validation Done !!


        if (password.equals(repassword) && userData.uphone.length() == 10 ) {

            firebaseAuth.createUserWithEmailAndPassword(userData.uemail, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                myRef.child("Users").child(firebaseAuth.getCurrentUser().getUid()).child("Profile").setValue(userData);
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(), Login.class));
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();


                            } else {
                                String msg = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                            // ...
                        }
                    });

        }

    }

}