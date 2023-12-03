package ca.gbc.comp3074.group27_restaurant_guide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Define database name and version
    private static final String DATABASE_NAME = "RestaurantGuide.db";
    private static final int DATABASE_VERSION = 1;

    // Define table creation SQL statement
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE RestaurantsList (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "location TEXT," +
                    "rating DOUBLE,);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        // You can add ALTER TABLE or other upgrade logic here.
    }
}
