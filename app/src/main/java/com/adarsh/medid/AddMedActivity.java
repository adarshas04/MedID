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

import java.util.ArrayList;

public class AddMedActivity extends AppCompatActivity {

    private EditText addMedName, addMedDesc, addMedQty;
    private Button submit;
    DatabaseReference mAddMed;
    ArrayList<MedDetailObject> mdo = new ArrayList<>();

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
                Log.d("yoloo", FirebaseAuth.getInstance().getUid());
                String name = addMedName.getText().toString();
                String desc = addMedDesc.getText().toString();
                String qty = addMedQty.getText().toString();
                String mid = mAddMed.push().getKey();
                Med a = new Med(name, desc, qty, mid);
                Log.d("jsasaj","sac "+name+"sd "+desc+"sca "+qty);
                mAddMed.child(mid).setValue(a);
                finish();
            }
        });
    }
}
