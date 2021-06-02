package org.lentils.oboronataganroga1855.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Hostel {
    private String type;
    private String title;
    private String discount;
    private ArrayList<Double> map = new ArrayList<>(2);
    private Marker marker = null;

    public Hostel(String type, String title, String discount, Double map1, Double map2) {
        this.type = type;
        this.title = title;
        this.discount = discount;
        this.map.add(map1);
        this.map.add(map2);
    }

    public Hostel(String type, String title, String discount) {
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
                    .icon(BitmapDescriptorFactory.defaultMarker(300))
                    .title(this.title)
                    .snippet(this.discount);
        return Marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setVisibleMarker(Boolean visibleMarker) {
        if (this.marker != null) {
            this.marker.setVisible(visibleMarker);
        }
    }
}
