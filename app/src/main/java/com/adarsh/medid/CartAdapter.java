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

public class CartAdapter extends ArrayAdapter {

    private Context contexts;
    String key;

    int resourse1;
    ArrayList<Cart> cartList;

    public CartAdapter(Context mcontext,int resourse, ArrayList<Cart> NameParam) {
        super(mcontext, resourse, NameParam);
        contexts = mcontext;
        resourse1 = resourse;
        cartList = NameParam;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View matDets;
        LayoutInflater l = (LayoutInflater) this.contexts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        matDets = l.inflate(this.resourse1, null, true);


        final TextView name = matDets.findViewById(R.id.tvName);
        final TextView qty = matDets.findViewById(R.id.tvQty);
        final TextView increase, decrease;
        increase = matDets.findViewById(R.id.increase);
        decrease = matDets.findViewById(R.id.decrease);

        name.setText(cartList.get(position).name);
        qty.setText(cartList.get(position).qty);

//        Log.i("yo adap",cartList.get(position).name);

        final DatabaseReference db  = FirebaseDatabase.getInstance().getReference().child("cart").child(FirebaseAuth.getInstance().getUid());

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(qty.getText().toString());
                a += 1;

                    qty.setText(Integer.toString(a));

                String name = cartList.get(position).getName();

                    final int finalA = a;
                    db.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for( DataSnapshot d : dataSnapshot.getChildren()) {
                                key = d.getKey().toString();
                                db.child(key).child("qty").setValue(Integer.toString(finalA));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //db.child(key).child("qty").setValue(Integer.toString(a));
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
                    final int finalA = a;
                    db.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for( DataSnapshot d : dataSnapshot.getChildren()) {
                                key = d.getKey().toString();
                                Log.d("andar","aaaaaaaaaaaaaaaaaa "+key);
                                db.child(key).child("qty").setValue(Integer.toString(finalA));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
//                    Log.d("bahar","aaaaaaaaaaaaaaaaaa "+key);
//                   db.child(key).child("qty").setValue(Integer.toString(a));
                }
                if(a<0){
                    Toast.makeText(getContext(), "Quantity should be > 0",Toast.LENGTH_SHORT).show();
                }
                else if(a==0) {
                    qty.setText(Integer.toString(a));
                    db.child("name").removeValue();
                    db.child("qty").removeValue();
                }

            }
        });

        return matDets;
    }



}
