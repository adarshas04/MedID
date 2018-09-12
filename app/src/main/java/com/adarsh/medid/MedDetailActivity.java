package com.adarsh.medid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MedDetailActivity extends AppCompatActivity {

    ImageView ivIcon;
    TextView nameTV;
    TextView descTV;
    TextView qtyTV;
    MedDetailObject medList;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_detail);
        ivIcon = findViewById(R.id.icon);
        nameTV = findViewById(R.id.name);
        descTV = findViewById(R.id.desc);
        qtyTV = findViewById(R.id.qty);
        url = "https://firebasestorage.googleapis.com/v0/b/medid-a1a84.appspot.com/o/med.jpg?alt=media&token=f369c541-e26a-4fa2-8157-3e017efdd030";
        medList = (MedDetailObject) this.getIntent().getExtras().getParcelableArrayList("MedDetails");
        nameTV.setText(medList.getName());
        descTV.setText(medList.getDesc());
        qtyTV.setText(medList.getQty());

        Glide.with(getApplicationContext()).load(url).into(ivIcon);
    }
}
