package org.lentils.oboronataganroga1855;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.lentils.oboronataganroga1855.data.Database;
import org.lentils.oboronataganroga1855.register.ActivityRegister;

public class SplashActivity extends AppCompatActivity {

    Database databaseHelper;
    SQLiteDatabase db;
    Cursor dataCursor;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new Database(getApplicationContext());

        db = databaseHelper.getReadableDatabase();
        dataCursor =  db.rawQuery("SELECT " + Database.COLUMN_ID + " FROM data", null);
        Integer count = dataCursor.getCount();
        Log.i("MyApp", "user data count " + count.toString());
        Intent intent;
        if (count == 0) {
            intent = new Intent(this, ActivityRegister.class);
        } else {
            intent = new Intent(this, MainNavigationActivity.class);
        }
        db.close();
        dataCursor.close();
        startActivity(intent);
        finish();
        /*
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this, ActivityRegister.class);
        startActivity(intent);
        finish();*/
    }
}