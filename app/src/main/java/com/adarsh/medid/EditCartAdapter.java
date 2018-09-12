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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditCartAdapter extends ArrayAdapter {

    private Context contexts;
    private ArrayList<Med> cartList;

    int resourse1;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public EditCartAdapter(Context mcontext,int resourse, ArrayList<Med> NameParam) {
        super(mcontext, resourse, NameParam);
        contexts = mcontext;
        cartList = NameParam;
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
        return cartList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View matDets;
        LayoutInflater l = (LayoutInflater) this.contexts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        matDets = l.inflate(this.resourse1, null, true);


        final TextView name = matDets.findViewById(R.id.tvName);
        final TextView desc = matDets.findViewById(R.id.tvDesc);
        final TextView qty = matDets.findViewById(R.id.tvQty);
        final TextView increase, decrease;
        increase = matDets.findViewById(R.id.increase);
        decrease = matDets.findViewById(R.id.decrease);
        Log.d("name",""+cartList.get(position).getName());
        Log.d("name",""+cartList.get(position).getDesc());
        name.setText(cartList.get(position).getName().toString());
        desc.setText(cartList.get(position).getDesc().toString());
        qty.setText("0");

        final DatabaseReference db  = FirebaseDatabase.getInstance().getReference().child("cart").child(FirebaseAuth.getInstance().getUid());

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("medicines").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("ankit","23wsdsadw");
                        medList.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()){
                            //Log.d("wdsdw","vlueeeeeeeeeeee "+d.getValue(Med.class));
                            medList.add(d.getValue(Med.class));
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/

                int a = Integer.parseInt(qty.getText().toString());
                a += 1;
                if(Integer.parseInt(cartList.get(position).getQty())>=a) {

                    qty.setText(Integer.toString(a));

                }
                else
                    Toast.makeText(getContext(), "Stock Limit Reached",Toast.LENGTH_SHORT).show();

                String name = cartList.get(position).getName();
                String desc = cartList.get(position).getDesc();
                String quant = qty.getText().toString();

                if(Integer.parseInt(cartList.get(position).getQty())>=a) {

                    db.child(cartList.get(position).getMed_id()).child("name").setValue(name);
                    db.child(cartList.get(position).getMed_id()).child("qty").setValue(Integer.toString(a));

                }

                // Log.d("adarsh",db.child(cartList.get(position).getMed_id()).getKey());

            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(qty.getText().toString());
                a -= 1;
                String name = cartList.get(position).getName();
                String quant = qty.getText().toString();
                if(a>0) {
                    qty.setText(Integer.toString(a));
                    db.child(cartList.get(position).getMed_id()).child("name").setValue(name);
                    db.child(cartList.get(position).getMed_id()).child("qty").setValue(Integer.toString(a));
                }
                if(a<0){
                    Toast.makeText(getContext(), "Quantity should be > 0", Toast.LENGTH_SHORT).show();
                }
                else if(a==0) {
                    qty.setText(Integer.toString(a));
                    db.child(cartList.get(position).getMed_id()).removeValue();
                }

                db.child("cart").child(cartList.get(position).getMed_id()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("adarsh","aaaaaaaaaaaaaaaaaaaaaaaaaa"+dataSnapshot.getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        return matDets;
    }
}