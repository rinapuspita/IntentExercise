package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import model.User;

public class ProfileActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    public static final String IMAGE_KEY = "image";
    private ImageView img;
    private TextView fullnameText;
    private TextView emailText;
    private TextView homepageText;
    private TextView aboutText;
    private String url;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullnameText = findViewById(R.id.label_fullname);
        emailText = findViewById(R.id.label_email);
        homepageText = findViewById(R.id.label_homepage);
        aboutText = findViewById(R.id.label_about);
        img = findViewById(R.id.image_profile);

        Bundle extras = getIntent().getExtras();

        User user = getIntent().getParcelableExtra("user");

        if (extras != null) {
            // TODO: display value here
            fullnameText.setText(user.getFullname());
            emailText.setText(user.getEmail());
            homepageText.setText(user.getHomepage());
            url = user.getHomepage();
            aboutText.setText(user.getAbout());
            uri = Uri.parse(extras.getString(IMAGE_KEY));
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            img.setImageBitmap(bitmap);

        }
    }

    public void handleHomepage(View view) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
            startActivity(intent);
    }
}
