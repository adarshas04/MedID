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
        nameTV = findViewById(R.id.medName);
        descTV = findViewById(R.id.medDesc);
        qtyTV = findViewById(R.id.medQty);
        url = "https://firebasestorage.googleapis.com/v0/b/medid-a1a84.appspot.com/o/med.jpg?alt=media&token=f369c541-e26a-4fa2-8157-3e017efdd030";
        medList = (MedDetailObject) this.getIntent().getExtras().getParcelableArrayList("MedDetails");
        nameTV.setText(medList.getMedName());
        descTV.setText(medList.getMedDesc());
        qtyTV.setText(medList.getMedQty());

        Glide.with(getApplicationContext()).load(url).into(ivIcon);
    }
}
