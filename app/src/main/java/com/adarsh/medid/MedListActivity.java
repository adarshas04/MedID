package com.adarsh.medid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<MedDetailObject> matList = new ArrayList<>();
    MedDetailAdapter adapter;
    DatabaseReference db2;
    private ListView mListView;
    private FloatingActionButton mFAB;
    ArrayList<Med> cartList = new ArrayList<>();
    Med c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseAuth firebase = FirebaseAuth.getInstance();
        mListView = findViewById(R.id.listView1);
        mFAB = findViewById(R.id.mFAB);
        db2 = FirebaseDatabase.getInstance().getReference();

        Log.d("ankit",matList.toString());
        /*adapter = new MedDetailAdapter(getApplicationContext(), matList);
        mListView.setAdapter(adapter);*/

        db2.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProfileObject u = dataSnapshot.getValue(ProfileObject.class);
                if (u != null) {
                    if (u.getStore_name() != null) {
                        mFAB.setVisibility(View.VISIBLE);
                        make();
                    }
                    else {
                        mFAB.setVisibility(View.GONE);
                        makeCart();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mFAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedListActivity.this,AddMedActivity.class));
            }

        });



        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MedListActivity.this, MedDetailActivity.class);
                MedDetailObject mat2 = matList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("MedDetails", mat2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        db2.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProfileObject u = dataSnapshot.getValue(ProfileObject.class);
                if (u != null) {
                    if (u.getStore_name() != null) {} else {
                        getMenuInflater().inflate(R.menu.menu_main, menu);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(MedListActivity.this, "Cart Opened", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(MedListActivity.this,CartActivity.class));
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void make(){
        Log.d("med","aa gaya");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("medicines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ankit","23wsdsadw");
                matList.clear();
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    MedDetailObject med = new MedDetailObject();
                    matList.add(d.getValue(MedDetailObject.class));
                }
                MedDetailAdapter adapter = new MedDetailAdapter(getApplicationContext(), R.layout.med_details, matList);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void makeCart(){
        Log.d("cart","aa gaya");
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("medicines");
//        Log.d("nsdj","yuppppppppp   "+db.child("name").toString());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartList.clear();
//                Log.d("adapter cart bahar","ssssssss   "+dataSnapshot.child("name").getValue());
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    //Log.d("adapter cart andar","ssssssss   "+d.getKey());
                    c = d.getValue(Med.class);
                    cartList.add(c);
//                    Log.i("yo act",""+c.getQty());
                }
                EditCartAdapter adapter = new EditCartAdapter(getApplicationContext(), R.layout.med_details, cartList );
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
