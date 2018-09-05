package com.adarsh.medid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedDetailAdapter extends ArrayAdapter {

    private Context contexts;
    private List<MedDetailObject> Names;
    ArrayList<MedDetailObject> de;

    int resourse1;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public MedDetailAdapter(Context mcontext,int resourse, ArrayList<MedDetailObject> NameParam) {
        super(mcontext, resourse, NameParam);
        contexts = mcontext;
        Names = NameParam;
        resourse1 = resourse;

        Log.d("ankit","Asdsad"+NameParam);
    }
/*
    public View getView(int position, View view, ViewGroup parent) {
        View matDets = view;

        Log.d("ankit","Asdsad");
        LayoutInflater l = (LayoutInflater) this.contexts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        matDets = l.inflate(R.layout.med_details, null, true);

        *//*final TextView name = matDets.findViewById(R.id.tvName);
        final TextView desc = matDets.findViewById(R.id.tvDesc);
        final TextView qty = matDets.findViewById(R.id.tvQty);*//*
*//*
        name.setText("asdasdsadsda");
        desc.setText("Asdsada");
        qty.setText("asad");*//*
       *//* db.child("medicines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    MedDetailObject med = new MedDetailObject();
                    de.add(d.getValue(MedDetailObject.class));
                    if(de!=null){
                        name.setText(med.getMedName());
                        desc.setText(med.getMedDesc());
                        qty.setText(med.getMedQty());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*//*

        return matDets;
    }*/

    @Override
    public int getCount() {
        return Names.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View matDets;
        LayoutInflater l = (LayoutInflater) this.contexts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        matDets = l.inflate(this.resourse1, null, true);

        final TextView name = matDets.findViewById(R.id.tvName);
        final TextView desc = matDets.findViewById(R.id.tvDesc);
        final TextView qty = matDets.findViewById(R.id.tvQty);

        name.setText(Names.get(position).getMedName());
        desc.setText(Names.get(position).getMedDesc());
        return matDets;
    }
}