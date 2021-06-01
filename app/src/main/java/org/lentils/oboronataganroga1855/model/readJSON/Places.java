package org.lentils.oboronataganroga1855.model.readJSON;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.Place;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Places {

    public static ArrayList<Place> readPlacesJSONFile(Context context) throws IOException, JSONException {

        String jsonText = readText(context, R.raw.places);

        JSONArray jsonRoot = new JSONArray(jsonText);
        ArrayList<Place> places = new ArrayList<>(jsonRoot.length());
        for (int i = 0; i < jsonRoot.length(); i++) {
            String type = jsonRoot.getJSONObject(i).getString("type");
            String title = jsonRoot.getJSONObject(i).getString("title");
            String description = jsonRoot.getJSONObject(i).getString("description");
            Double map1 = jsonRoot.getJSONObject(i).getJSONArray("map").getDouble(0);
            Double map2 = jsonRoot.getJSONObject(i).getJSONArray("map").getDouble(1);
            String data = jsonRoot.getJSONObject(i).getString("data");
            String time = jsonRoot.getJSONObject(i).getString("time");
            places.add(new Place(type, title, description, map1, map2, data, time));
        }
        return places;
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
