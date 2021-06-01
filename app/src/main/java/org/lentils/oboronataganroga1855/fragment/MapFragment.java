package org.lentils.oboronataganroga1855.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.lentils.oboronataganroga1855.HintMarkerActivity;
import org.lentils.oboronataganroga1855.R;

import static org.lentils.oboronataganroga1855.MainNavigationActivity.hostels;
import static org.lentils.oboronataganroga1855.MainNavigationActivity.places;
import static org.lentils.oboronataganroga1855.MainNavigationActivity.restaurants;

public class MapFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap map;
    private boolean permissionDenied = false;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    int indexPlaceMarker = -1;

    @SuppressLint("MissingPermission")
    private OnMapReadyCallback callback = map -> {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        } else {
            PermissionUtils.requestPermission((AppCompatActivity) requireActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        map.setOnInfoWindowClickListener(this);
        for (int i = 0; i < places.size(); i++) {
            map.addMarker(places.get(i).toMarker());
        }
        for (int i = 0; i < hostels.size(); i++) {
            map.addMarker(hostels.get(i).toMarker());
        }
        for (int i = 0; i < restaurants.size(); i++) {
            map.addMarker(restaurants.get(i).toMarker());
        }
        LatLng locationCenter = new LatLng(47.220646, 38.914722);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCenter, 13.0f));
    };


    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(requireContext(), "Current location:\n" + location, Toast.LENGTH_LONG);
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        } else {
            PermissionUtils.requestPermission((AppCompatActivity) requireActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation();
        } else {
            permissionDenied = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(requireContext(), HintMarkerActivity.class);
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                intent.putExtra("type", places.get(indexPlaceMarker).getType());
                intent.putExtra("title", places.get(indexPlaceMarker).getTitle());
                intent.putExtra("description", places.get(indexPlaceMarker).getDescription());
                intent.putExtra("data", places.get(indexPlaceMarker).getData());
                intent.putExtra("time", places.get(indexPlaceMarker).getTime());
                break;
            }
        }
        for (int i = 0; i < hostels.size(); i++) {
            if (hostels.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                intent.putExtra("type", hostels.get(indexPlaceMarker).getType());
                intent.putExtra("title", hostels.get(indexPlaceMarker).getTitle());
                intent.putExtra("description", hostels.get(indexPlaceMarker).getDiscount());
                intent.putExtra("data", "");
                intent.putExtra("time", "");
                break;
            }
        }
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                intent.putExtra("type", restaurants.get(indexPlaceMarker).getType());
                intent.putExtra("title", restaurants.get(indexPlaceMarker).getTitle());
                intent.putExtra("description", restaurants.get(indexPlaceMarker).getDiscount());
                intent.putExtra("data", "");
                intent.putExtra("time", "");
                break;
            }
        }

        startActivity(intent);
    }
}
