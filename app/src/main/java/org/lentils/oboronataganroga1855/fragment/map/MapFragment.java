package org.lentils.oboronataganroga1855.fragment.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.lentils.oboronataganroga1855.MainNavigationActivity;
import org.lentils.oboronataganroga1855.fragment.HintMarkerFragment;
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

    HintMarkerFragment hintMarkerFragment = new HintMarkerFragment();

    int indexPlaceMarker = -1;

    Boolean isVisible_place = true;
    Boolean isVisible_restaurant = true;
    Boolean isVisible_hostel = true;

    @SuppressLint("MissingPermission")
    private OnMapReadyCallback callback = map -> {
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
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
            places.get(i).setMarker(map.addMarker(places.get(i).addMarker(getResources())));
        }
        for (int i = 0; i < hostels.size(); i++) {
            hostels.get(i).setMarker(map.addMarker(hostels.get(i).toMarker()));
        }
        for (int i = 0; i < restaurants.size(); i++) {
            restaurants.get(i).setMarker(map.addMarker(restaurants.get(i).toMarker()));
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
        ImageButton buttonLayersMenu = view.findViewById(R.id.button_menu_layers_map);
        buttonLayersMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }


    private void showPopupMenu(View v) {
        PopupMenu menuVisibleLayers = new PopupMenu(getContext(), v);
        menuVisibleLayers.inflate(R.menu.item_layers_map_menu);
        menuVisibleLayers.getMenu().findItem(R.id.layers_menu_button_place).setChecked(isVisible_place);
        menuVisibleLayers.getMenu().findItem(R.id.layers_menu_button_restaurant).setChecked(isVisible_restaurant);
        menuVisibleLayers.getMenu().findItem(R.id.layers_menu_button_hostel).setChecked(isVisible_hostel);
        menuVisibleLayers
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.layers_menu_button_place:
                                if (item.isChecked()) {
                                    isVisible_place = false;
                                } else {
                                    isVisible_place = true;
                                }
                                item.setChecked(isVisible_place);
                                for (int i = 0; i < places.size(); i++) {
                                    places.get(i).setVisibleMarker(isVisible_place);
                                }
                                return true;
                            case R.id.layers_menu_button_restaurant:
                                if (item.isChecked()) {
                                    isVisible_restaurant = false;
                                } else {
                                    isVisible_restaurant = true;
                                }
                                item.setChecked(isVisible_restaurant);
                                for (int i = 0; i < hostels.size(); i++) {
                                    hostels.get(i).setVisibleMarker(isVisible_restaurant);
                                }
                                return true;
                            case R.id.layers_menu_button_hostel:
                                if (item.isChecked()) {
                                    isVisible_hostel = false;
                                } else {
                                    isVisible_hostel = true;
                                }
                                item.setChecked(isVisible_hostel);
                                for (int i = 0; i < restaurants.size(); i++) {
                                    restaurants.get(i).setVisibleMarker(isVisible_hostel);
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            menuVisibleLayers.setForceShowIcon(true);
        }
        menuVisibleLayers.show();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle bundle = new Bundle();

        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                bundle.putString("type", places.get(indexPlaceMarker).getType());
                bundle.putString("title", places.get(indexPlaceMarker).getTitle());
                bundle.putString("description", places.get(indexPlaceMarker).getDescription());
                bundle.putString("data", places.get(indexPlaceMarker).getData());
                bundle.putString("time", places.get(indexPlaceMarker).getTime());
                break;
            }
        }
        for (int i = 0; i < hostels.size(); i++) {
            if (hostels.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                bundle.putString("type", hostels.get(indexPlaceMarker).getType());
                bundle.putString("title", hostels.get(indexPlaceMarker).getTitle());
                bundle.putString("description", hostels.get(indexPlaceMarker).getDiscount());
                bundle.putString("data", "");
                bundle.putString("time", "");
                break;
            }
        }
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getTitle().equals(marker.getTitle())) {
                indexPlaceMarker = i;
                bundle.putString("type", restaurants.get(indexPlaceMarker).getType());
                bundle.putString("title", restaurants.get(indexPlaceMarker).getTitle());
                bundle.putString("description", restaurants.get(indexPlaceMarker).getDiscount());
                bundle.putString("data", "");
                bundle.putString("time", "");
                break;
            }
        }
        hintMarkerFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction().add(R.id.main_activity, hintMarkerFragment, "hint")
                .show(hintMarkerFragment)
                .addToBackStack("3")
                .commit();
    }
}
