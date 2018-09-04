package com.adarsh.medid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileSetUpActivity extends AppCompatActivity {

    private EditText dob,phone,fname,lname;
    private Button submit;
    private String email;
    DatabaseReference mProfileDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_up);

        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        phone = findViewById(R.id.editTextPhone);
        dob = findViewById(R.id.editTextDOB);
        submit = findViewById(R.id.submit);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendProfile();
            }
        });

    }

    public void sendProfile(){
        mProfileDB = FirebaseDatabase.getInstance().getReference().child("user");
        String userId = mProfileDB.push().getKey();
        ProfileObject profileObject = new ProfileObject(fname.getText().toString().trim(),lname.getText().toString().trim(),
                phone.getText().toString().trim(),dob.getText().toString().trim(),email);
        mProfileDB.child(userId).setValue(profileObject);
    }

}
