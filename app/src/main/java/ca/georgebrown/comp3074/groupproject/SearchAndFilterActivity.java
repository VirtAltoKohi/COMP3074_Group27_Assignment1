package ca.georgebrown.comp3074.groupproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAndFilterActivity extends Activity {
    private EditText searchField;
    private ListView listView;
    private RestaurantAdapter adapter;
    private List<Restaurant> restaurants; // This should be your complete list of restaurants

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_filter);

        searchField = findViewById(R.id.search_field);
        listView = findViewById(R.id.list_view_restaurants);

        initializeRestaurantList();

        adapter = new RestaurantAdapter(this, R.layout.restaurant_item, restaurants);
        listView.setAdapter(adapter);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initializeRestaurantList() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Restaurant 1", "Address 1", "Phone 1", "Description 1", new ArrayList<String>(), 4.5f));
        restaurants.add(new Restaurant("Restaurant 2", "Address 2", "Phone 2", "Description 2", new ArrayList<String>(), 3.0f));
    }
}

class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private List<Restaurant> originalList;
    private List<Restaurant> filteredList;
    private Context context;

    public RestaurantAdapter(Context context, int resource, List<Restaurant> restaurants) {
        super(context, resource, restaurants);
        this.originalList = new ArrayList<>(restaurants);
        this.filteredList = new ArrayList<>(restaurants);
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = convertView.findViewById(R.id.restaurant_name);
            holder.addressTextView = convertView.findViewById(R.id.restaurant_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the restaurant item for this position
        Restaurant restaurant = getItem(position);
        holder.nameTextView.setText(restaurant.getName());
        holder.addressTextView.setText(restaurant.getAddress());

        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredList.clear();
        if (charText.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            for (Restaurant restaurant : originalList) {
                if (restaurant.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(restaurant);
                }
            }
        }
        notifyDataSetChanged();
    }

    // View holder to hold views for fast access during scrolling
    private static class ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
    }
}

class Restaurant {
    private String name;
    private String address;
    private String phone;
    private String description;
    private List<String> tags;
    private float rating;

    public Restaurant(String name, String address, String phone, String description, List<String> tags, float rating) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.tags = tags;
        this.rating = rating;
    }

    // Add getters for all properties
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public List<String> getTags() { return tags; }
    public float getRating() { return rating; }
}
