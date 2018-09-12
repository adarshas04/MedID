package com.adarsh.medid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileSetUpActivity extends AppCompatActivity {

    private EditText dob,phone,fname,lname,store_name,place;
    private Button submit;
    private String email;
    Spinner user_type;
    DatabaseReference mProfileDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_set_up);

        user_type = findViewById(R.id.user_types);
        store_name = findViewById(R.id.store_name);
        place = findViewById(R.id.place);
        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        phone = findViewById(R.id.editTextPhone);
        dob = findViewById(R.id.editTextDOB);
        submit = findViewById(R.id.submit);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        user_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String med = String.valueOf(parent.getItemAtPosition(position));
                if(med.equals("Medical Store"))
                {
                    store_name.setVisibility(View.VISIBLE);
                }
                else if(med.equals("Delivery Person")){
                    place.setVisibility(View.VISIBLE);
                }
                else
                {
                    store_name.setVisibility(View.GONE);
                    place.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendProfile();
                startActivity(new Intent(ProfileSetUpActivity.this,MedListActivity.class));

            }
        });

    }

    public void sendProfile(){
        mProfileDB = FirebaseDatabase.getInstance().getReference().child("user");
        ProfileObject d = new ProfileObject();

        d.setFname(fname.getText().toString());
        d.setLname(lname.getText().toString());
        d.setDob(dob.getText().toString());
        d.setEmail(email);
        d.setMob(phone.getText().toString());

        if (!store_name.getText().toString().isEmpty()) {
            d.setStore_name(store_name.getText().toString());
        }

        if(!place.getText().toString().isEmpty()){
            d.setPlace(place.getText().toString());

        }

        mProfileDB.child(FirebaseAuth.getInstance().getUid()).setValue(d);

    }

}
