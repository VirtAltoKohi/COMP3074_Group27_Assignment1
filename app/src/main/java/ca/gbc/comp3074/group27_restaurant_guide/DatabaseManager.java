package ca.gbc.comp3074.group27_restaurant_guide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Insert data into the database
    public long insertData(String name, String location, double rating) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("rating", rating);

        return database.insert("RestaurantList", null, values);
    }

    // Query data from the database
    public Cursor fetchData() {
        String[] columns = {"id", "name", "location", "rating"};
        return database.query("RestaurantList", columns, null, null, null, null, null);
    }

    // Update data in the database
    public int updateData(long id, String newName, String newLocation, double newRating) {
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("location", newLocation);
        values.put("rating", newRating);

        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};

        return database.update("RestaurantList", values, whereClause, whereArgs);
    }

    // Delete data from the database
    public int deleteData(long id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};

        return database.delete("RestaurantList", whereClause, whereArgs);
    }


}
