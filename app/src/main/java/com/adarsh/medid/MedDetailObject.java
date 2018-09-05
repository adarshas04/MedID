package com.adarsh.medid;

public class MedDetailObject {

    String medName, medDesc;
    int medQty;

    public MedDetailObject() {
    }

    public MedDetailObject(String medName, String medDesc, int medQty) {
        this.medName = medName;
        this.medDesc = medDesc;
        this.medQty = medQty;
    }

    public String getMedName() {

        return medName;
    }

    public String getMedDesc() {
        return medDesc;
    }

    public int getMedQty() {
        return medQty;
    }

}
