package com.example.elearninginfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button signin,signup;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        signin=(Button)findViewById(R.id.signin);
        signup=(Button)findViewById(R.id.signup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOGGING IN ...");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

    }

    public void btnSignin(View view) {

        progressDialog.show();
        String email=username.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(Login.this, "Email Should not be Empty", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if(TextUtils.isEmpty(passwords)){

            Toast.makeText(Login.this, "Password Should not be Empty", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if(password.length()<6){

            Toast.makeText(Login.this, "Short Password,length must be more than 6", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email, passwords)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), CurriculumActivity.class));


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login.this, "Login Failed or User Not Found", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        // ...
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // User is signed in
            Intent i = new Intent(Login.this,CurriculumActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out

        }

    }

}
