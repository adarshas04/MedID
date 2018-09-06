package com.adarsh.medid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedListActivity extends AppCompatActivity {

    ArrayList<MedDetailObject> matList = new ArrayList<>();
    MedDetailAdapter adapter;
    private ListView mListView;
    private FloatingActionButton mFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);
        mListView = findViewById(R.id.listView1);
        mFAB = findViewById(R.id.mFAB);
        make();
        Log.d("ankit",matList.toString());
        /*adapter = new MedDetailAdapter(getApplicationContext(), matList);
        mListView.setAdapter(adapter);*/
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedListActivity.this,AddMedActivity.class));
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MedListActivity.this, MedDetailActivity.class);
                MedDetailObject mat2 = matList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("MedDetails", mat2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void make(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("medicines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ankit","23wsdsadw");
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    MedDetailObject med = new MedDetailObject();
                    matList.add(d.getValue(MedDetailObject.class));
                    System.out.println(d.getValue(MedDetailObject.class));
                }
                MedDetailAdapter adapter = new MedDetailAdapter(getApplicationContext(), R.layout.med_details, matList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}