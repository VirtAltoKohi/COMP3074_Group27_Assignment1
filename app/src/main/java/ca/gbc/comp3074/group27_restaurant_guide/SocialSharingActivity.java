package ca.gbc.comp3074.group27_restaurant_guide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SocialSharingActivity extends Activity {

    private EditText postField;
    private Button emailButton, twitterButton, facebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_sharing);

        postField = findViewById(R.id.post_field);
        emailButton = findViewById(R.id.email_button);
        twitterButton = findViewById(R.id.twitter_button);
        facebookButton = findViewById(R.id.facebook_button);

        // Set up listeners for buttons (functionality can be added later)
        emailButton.setOnClickListener(View -> {
            // Logic for sharing via Email
        });

        twitterButton.setOnClickListener(View -> {
            // Logic for sharing via Twitter/X
        });

        facebookButton.setOnClickListener(View -> {
            // Logic for sharing via Facebook/Meta
        });
    }
}
