package com.samu.trab2_samuelmolendolff.model;

import android.os.Parcelable;

import java.util.ArrayList;

public class Product extends ArrayList<Parcelable> {

    private String name, brand;

    public Product(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }
}
