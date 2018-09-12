package com.adarsh.medid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    TextView name;
    Button place;
    TextView desc, qty;
    ArrayList<Cart> cartList = new ArrayList<>();
    Cart c;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.listView);

        name = findViewById(R.id.tvName);
        qty = findViewById(R.id.tvQty);

        place = findViewById(R.id.order);

        make();

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mAddMed = FirebaseDatabase.getInstance().getReference().child("Active Orders").child("4J47h937vNOKUhWVsWS5bKgu0OJ2").child(FirebaseAuth.getInstance().getUid());
                Log.d("yoloo", FirebaseAuth.getInstance().getUid());
                DatabaseReference cart = FirebaseDatabase.getInstance().getReference().child("cart").child(FirebaseAuth.getInstance().getUid());
                cart.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        cartList.clear();
//                Log.d("adapter cart bahar","ssssssss   "+dataSnapshot.child("name").getValue());
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            //Log.d("adapter cart andar","ssssssss   "+d.getKey());
                            c = d.getValue(Cart.class);
                            cartList.add(c);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                String oid = mAddMed.push().getKey();
                Log.d("jsasaj","sac "+name+"sd "+desc+"sca "+qty);
                mAddMed.child(oid).setValue(cartList);
            }
        });

    }

    private void make(){
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("cart").child(FirebaseAuth.getInstance().getUid()).getRef();
//        Log.d("nsdj","yuppppppppp   "+db.child("name").toString());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartList.clear();
//                Log.d("adapter cart bahar","ssssssss   "+dataSnapshot.child("name").getValue());
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    //Log.d("adapter cart andar","ssssssss   "+d.getKey());
                    c = d.getValue(Cart.class);
                    cartList.add(c);
//                    Log.i("yo act",""+c.getQty());
                }
                CartAdapter adapter = new CartAdapter(getApplicationContext(), R.layout.cart_list, cartList );
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
