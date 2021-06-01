package org.lentils.oboronataganroga1855.model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Restaurant {
    private String type;
    private String title;
    private String discount;
    private ArrayList<Double> map = new ArrayList<>(2);

    public Restaurant(String type, String title, String discount, Double map1, Double map2) {
        this.type = type;
        this.title = title;
        this.discount = discount;
        this.map.add(map1);
        this.map.add(map2);
    }

    public Restaurant(String type, String title, String discount) {
        this.type = type;
        this.title = title;
        this.discount = discount;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDiscount() {
        return this.discount;
    }

    public Double getMap1() {
        return this.map.get(0);
    }

    public Double getMap2() {
        return this.map.get(1);
    }

    public MarkerOptions toMarker() {
        MarkerOptions Marker = new MarkerOptions()
                .position(new LatLng(this.map.get(0), this.map.get(1)))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .title(this.title)
                .snippet(this.discount);
        return Marker;
    }
}
