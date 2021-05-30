package org.lentils.oboronataganroga1855.adpter;

import android.graphics.drawable.Drawable;

public class settings_cards {
        private String text1;
        private String text2;
        private Drawable img;

        public settings_cards(String name1, String name2, Drawable idImg) {
            this.text1 = name1;
            this.text2 = name2;
            this.img = idImg;
        }

    String getText1() {
        return text1;
    }

    String getText2() {
        return text2;
    }

    Drawable getImg() {
        return img;
    }
}