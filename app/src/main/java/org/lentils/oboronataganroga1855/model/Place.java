package org.lentils.oboronataganroga1855.model;

import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Array;
import java.util.ArrayList;

public class Place {
    private String type;
    private String title;
    private String description;
    private ArrayList<Double> map = new ArrayList<>(2);
    private String data;
    private String time;
    private BitmapDescriptor icon;

    public Place(String type, String title, String description, Double map1, Double map2, String data, String time) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.map.add(map1);
        this.map.add(map2);
        this.data = data;
        this.time = time;
    }

    public String setType() {
        return this.type;
    }

    public String setTitle() {
        return this.title;
    }

    public String setDescription() {
        return this.description;
    }

    public ArrayList<Double> setMap() {
        return this.map;
    }

    public String setData() {
        return this.data;
    }

    public String setTime() {
        return this.time;
    }

    public MarkerOptions toMarker() {
        if (this.type.equals("attraction")) {
            this.icon = (BitmapDescriptor) BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
        } else {
            this.icon = (BitmapDescriptor) BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
        }
        return new MarkerOptions().position(new LatLng(this.map.get(0), this.map.get(1))).icon(icon).title(this.title).snippet(this.description);
    }
}
