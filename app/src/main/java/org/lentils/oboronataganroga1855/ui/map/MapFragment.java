package org.lentils.oboronataganroga1855.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.lentils.oboronataganroga1855.MainNavigationActivity;
import org.lentils.oboronataganroga1855.PlaceActivity;
import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.Place;
import org.lentils.oboronataganroga1855.model.ReadJSONPlaces;

import java.io.IOException;
import java.util.ArrayList;

import static org.lentils.oboronataganroga1855.MainNavigationActivity.places;

public class MapFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap map;
    private boolean permissionDenied = false;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    int indexPlaceMarker;

    FragmentTransaction transaction;

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
        LatLng locationCenter = new LatLng(47.220646, 38.914722);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCenter, 13.0f));
    };


    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(requireContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
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
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                break;
            }
        }
        Intent placeIntent = new Intent(requireContext(), PlaceActivity.class);
        placeIntent.putExtra("type", places.get(indexPlaceMarker).getType());
        placeIntent.putExtra("title", places.get(indexPlaceMarker).getTitle());
        placeIntent.putExtra("description", places.get(indexPlaceMarker).getDescription());
        placeIntent.putExtra("data", places.get(indexPlaceMarker).getData());
        placeIntent.putExtra("time", places.get(indexPlaceMarker).getTime());
        startActivity(placeIntent);
    }
}
