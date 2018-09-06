package com.adarsh.medid;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;

public class MedDetailObject extends ArrayList<Parcelable> implements Parcelable{

    String medName, medDesc, medQty,user;

    public MedDetailObject() {
    }

    public MedDetailObject(String medName, String medDesc, String medQty,String user) {
        this.medName = medName;
        this.medDesc = medDesc;
        this.medQty = medQty;
        this.user = user;
    }

    protected MedDetailObject(Parcel in) {
        medName = in.readString();
        medDesc = in.readString();
        medQty = in.readString();
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

    public String getMedName() {

        return medName;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public String getMedQty() {
        return medQty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(medName);
        parcel.writeString(medDesc);
        parcel.writeString(medQty);
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
