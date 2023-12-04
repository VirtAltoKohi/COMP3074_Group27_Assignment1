package ca.gbc.comp3074.group27_restaurant_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar_MA;
    Button Add_Restaurant_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_MA = findViewById(R.id.toolbar_MA);
        Add_Restaurant_bt = findViewById(R.id.Add_Restaurant_bt);

        Add_Restaurant_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onAddRestaurantClick();
            }
        });

        setSupportActionBar(toolbar_MA);
    }

    public boolean onAddRestaurantClick() {
        startActivity(new Intent(this, AddRestaurantActivity.class));
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();
        if (id == R.id.menu_item_one) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (id == R.id.menu_item_two) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}