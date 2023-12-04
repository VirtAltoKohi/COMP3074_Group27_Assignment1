package ca.georgebrown.comp3074.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

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

        emailButton.setOnClickListener(view -> shareViaEmail(postField.getText().toString()));
        twitterButton.setOnClickListener(view -> shareOnTwitter(postField.getText().toString()));
        facebookButton.setOnClickListener(view -> shareOnFacebook(postField.getText().toString()));
    }

    private void shareViaEmail(String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this restaurant");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showToast("No email app available");
        }
    }

    private void shareOnTwitter(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                intent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                startActivity(intent);
                return;
            }
        }

        // If Twitter app is not installed, then use the browser
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + Uri.encode(text))));
        } catch (Exception e) {
            showToast("Twitter app is not installed and no browser available");
        }
    }

    private void shareOnFacebook(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        showToast("Please copy the text and paste it on Facebook");

        // Optionally open Facebook app or web page
        try {
            startActivity(getPackageManager().getLaunchIntentForPackage("com.facebook.katana"));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com")));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
