package org.lentils.oboronataganroga1855.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;
import org.lentils.oboronataganroga1855.R;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Place {
    private String type;
    private String title;
    private String description;
    private ArrayList<Double> map = new ArrayList<>(2);
    private String data;
    private String time;
    private Marker marker = null;

    public Place(String type, String title, String description, Double map1, Double map2, String data, String time) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.map.add(map1);
        this.map.add(map2);
        this.data = data;
        this.time = time;
    }

    public Place(String type, String title, String description, String data, String time) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.data = data;
        this.time = time;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getMap1() {
        return this.map.get(0);
    }

    public Double getMap2() {
        return this.map.get(1);
    }

    public String getData() {
        return this.data;
    }

    public String getTime() {
        return this.time;
    }

    public MarkerOptions addMarker(Resources resources) {
        String d;
        if (this.title.length() > 30) {
            d = getDescription().substring(0, this.title.length()) + "...";
        } else if (this.description.length() > 27) {
            d = getDescription().substring(0, 27) + "...";
        } else {
            d = getDescription();
        }
        Drawable circleDrawable = resources.getDrawable(R.drawable.ic_map_24dp);
        BitmapDescriptor icon = getMarkerIconFromDrawable(circleDrawable);
        MarkerOptions Marker = new MarkerOptions()
                .position(new LatLng(this.map.get(0), this.map.get(1)))
                .icon(BitmapDescriptorFactory.defaultMarker(80))
                .title(getTitle())
                .snippet(d);
        return Marker;
    }

    @NotNull
    private BitmapDescriptor getMarkerIconFromDrawable(@NotNull Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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
