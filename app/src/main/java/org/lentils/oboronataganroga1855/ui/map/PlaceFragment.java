package org.lentils.oboronataganroga1855.ui.map;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.Place;

public class PlaceFragment extends Fragment {

    Place place;

    public static PlaceFragment newInstance() {
        return new PlaceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.place_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            place = new Place(bundle.getString("type"),
                    bundle.getString("title"),
                    bundle.getString("description"),
                    bundle.getString("data"),
                    bundle.getString("time"));
        }
        Log.i("PLACE", "start");
        Log.i("PLACE", place.getType());
        Log.i("PLACE", place.getTitle());
        Log.i("PLACE", place.getDescription());
        Log.i("PLACE", place.getData());
        Log.i("PLACE", place.getTime());
    }

}