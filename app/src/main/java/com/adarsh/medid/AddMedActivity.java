package com.adarsh.medid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedActivity extends AppCompatActivity {

    private EditText addMedName, addMedDesc, addMedQty;
    private Button submit;
    DatabaseReference mAddMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        addMedName = findViewById(R.id.addMedName);
        addMedDesc = findViewById(R.id.addMedDesc);
        addMedQty = findViewById(R.id.addMedQty);
        submit = findViewById(R.id.submitMed);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddMed = FirebaseDatabase.getInstance().getReference().child("medicines");
                Log.d("yo", FirebaseAuth.getInstance().getUid());
                MedDetailObject medDetailObject = new MedDetailObject(addMedName.getText().toString(),addMedDesc.getText().toString(), addMedQty.getText().toString(), "sdfsdf");
                mAddMed.push().setValue(medDetailObject);
                Log.d("yo",medDetailObject.medDesc);
                finish();
            }
        });
    }
}
