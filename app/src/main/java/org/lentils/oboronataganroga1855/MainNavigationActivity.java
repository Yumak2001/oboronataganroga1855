package org.lentils.oboronataganroga1855;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.lentils.oboronataganroga1855.model.Place;
import org.lentils.oboronataganroga1855.model.ReadJSONPlaces;
import org.lentils.oboronataganroga1855.ui.account.AccountFragment;
import org.lentils.oboronataganroga1855.ui.map.MapFragment;
import org.lentils.oboronataganroga1855.ui.sites.SitesFragment;

import java.io.IOException;
import java.util.ArrayList;

public class MainNavigationActivity extends AppCompatActivity {

    final Fragment accountFragment = new AccountFragment();
    final Fragment sitesFragment = new SitesFragment();
    final Fragment mapFragment = new MapFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = accountFragment;

    public static ArrayList<Place> places;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_activity, mapFragment, "3").hide(mapFragment).commit();
        fm.beginTransaction().add(R.id.main_activity, sitesFragment, "2").hide(sitesFragment).commit();
        fm.beginTransaction().add(R.id.main_activity, accountFragment, "1").commit();

        try {
            places = ReadJSONPlaces.readPlacesJSONFile(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_account:
                    fm.beginTransaction().hide(active).show(accountFragment).commit();
                    active = accountFragment;
                    return true;

                case R.id.navigation_sites:
                    fm.beginTransaction().hide(active).show(sitesFragment).commit();
                    active = sitesFragment;
                    return true;

                case R.id.navigation_maps:
                    fm.beginTransaction().hide(active).show(mapFragment).commit();
                    active = mapFragment;
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}