package org.lentils.oboronataganroga1855.model.readJSON;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.Place;
import org.lentils.oboronataganroga1855.model.Restaurant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Restaurants {

    public static ArrayList<Restaurant> readRestaurantsJSONFile(Context context) throws IOException, JSONException {

        String jsonText = readText(context, R.raw.restaurants);

        JSONArray jsonRoot = new JSONArray(jsonText);
        ArrayList<Restaurant> restaurants = new ArrayList<>(jsonRoot.length());
        for (int i = 0; i < jsonRoot.length(); i++) {
            String type = jsonRoot.getJSONObject(i).getString("type");
            String title = jsonRoot.getJSONObject(i).getString("title");
            String discount = jsonRoot.getJSONObject(i).getString("discount");
            Double map1 = jsonRoot.getJSONObject(i).getJSONArray("map").getDouble(0);
            Double map2 = jsonRoot.getJSONObject(i).getJSONArray("map").getDouble(1);
            restaurants.add(new Restaurant(type, title, discount, map1, map2));
        }
        return restaurants;
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
