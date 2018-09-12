package com.adarsh.medid;

public class Med {

    String name, desc, qty, med_id;

    public Med() {

    }

    public Med(String name, String desc, String qty, String med_id) {

        this.name = name;
        this.desc = desc;
        this.qty = qty;
        this.med_id = med_id;
    }

    public String getName() {

        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getQty() {
        return qty;
    }

    public String getMed_id() {
        return med_id;
    }
}
