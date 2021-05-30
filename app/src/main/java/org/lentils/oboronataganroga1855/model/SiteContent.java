package org.lentils.oboronataganroga1855.model;

import android.content.Context;

import org.json.JSONException;
import org.lentils.oboronataganroga1855.MainNavigationActivity;
import org.lentils.oboronataganroga1855.model.Place;
import org.lentils.oboronataganroga1855.model.ReadJSONPlaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SiteContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final ArrayList<Place> places = MainNavigationActivity.places;

    static {
        for (int i = 0; i < places.size(); i++) {
            addItem(createDummyItem(places.get(i)));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(Place place) {
        return new DummyItem(String.valueOf(place), place.getTitle(), place.getDescription(), place.getData(), place.getTime(), "Location");
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public String title;
        public String data;
        public String time;
        public String description;
        public String location;

        public DummyItem(String id, String title, String description, String data, String time, String location) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.data = data;
            this.time = time;
            this.location = location;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}