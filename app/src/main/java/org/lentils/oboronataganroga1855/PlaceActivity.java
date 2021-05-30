package org.lentils.oboronataganroga1855;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.lentils.oboronataganroga1855.model.Place;

public class PlaceActivity extends AppCompatActivity {

    Place place;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        place = new Place(getIntent().getStringExtra("type"),
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("description"),
                getIntent().getStringExtra("data"),
                getIntent().getStringExtra("time"));
        Log.i("PLACE", place.getType());
        Log.i("PLACE", place.getTitle());
        Log.i("PLACE", place.getDescription());
        Log.i("PLACE", place.getData());
        Log.i("PLACE", place.getTime());
    }
}