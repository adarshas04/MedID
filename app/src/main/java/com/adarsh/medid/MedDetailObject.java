package com.adarsh.medid;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class MedDetailObject extends ArrayList<Parcelable> implements Parcelable{

    String name, desc, qty;

    public MedDetailObject() {
    }

    public MedDetailObject(String name, String desc, String qty) {
        this.name = name;
        this.desc = desc;
        this.qty = qty;
    }

    protected MedDetailObject(Parcel in) {
        name = in.readString();
        desc = in.readString();
        qty = in.readString();
    }

    public static final Creator<MedDetailObject> CREATOR = new Creator<MedDetailObject>() {
        @Override
        public MedDetailObject createFromParcel(Parcel in) {
            return new MedDetailObject(in);
        }

        @Override
        public MedDetailObject[] newArray(int size) {
            return new MedDetailObject[size];
        }
    };

    public String getName() {

        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getQty() {
        return qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(desc);
        parcel.writeString(qty);
    }

    @Override
    public Stream<Parcelable> stream() {
        return null;
    }

    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
}
