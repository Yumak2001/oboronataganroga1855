package org.lentils.oboronataganroga1855.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.Place;

public class HintMarkerFragment extends Fragment {

    Place place;
    TextView typeText, titleText, descriptionText, dataText, timeText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("tag", "tag");
        typeText = (TextView) view.findViewById(R.id.marker_hint_type);
        titleText = (TextView) view.findViewById(R.id.marker_hint_title);
        descriptionText = (TextView) view.findViewById(R.id.marker_hint_description);
        dataText = (TextView) view.findViewById(R.id.marker_hint_data);
        timeText = (TextView) view.findViewById(R.id.marker_hint_time);
        String typeTextSTR;
        switch (place.getType()){
            case "hotel":
                typeTextSTR = "Отель";
                break;
            case "attraction":
                typeTextSTR = "Достопримечательность";
                break;
            case "restaurant":
                typeTextSTR = "Ресторан";
                break;
            default:
                typeTextSTR = "Неизвестно что это";
        }
        typeText.setText(typeTextSTR);
        titleText.setText(place.getTitle());
        descriptionText.setText(place.getDescription());
        dataText.setText(place.getData());
        timeText.setText(place.getTime());

        /*LinearLayout back = (LinearLayout) view.findViewById(R.id.layout_marker_hint);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Hint", "Back");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .hide(HintMarkerFragment.this)
                        .remove(HintMarkerFragment.this)
                        .commit();
            }
        });*/


        ImageView backButton = (ImageView) view.findViewById(R.id.marker_hint_button_back);
        backButton.setClickable(true);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Hint", "Back");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .hide(HintMarkerFragment.this)
                        .remove(HintMarkerFragment.this)
                        .commit();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        place = new Place(getArguments().getString("type"),
                getArguments().getString("title"),
                getArguments().getString("description"),
                getArguments().getString("data"),
                getArguments().getString("time"));
        Log.i("PLACE", place.getType());
        Log.i("PLACE", place.getTitle());
        Log.i("PLACE", place.getDescription());
        Log.i("PLACE", place.getData());
        Log.i("PLACE", place.getTime());
        return inflater.inflate(R.layout.fragment_marker_hint, container, false);
    }
}