package ca.georgebrown.comp3074.groupproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchAndFilterActivity extends Activity {
    private EditText searchField;
    private ListView listView;
    private ArrayAdapter<String> adapter; // This is a simple adapter for example purposes
    private ArrayList<String> listItems; // This should be a list of your restaurant names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filter);

        searchField = findViewById(R.id.search_field);
        listView = findViewById(R.id.list_view_restaurants);

        // Initialize your list with restaurant names
        listItems = new ArrayList<>();
        // Populate listItems with your restaurant data
        listItems.add("Restaurant 1");
        listItems.add("Restaurant 2");
        // Add more restaurant names as needed

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // You want to filter the list as per the user's input
                SearchAndFilterActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Setup filters similar to the search functionality
        // Set onItemSelectedListeners on Spinners, etc.
    }
}
